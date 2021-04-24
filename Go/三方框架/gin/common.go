--------------------------------
ҵ���쳣����Ӧbody�����
--------------------------------

import (
	"encoding/json"
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
	"os"
)


// Status ҵ��״̬
type Status struct {
	HttpStatus int			// HttpStatus http״̬��
	Code string				// Code ҵ��״̬��
	Message string			// Message Ĭ����ʾ��Ϣ
}

// Ԥ����ҵ��״̬
var(
	CodeOk = &Status{HttpStatus: http.StatusOK, Code: "ok", Message: "ok"}
	CodeBadRequest = &Status{HttpStatus: http.StatusBadRequest, Code: "BadRequest", Message: "�Ƿ�����"}
	CodeServerError = &Status{HttpStatus: http.StatusInternalServerError, Code: "ServerError", Message: "�������쳣"}
)


// ResponseBody ��Ӧjson��ʽ
type ResponseBody struct {
	Success bool		`json:"success,omitempty"`
	Code string			`json:"code,omitempty"`
	Data interface{}	`json:"data,omitempty"`
	Message string		`json:"message,omitempty"`
}
var (
	BodyOk = SuccessBody(nil)
)

// SuccessBody ��ȡ�ɹ���Ӧ��
func SuccessBody (data interface{}) *ResponseBody {
	return &ResponseBody{
		Success: true,
		Code:    CodeOk.Code,
		Data:    data,
		Message: CodeOk.Message,
	}
}
// FailBody ��ȡ�쳣��Ӧ��
func FailBody (status Status) *ResponseBody {
	return &ResponseBody{
		Success: false,
		Code:    status.Code,
		Data:    nil,
		Message: status.Message,
	}
}
// FailBody1 ��ȡ�쳣��Ӧ�壬�Զ���body
func FailBody1 (status Status, message string) *ResponseBody {
	status.Message = message
	return FailBody(status)
}

// ServiceError ҵ���쳣
type ServiceError struct {
	Message string			// �쳣��Ϣ
	Cause error				// ������쳣��Ϣ
	HttpStatus int			// http״̬��
	ResponseBody interface{}	// ��Ӧ�ͻ��˵�����
}
func (s ServiceError) Error () string {
	return s.Message
}

// NewServiceError �����µ�ҵ���쳣��ָ��ԭʼ�쳣��״̬�룬��Ϣ
func NewServiceError (cause error, status Status, message string) *ServiceError {
	if message == "" {
		message = status.Message  // Ĭ��ʹ��״̬���Ĭ����ʾ��Ϣ
	}
	return &ServiceError {
		Message: message,
		Cause: cause,
		HttpStatus: status.HttpStatus,
		ResponseBody: ResponseBody {
			Success: false,
			Code:    status.Code,
			Message: message,
		},
	}
}
// NewServiceError1 �����µ�ҵ���쳣��ָ��״̬��
func NewServiceError1(status Status) *ServiceError {
	return NewServiceError(nil, status, "")
}
// NewServiceError2 �����ȵ�ҵ���쳣��ָ��״̬�룬�Զ����쳣��Ϣ
func NewServiceError2(status Status, message string) *ServiceError {
	return NewServiceError(nil, status, message)
}
// NewServiceError3 �����µ�ҵ���쳣��ָ��ԭʼ�쳣��״̬��
func NewServiceError3(cause error, status Status) *ServiceError {
	return NewServiceError(cause, status, "")
}

// FailResponse ��Ӧҵ���쳣��Ϣ��err ����Ϊ��
func failResponse (ctx *gin.Context, err *ServiceError){
	log.Printf("ҵ���쳣��message=%s\n", err.Error())
	if err.Cause != nil {
		log.Printf("�����쳣��message=%s\n", err.Cause.Error())
	}

	var statusCode = err.HttpStatus
	var responseBody []byte

	if err.ResponseBody != nil {
		if jsonBytes, err := json.Marshal(err.ResponseBody); err != nil {
			log.Printf("JSON�����쳣��%s\n", err.Error())
			statusCode = http.StatusInternalServerError
		} else {
			responseBody = jsonBytes
		}
	}

	ctx.Status(statusCode)
	// ��ӦBody
	if responseBody != nil {
		ctx.Header("Content-Type", "application/json; charset=utf-8")
		if _ ,err := ctx.Writer.Write(responseBody); err != nil {
			log.Printf("��ӦJSON��Ϣ�쳣��%s\n", err.Error())
		}
	}
}

// ErrorResponse ��Ӧδ֪�쳣��err���Ϊ�գ��򲻻����κδ���
func ErrorResponse (ctx *gin.Context, err error) {
	if err == nil {
		return
	}
	switch rawError := err.(type) {
		case ServiceError, *ServiceError:{
			if err, ok := rawError.(*ServiceError) ; ok {
				failResponse(ctx, err)
			}  else if err, ok := rawError.(ServiceError) ; ok {
				failResponse(ctx, &err)
			}
		}
		default: {
			// �����쳣��������־����쳣��Ϣ
			log.Printf("ϵͳ�쳣��%s\n", err.Error())

			var HttpStatus = http.StatusInternalServerError

			// ��ϸ���쳣��Ϣ�ж�
			if os.IsNotExist(err) {			// �ļ�δ�ҵ�
				HttpStatus = http.StatusNotFound
			} else if os.IsPermission(err) {		// Ȩ�޲���
				HttpStatus = http.StatusForbidden
			}

			ctx.Status(HttpStatus)
		}
	}
}
