--------------------
���ŵ�ֹͣ
--------------------
	package main
	import (
		"context"
		"github.com/gin-gonic/gin"
		"log"
		"net/http"
		"os"
		"os/signal"
		"time"
	)

	func main() {

		router := gin.New()
		server := http.Server{
			Addr: ":8080",
			Handler: router,
		}
		// �첽��������
		go func() {
			if err := server.ListenAndServe(); err != nil && err != http.ErrServerClosed {
				// ���������쳣���˳�����
				log.Fatalf("listen: %s\n", err)
			}
		}()

		// ע���źż����������ж��ź�
		quit := make(chan os.Signal)
		signal.Notify(quit, os.Interrupt)
		<- quit

		// �յ����źţ�ֹͣ����
		ctx, cancel := context.WithTimeout(context.Background(), time.Second * 10)
		defer cancel()
		if err := server.Shutdown(ctx); err != nil {
			// ����ֹͣ�쳣���˳�����
			log.Fatal("Server Shutdown:", err)
		}
		log.Println("Server exiting")
	}
