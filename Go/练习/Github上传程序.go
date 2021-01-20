package main

import (
	"bytes"
	"context"
	"encoding/base64"
	"encoding/json"
	"errors"
	"flag"
	"fmt"
	"html/template"
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

// ����
var (
	user string							// ��¼�˻�
	password string						// ��¼����
	githubAccessToken string			// Github�ķ���Token
	githubAccount string				// Github�˻�
	githubRepository string				// �洢ͼƬ�Ĳֿ�
)

var (
	authorizationHeader string
	htmlTemplate string
)

const (
	// ����ڴ� 1Mb
	maxMemory int64 = 1024 * 1024
)

// ��¼��֤
func BasicAuthHandler (handlerFunc http.HandlerFunc)  http.HandlerFunc {
	return func(writer http.ResponseWriter, request *http.Request) {
		authorization := request.Header.Get("Authorization")
		if authorizationHeader != authorization {
			// δ��¼������֤ʧ��
			writer.Header().Set("WWW-Authenticate", "Basic realm=" + strconv.Quote("Authorization Required"))
			writer.WriteHeader(http.StatusUnauthorized)
			return
		}
		handlerFunc.ServeHTTP(writer, request)
	}
}

func AuthorizationHeader(user, password string) string {
	return "Basic " + base64.StdEncoding.EncodeToString([]byte(user + ":" + password))
}

// ����HTML
func LoadTemplate() (string, error) {
	if htmlTemplate != "" {
		return htmlTemplate, nil
	}
	temp, err := template.ParseFiles("./index.html")
	if err != nil {
		return "", err
	}
	builder := new(strings.Builder)
	err = temp.Execute(builder, nil)
	if err != nil {
		return "", err
	}
	htmlTemplate = builder.String()
	return htmlTemplate, nil
}

// Ĭ�ϵ�HTTP�ͻ���
var httpClient = http.Client{
	Timeout: time.Second * 5,
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
func Suffix (filename string) (string, error){
	index := strings.LastIndex(filename, ".")
	if index == -1 {
		return "", errors.New("���ǺϷ����ļ�")
	}
	suffix := filename[index + 1:]
	return suffix, nil
}

// ���ı���ʽ��Ӧ�쳣��Ϣ
func ErrorResponse (writer http.ResponseWriter, message string, code int){
	writer.Header().Set("Content-Type", "text/plan")
	writer.WriteHeader(code)
	io.WriteString(writer, message)
	return
}

func main() {

	// �����������
	var host = *flag.String("host", "", "����������ָ�����������")
	var port = *flag.Int("port", 8081, "ʹ�õĶ˿�")
	user = *flag.String("user", "admin", "�����˻���Ĭ��admin")
	password = *flag.String("password", "admin", "�������룬Ĭ��admin")
	githubAccessToken = *flag.String("token", "bf31fb1a611a13bce449619ae854d5fc77f25733", "Github�ӿڷ��ʵ�Token")
	githubAccount = *flag.String("account", "KevinBlandy", "Github�˻�")
	githubRepository = *flag.String("repository", "image-bucket", "Github�洢ͼƬ�Ĳֿ�")

	flag.Parse()

	if githubAccessToken == "" || githubAccount == "" || githubRepository == "" {
		log.Panic("Github������Ϣ���ܲ���Ϊ��")
	}

	authorizationHeader = AuthorizationHeader(user, password)

	log.Printf("host=%s, port=%d, user=%s, password=%s, githubAccessToken=%s, githubAccount=%s, githubRepository=%s\n",
		host, port, user, password, githubAccessToken, githubAccount, githubRepository)

	router := http.NewServeMux()

	// ��ȾHTMLҳ��
	router.HandleFunc("/", BasicAuthHandler(func(writer http.ResponseWriter, request *http.Request) {
		html, err := LoadTemplate()
		if err != nil {
			ErrorResponse(writer, fmt.Sprintf("index.html�����쳣:%s\n", err.Error()), http.StatusInternalServerError)
			return
		}
		writer.WriteHeader(http.StatusOK)
		writer.Header().Set("Content-Type", "text/html")
		io.WriteString(writer, html)
	}))
	// ִ���ϴ�
	router.HandleFunc("/upload", BasicAuthHandler(func(writer http.ResponseWriter, request *http.Request) {
		defer request.Body.Close()
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
				log.Printf("�ļ�:filename=%s, size=%d\n", filename, size)

				// TODO �ļ������ж�

				suffix, _ := Suffix(filename)
				// ���ļ�
				f, _ := file.Open()
				// ��ȡ�ļ�
				data, _ := ioutil.ReadAll(f)

				// ������
				requestBody, _ := json.Marshal(map[string] string {
					"message": "file upload",
					"content": base64.StdEncoding.EncodeToString(data),
				})

				// ��Դ�洢·��
				filePath := FilePath(suffix)

				log.Printf("�ϴ�·��:%s\n", filePath)

				// ����URL
				requestUrl, _ := url.Parse(fmt.Sprintf("https://api.github.com/repos/%s/%s/contents/%s", githubAccount, githubRepository, filePath))
				log.Printf("����URL:%s\n", requestUrl)
				request, _ := http.NewRequest(http.MethodPut, requestUrl.String(), bytes.NewReader(requestBody))
				request.Header.Set("Accept", "application/json")
				request.Header.Set("Content-Type", "application/json")
				request.Header.Set("Authorization", "token " + githubAccessToken)
				response, err := httpClient.Do(request)
				if err != nil {
					ErrorResponse(writer, fmt.Sprintf("�����쳣:%s\n", err.Error()), http.StatusInternalServerError)
					return
				}
				if response.StatusCode != http.StatusCreated  {
					ErrorResponse(writer, fmt.Sprintf("��Ӧ״̬���쳣:code=%d\n", response.StatusCode), response.StatusCode)
					return
				}
				responseBody, _ := ioutil.ReadAll(response.Body)
				response.Body.Close()

				log.Printf("github�ϴ���Ӧ:code=%d, body=%s\n", response.StatusCode, responseBody)

				// ��������URL
				images = append(images, fmt.Sprintf("https://cdn.jsdelivr.net/gh/%s/%s/%s", githubAccount, githubRepository, filePath))
			}
		}

		jsonVal, _ := json.Marshal(images)
		writer.WriteHeader(http.StatusCreated)
		writer.Header().Set("Content-Type", "application/json")
		writer.Write(jsonVal)
	}))
	server := http.Server{
		Addr: fmt.Sprintf("%s:%d", host, port),
		Handler: router,
	}
	go func() {
		log.Println("��������...")
		if err := server.ListenAndServe(); err != nil && err != http.ErrServerClosed {
			log.Panicf("���������쳣:%s\n", err.Error())
		}
	}()
	quit := make(chan os.Signal)
	signal.Notify(quit, os.Interrupt, os.Kill)
	<- quit

	log.Println("����ֹͣ��...")

	ctx, cancel := context.WithTimeout(context.Background(), time.Second * 5)
	defer cancel()
	err := server.Shutdown(ctx)
	if err != nil {
		log.Panicf("����ֹͣ�쳣:%s\n", err.Error())
	}
	log.Println("�����Ѿ�ֹͣ")
}