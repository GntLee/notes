package main

import (
	"bytes"
	"context"
	"encoding/base64"
	"encoding/json"
	"errors"
	"flag"
	"fmt"
	"io"
	"io/ioutil"
	"log"
	"net/http"
	"net/url"
	"os"
	"os/signal"
	"strconv"
	"strings"
	"time"
)

var (
	authorizationHeader string // �����֤
)

const (
	// ����ڴ� 1Mb
	maxMemory int64 = 1024 << 10
)

// Ĭ�ϵ�HTTP�ͻ���
var httpClient = http.Client{
	Timeout: time.Second * 10,
}

// ��¼��֤
func BasicAuthHandler(handler http.Handler) http.HandlerFunc {
	return func(writer http.ResponseWriter, request *http.Request) {
		authorization := request.Header.Get("Authorization")
		if authorizationHeader != authorization {
			// δ��¼������֤ʧ��
			writer.Header().Set("WWW-Authenticate", "Basic realm="+strconv.Quote("Authorization Required"))
			writer.WriteHeader(http.StatusUnauthorized)
			return
		}
		handler.ServeHTTP(writer, request)
	}
}

func AuthorizationHeader(user, password string) string {
	return "Basic " + base64.StdEncoding.EncodeToString([]byte(user+":"+password))
}

// �����ڴ�ɢ·��
func FilePath(suffix string) string {
	builder := strings.Builder{}
	now := time.Now()
	builder.WriteString(fmt.Sprintf("%d/", now.Year()))
	builder.WriteString(fmt.Sprintf("%02d/", now.Month()))
	builder.WriteString(fmt.Sprintf("%02d/%d.%s", now.Day(), now.Unix(), suffix))
	return builder.String()
}

// ��ȡ�ļ���׺
func Suffix(filename string) (string, error) {
	index := strings.LastIndex(filename, ".")
	if index == -1 {
		return "", errors.New("���ǺϷ����ļ�")
	}
	suffix := filename[index+1:]
	// TODO �ļ�����У��
	return suffix, nil
}

// ��Ӧ�쳣��Ϣ
func ErrorResponse(writer http.ResponseWriter, message string, code int) {
	writer.Header().Set("Content-Type", "text/plan; charset=utf-8")
	writer.WriteHeader(code)
	_, _ = io.WriteString(writer, message)
	return
}

var (
	host = flag.String("host", "", "����host����ָ�����������")
	port = flag.Int("port", 8081, "ʹ�õĶ˿�")
	user = flag.String("user", "admin", "�����˻�")
	password = flag.String("password", "admin", "��������")
	githubAccessToken = flag.String("token", "", "Github�ӿڷ��ʵ�Token")
	githubAccount = flag.String("account", "KevinBlandy", "Github�˻�")
	githubRepository = flag.String("repository", "image-bucket", "Github�洢ͼƬ�Ĳֿ�")
)

func main() {

	flag.Parse()

	log.Printf("host=%s, port=%d, user=%s, password=%s, githubAccessToken=%s, githubAccount=%s, githubRepository=%s\n",
		*host, *port, *user, *password, *githubAccessToken, *githubAccount, *githubRepository)

	if *githubAccessToken == "" || *githubAccount == "" || *githubRepository == "" {
		log.Println("Github������Ϣ���ܲ���Ϊ��")
		os.Exit(1)
	}

	authorizationHeader = AuthorizationHeader(*user, *password)

	router := http.NewServeMux()

	// ��̬��ԴĿ¼
	router.Handle("/", BasicAuthHandler(http.FileServer(http.Dir("./static"))))

	// ִ���ϴ�
	router.HandleFunc("/upload", BasicAuthHandler(http.HandlerFunc(func(writer http.ResponseWriter, request *http.Request) {
		defer func() {
			_ = request.Body.Close()
		}()
		err := request.ParseMultipartForm(maxMemory)
		if err != nil {
			// MultipartForm�����쳣
			ErrorResponse(writer, "bad request", http.StatusBadRequest)
			return
		}
		form := request.MultipartForm
		images := make([]string, 0, 0)
		for _, files := range form.File {
			for _, file := range files {

				filename := file.Filename
				size := file.Size
				log.Printf("�� �ļ�:filename=%s, size=%d\n", filename, size)

				// ��ȡ�ļ���׺
				suffix, err := Suffix(filename)
				if err != nil {
					ErrorResponse(writer, err.Error(), http.StatusBadRequest)
					return
				}

				// ���ļ�
				f, _ := file.Open()
				// ��ȡ�ļ�
				data, _ := ioutil.ReadAll(f)
				_ = f.Close()

				// ������
				requestBody, _ := json.Marshal(map[string]string{
					"message": "file upload",
					"content": base64.StdEncoding.EncodeToString(data),
				})

				// ��Դ�洢·��
				filePath := FilePath(suffix)

				log.Printf("�� �ϴ�·��:%s\n", filePath)

				// ����URL
				requestUrl, _ := url.Parse(fmt.Sprintf("https://api.github.com/repos/%s/%s/contents/%s", *githubAccount, *githubRepository, filePath))
				log.Printf("�� ����URL:%s\n", requestUrl)
				request, _ := http.NewRequest(http.MethodPut, requestUrl.String(), bytes.NewReader(requestBody))
				request.Header.Set("Accept", "application/json")
				request.Header.Set("Content-Type", "application/json")
				request.Header.Set("Authorization", "token " + *githubAccessToken)
				response, err := httpClient.Do(request)
				if err != nil {
					ErrorResponse(writer, fmt.Sprintf("Github�����쳣:%s\n", err.Error()), http.StatusInternalServerError)
					return
				}
				responseBody, _ := ioutil.ReadAll(response.Body)
				_ = response.Body.Close()

				log.Printf("�� Github�ϴ���Ӧ:code=%d, body=%s\n", response.StatusCode, responseBody)

				if response.StatusCode != http.StatusCreated {
					ErrorResponse(writer, fmt.Sprintf("Github��Ӧ״̬��:code=%d, body=%s\n", response.StatusCode, responseBody), response.StatusCode)
					return
				}

				// ��������URL
				images = append(images, fmt.Sprintf("https://cdn.jsdelivr.net/gh/%s/%s/%s", *githubAccount, *githubRepository, filePath))
			}
		}

		jsonVal, _ := json.Marshal(images)
		writer.WriteHeader(http.StatusCreated)
		writer.Header().Set("Content-Type", "application/json")
		_, _ = writer.Write(jsonVal)
	})))
	server := http.Server{
		Addr:    fmt.Sprintf("%s:%d", *host, *port),
		Handler: router,
	}
	go func() {
		log.Println("��������...")
		if err := server.ListenAndServe(); err != nil && err != http.ErrServerClosed {
			log.Printf("���������쳣:%s\n", err.Error())
		}
	}()

	quit := make(chan os.Signal)
	signal.Notify(quit, os.Interrupt, os.Kill)
	<-quit

	log.Println("����ֹͣ��...")

	ctx, cancel := context.WithTimeout(context.Background(), time.Second*5)
	defer cancel()
	err := server.Shutdown(ctx)
	if err != nil {
		log.Printf("����ֹͣ�쳣:%s\n", err.Error())
	}
	log.Println("�����Ѿ�ֹͣ")
}


--------------
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Upload</title>
    </head>
    <body>
        <form id="form">
            <input name="file" type="file" multiple="multiple" accept="image/*"/>
        </form>
    </body>
    <script>

        window.onload = () => {
            document.querySelector(`input[name="file"]`).addEventListener('change', (e) => {
                const files = e.target.files
                if (!files) {
                    return
                }
                const formData = new FormData(document.querySelector('#form'))
                const xhr = new XMLHttpRequest()
                xhr.open("POST", "/upload")
                if (xhr.upload){
                    xhr.upload.onprogress = e => {
                        const percent  = ((e.loaded / e.total) * 100).toFixed(2);
                        console.log(`�ϴ����� ${percent}`);
                    }
                }
                xhr.onload = e => {
                    document.querySelector('#form').reset()
                    if (xhr.status == 201){
                        const images = JSON.parse(xhr.responseText)
                        images.forEach(i => console.log(i))
                    } else {
                        const errorMessage = xhr.responseText
                        alert(errorMessage);
                    }
                };
                xhr.send(formData)
            })
        }
    </script>
</html>