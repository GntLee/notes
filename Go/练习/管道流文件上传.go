package main

import (
	"context"
	"encoding/json"
	"fmt"
	"github.com/gin-gonic/gin"
	"io"
	"io/ioutil"
	"log"
	"mime/multipart"
	"net/http"
	"net/textproto"
	"strings"
	"time"
)

func main(){

	// Http ����
	ctx, cancel := context.WithCancel(context.Background())
	ch := make(chan struct{})
	go server(ctx, ch)

	// �ܵ���
	r, w := io.Pipe()
	defer r.Close()

	// ���� multipart��ָ��writer
	formWriter := multipart.NewWriter(w)
	go func() {
		defer w.Close()
		var writer io.Writer

		// ���ٹ�����ͨ���key/value�����ַ���
		formWriter.WriteField("lang", "PHP��������õ�����")

		// ������ͨ�ı��ͨ��Writerд������
		writer, _ = formWriter.CreateFormField("lang")
		writer.Write([]byte("Java����������õ�����"))

		// �����ļ����ָ�������ƣ��Լ��ļ����ƣ�ͨ��Writerд�����ݣ�Ĭ�ϵ�ContentType �� application/octet-stream
		writer, _ = formWriter.CreateFormFile("file", "app.json")
		jsonVal, _ := json.Marshal(map[string] string {"name": "KevinBlandy"})
		writer.Write(jsonVal)

		// �Զ���part�����������Զ����header
		header := textproto.MIMEHeader{}
		header.Set("Content-Disposition", `form-data; name="file"; filename="app1.json"`)		// �Զ����ֶ����ƣ��ļ����ƣ����Ǳ����
		header.Set("Content-Type", `application/octet-stream`)									// ָ��ContentType�����Ǳ����
		writer, _ = formWriter.CreatePart(header)
		writer.Write([]byte("foo"))

		// ���д�룬��Ҫ����close����
		formWriter.Close()
	}()

	// ����http�ͻ���
	client := http.Client{}
	// ����request����ָ��body reader
	req, _ := http.NewRequest(http.MethodPost, "http://127.0.0.1/upload", r)
	req.Header.Set("Content-Type", formWriter.FormDataContentType()) // ��Ҫ��ȷ������ContentType

	// ִ�������ȡ��Ӧ
	resp, _ := client.Do(req)
	defer resp.Body.Close()

	// ��ȡ��Ӧ
	data, _ := ioutil.ReadAll(resp.Body)
	fmt.Println(string(data))

	// ֹͣ������
	cancel()

	// �ȴ��˳�
	<- ch
}
func server(ctx context.Context, ch chan <- struct{}){
	router := gin.Default()
	router.POST("/upload", func(ctx *gin.Context) {
		form, _ := ctx.MultipartForm()
		fmt.Println("��ͨ����-------------------")
		for key, value := range form.Value {
			fmt.Printf("name=%s, value=%s\n", key, strings.Join(value, ","))
		}
		fmt.Println("�ļ�����-------------------")
		for key, value := range form.File {
			for _, file := range value {
				fmt.Printf("name=%s, size=%d, fileName=%s, headers=%v\n", key, file.Size, file.Filename, file.Header)
			}
		}
		ctx.Writer.Header().Set("Content-Type", "text/plan")
		ctx.Writer.WriteString("success")
	})
	server := http.Server{
		Addr: ":80",
		Handler: router,
	}

	// ��������
	go func() {
		if err := server.ListenAndServe(); err != nil && err != http.ErrServerClosed {
			log.Panic(err)
		}
	}()

	for {
		select {
			case <- ctx.Done():{
				ctx, _ := context.WithTimeout(context.Background(), time.Second * 2)
				server.Shutdown(ctx)
				log.Println("������ֹͣ...")
				ch <- struct{}{}
				return
			}
		}
	}
}


