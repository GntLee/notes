/*
+----------------------+--------------------------------------------+
| Length (8 byte)      | Body                                       |
+----------------------+--------------------------------------------+
*/

package main

import (
	"encoding/binary"
	"fmt"
	"io"
	"log"
	"net"
	"os"
	"time"
)
var (
	clog = log.New(os.Stdout, "client - ", log.LstdFlags)
	slog = log.New(os.Stdout, "server - ", log.LstdFlags)
)

func main() {
	go Server()
	go Client()

	select {}
}

func Client(){
	addr, err := net.ResolveTCPAddr("tcp4", "127.0.0.1:1024")
	if err != nil {
		clog.Println(err)
		return
	}
	conn, err := net.DialTCP("tcp4", nil, addr)
	if err != nil {
		clog.Println(err)
		return
	}

	// �첽��ȡ
	go func() {
		for {
			// ��ȡHeader
			length, err := ReadHeader(conn)
			if err != nil && err != io.EOF {
				conn.Close()
				return
			}

			// ��ȡbody
			dataBuffer, err := ReadBody(conn, length)
			if err != nil{
				conn.Close()
				return
			}

			data := string(dataBuffer)
			clog.Printf("�ͻ����յ���Ϣ��%s\n", data)
		}
	}()

	// ѭ��д��
	var count = 1
	for {
		request := fmt.Sprintf("���ǿͻ��˵ĵ� %d ����Ϣ", count)

		// д��Header
		WriteHeader(conn, uint64(len(request)))

		// д��body
		_, err = io.WriteString(conn, request)
		if err != nil {
			clog.Printf("д���쳣��%s\n", err.Error())
			return
		}
		count ++
		time.Sleep(time.Second)
	}
}

// ������
func Server (){
	addr, err := net.ResolveTCPAddr("tcp4", "0.0.0.0:1024")
	if err != nil {
		slog.Fatal(err)
	}
	server, err := net.ListenTCP("tcp4", addr)
	if err != nil {
		slog.Fatal(err)
	}
	for {
		conn, err := server.AcceptTCP()
		go ConnHandler(conn, err)
	}
}
func ConnHandler (conn *net.TCPConn, err error){
	if err != nil{
		slog.Printf("�����쳣:%s\n", err.Error())
		return
	}
	for {
		// ��ȡHeader
		length, err := ReadHeader(conn)
		if err != nil && err != io.EOF {
			conn.Close()
			return
		}

		// ��ȡbody
		dataBuffer, err := ReadBody(conn, length)
		if err != nil{
			conn.Close()
			return
		}

		data := string(dataBuffer)
		slog.Printf("�������յ���Ϣ��%s\n", data)

		// ���ͻ�����Ӧ����
		response := fmt.Sprintf("���Ƿ����������յ��������Ϣ��[%s]", time.Now().Format("2006-01-02 15:04:05.0000000"))

		// д��Header
		WriteHeader(conn, uint64(len(response)))

		// д��body
		_, err = io.WriteString(conn, response)
		if err != nil {
			slog.Printf("д���쳣��%s\n", err.Error())
			conn.Close()
		}
	}
}

// ��ȡ����
func ReadHeader(reader io.Reader) (uint64, error) {
	lengthBuffer := make([]byte, 8, 8)
	_, err := io.ReadFull(reader, lengthBuffer)
	if err != nil && err != io.EOF {
		slog.Printf("Header��ȡ�쳣:%s\n", err.Error())
		return 0, err
	}
	length, count := binary.Uvarint(lengthBuffer)
	if count <= 0 {
		return 0, fmt.Errorf("����Header�쳣(binary.Uvarint):%d", count)
	}
	return length, nil
}

// ��ȡBody
func ReadBody(reader io.Reader, length uint64) ([]byte, error){
	dataBuffer := make([]byte, length, length)
	_, err := io.ReadFull(reader, dataBuffer)
	if err != nil{
		slog.Printf("Body��ȡ�쳣:%s\n", err.Error())
		return nil, err
	}
	return dataBuffer, nil
}

// д��Header
func WriteHeader(writer io.Writer, length uint64) (int, error) {
	lengthBuffer := make([]byte, 8, 8)
	binary.PutUvarint(lengthBuffer, length)
	count, err := writer.Write(lengthBuffer)
	if err != nil {
		log.Printf("Headerд���쳣:%s\n", err.Error())
	}
	return count, err
}

