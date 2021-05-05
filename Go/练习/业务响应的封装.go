import (
	"encoding/json"
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
	"os"
)


// Code ҵ��״̬
type Code struct {
	HttpStatus int			// HttpStatus http״̬��
	Code string				// Code ҵ��״̬��
	Message string			// Message Ĭ����ʾ��Ϣ
}

// Ԥ����ҵ��״̬
var(
	//CodeOk �ɹ�
	CodeOk = &Code{HttpStatus: http.StatusOK, Code: "ok", Message: "Ok"}
	//CodeBadRequest ͨ�õĿͻ����쳣
	CodeBadRequest = &Code{HttpStatus: http.StatusBadRequest, Code: "BadRequest", Message: "�Ƿ�����"}
	// CodeServiceUnavailable ���񲻿���
	CodeServiceUnavailable = &Code{HttpStatus: http.StatusServiceUnavailable, Code: "ServiceUnavailable", Message: "���񲻿���"}
	// CodeServerError �������쳣
	CodeServerError = &Code{HttpStatus: http.StatusInternalServerError, Code: "ServerError", Message: "�������쳣"}
)


// Body ��Ӧjson��ʽ
type Body struct {
	Success bool		`json:"success"`
	Code string			`json:"code"`
	Data interface{}	`json:"data"`
	Message string		`json:"message"`
}

// Success ��ȡ�ɹ���Ӧ��
func Success (data interface{}) *Body {
	return &Body{
		Success: true,
		Code:    CodeOk.Code,
		Data:    data,
		Message: CodeOk.Message,
	}
}
// Fail ��ȡ�쳣��Ӧ��
func Fail (code *Code) *Body {
	return &Body{
		Success: false,
		Code:    code.Code,
		Data:    nil,
		Message: code.Message,
	}
}
// Fail1 ��ȡ�쳣��Ӧ�壬�Զ�����ʾ��Ϣ
func Fail1 (code *Code, message string) *Body {
	code.Message = message
	return Fail(code)
}

// ServiceError ҵ���쳣
type ServiceError struct {
	Message string			// �쳣��Ϣ
	Cause error				// ������쳣��Ϣ
	HttpStatus int			// http״̬��
	Body interface{}	// ��Ӧ�ͻ��˵�����
}
func (s ServiceError) Error () string {
	return s.Message
}

// NewServiceError �����µ�ҵ���쳣��ָ��ԭʼ�쳣��״̬�룬��Ϣ
func NewServiceError (cause error, code *Code, message string) *ServiceError {
	if message == "" {
		message = code.Message  // Ĭ��ʹ��״̬���Ĭ����ʾ��Ϣ
	}
	return &ServiceError{
		Message: message,
		Cause: cause,
		HttpStatus: code.HttpStatus,
		Body: &Body{
			Success: false,
			Code:    code.Code,
			Message: message,
		},
	}
}
// NewServiceError1 �����µ�ҵ���쳣��ָ��״̬��
func NewServiceError1(code *Code) *ServiceError {
	return NewServiceError(nil, code, "")
}
// NewServiceError2 �����ȵ�ҵ���쳣��ָ��״̬�룬�Զ����쳣��Ϣ
func NewServiceError2(code *Code, message string) *ServiceError {
	return NewServiceError(nil, code, message)
}
// NewServiceError3 �����µ�ҵ���쳣��ָ��ԭʼ�쳣��״̬��
func NewServiceError3(cause error, code *Code) *ServiceError {
	return NewServiceError(cause, code, "")
}

// FailResponse ��Ӧҵ���쳣��Ϣ��err ����Ϊ��
func failResponse (ctx *gin.Context, err *ServiceError){
	log.Printf("ҵ���쳣��%s\n", err.Error())
	if err.Cause != nil {
		log.Printf("�����쳣��%s\n", err.Cause.Error())
	}

	var statusCode = err.HttpStatus
	var responseBody []byte

	if err.Body != nil {
		if jsonBytes, err := json.Marshal(err.Body); err != nil {
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
			log.Printf("�쳣��%s\n", err.Error())

			var httpStatus = http.StatusInternalServerError

			// ��ϸ���쳣��Ϣ�ж�
			if os.IsNotExist(err) {			// �ļ�δ�ҵ�
				httpStatus = http.StatusNotFound
			} else if os.IsPermission(err) {		// Ȩ�޲���
				httpStatus = http.StatusForbidden
			} else if err.Error() == "http: request body too large" {		// ��Ϣ�����
				httpStatus = http.StatusRequestEntityTooLarge
			}

			ctx.Status(httpStatus)
		}
	}
}

