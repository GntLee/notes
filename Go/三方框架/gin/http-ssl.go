-------------------
Let's Encrypt��֧��
-------------------
	# һ�д���
		package main

		import (
			"log"

			"github.com/gin-gonic/autotls"
			"github.com/gin-gonic/gin"
		)

		func main() {
			r := gin.Default()

			// Ping handler
			r.GET("/ping", func(c *gin.Context) {
				c.String(200, "pong")
			})
			
			// 
			log.Fatal(autotls.Run(r, "example1.com", "example2.com"))
		}
	
	# Ĭ�ϵ�֤�黺��·��
		* darwin
			${home}/Library/Caches/golang-autocert
		
		* windows
			* �ӻ���������ȡ·��,���μ���������оͷ���
				APPDATA
				CSIDL_APPDATA
				TEMP
				TMP
				${env}/golang-autocert

			* �������û
				${home}/golang-autocert
		
		* ����л�������	XDG_CACHE_HOME
			${XDG_CACHE_HOME}/golang-autocert

		* Linux
			${hone}/.cache/golang-autocert
