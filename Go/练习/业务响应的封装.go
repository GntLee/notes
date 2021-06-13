
import (
	"fmt"
	"net/http"
)

// Code ҵ��״̬��
type Code struct {
	Code string
	HttpStatus int
	Message string
}

// SetMessage ���Ƶ�ǰCode�������µ���Ϣ��һ�������Զ�����Ϣ����
func (c Code) SetMessage (message string) *Code {
	return &Code{
		Code:       c.Code,
		HttpStatus: c.HttpStatus,
		Message:    message,
	}
}

func (c Code) String () string {
	return c.Code
}

// MarshalJSON ���л�Ϊ�ַ���
func (c Code) MarshalJSON() ([]byte, error) {
	return []byte(fmt.Sprintf(`"%s"`, c.Code)), nil
}

func (c *Code) UnmarshalJSON(data []byte) error {
	// Response һ�㲻��Ҫ���л�
	//if len(data) == 0 {
	//	return nil
	//}
	//val := string(data)
	//switch{
	//	case strings.EqualFold(val, "ok"): *c = *CodeOk
	//}
	return nil
}


// Ԥ����ϵͳ�е�ҵ��״̬��
var (
	// CodeOk ����
	CodeOk = &Code{Code: "OK", HttpStatus: http.StatusOK, Message: "ok"}

	//CodeBadRequest ͨ�õĿͻ����쳣
	CodeBadRequest = &Code{Code: "BadRequest",HttpStatus: http.StatusBadRequest, Message: "�Ƿ�����"}

	// CodeNotFound ��Դδ�ҵ�
	CodeNotFound = &Code{Code: "NotFound",HttpStatus: http.StatusNotFound, Message: "������Դ������"}

	// CodeUnauthorized ��Դδ�ҵ�
	CodeUnauthorized = &Code{Code: "Unauthorized",HttpStatus: http.StatusUnauthorized, Message: "δ��Ȩ"}

	// CodeForbidden ��Դδ�ҵ�
	CodeForbidden = &Code{Code: "Forbidden",HttpStatus: http.StatusForbidden, Message: "��Ȩ����"}

	// CodeRequestEntityTooLarge �������С��������
	CodeRequestEntityTooLarge = &Code{Code: "RequestEntityTooLarge",HttpStatus: http.StatusRequestEntityTooLarge, Message: "�������С��������"}

	// CodeServiceUnavailable ���񲻿���
	CodeServiceUnavailable = &Code{Code: "ServiceUnavailable", HttpStatus: http.StatusServiceUnavailable, Message: "���񲻿���"}

	// CodeServerError �������쳣
	CodeServerError = &Code{Code: "ServerError",HttpStatus: http.StatusInternalServerError,  Message: "�������쳣"}
)

// Response ��Ӧ�ͻ��˵�ͨ��JSON
type Response struct {
	Success bool		`json:"success"`
	Data interface{}	`json:"data"`
	Code *Code			`json:"code"`
	Message string		`json:"message"`
}

var (
	// EmptyOkResponse ����Ӧ
	EmptyOkResponse = OkResponse(nil)
)

// OkResponse �ɹ���Ӧ
func OkResponse (data interface{}) *Response {
	return &Response{
		Success: true,
		Code:    CodeOk,
		Data:    data,
		Message: CodeOk.Message,
	}
}
// FailResponse �쳣��Ӧ
func FailResponse (code *Code) *Response {
	return &Response{
		Success: false,
		Code:    code,
		Data:    nil,
		Message: code.Message,
	}
}


// ------------------- �쳣

// ServiceError ҵ���쳣
type ServiceError struct {
	Message string			// �쳣��Ϣ
	Cause error				// ������쳣��Ϣ
	HttpStatus int			// http״̬��
	Response interface{}		// ��Ӧ�ͻ��˵�����
}
func (s ServiceError) Error () string {
	return s.Message
}

// SetCause ����Catch���쳣
func (s ServiceError) SetCause(err error) *ServiceError {
	s.Cause = err
	return &s
}

// NewServiceError �����µ�ҵ���쳣��ָ��ԭʼ�쳣��״̬�룬��Ϣ
func NewServiceError (code *Code) *ServiceError {
	return &ServiceError{
		Message: code.Message,
		Cause: nil,
		HttpStatus: code.HttpStatus,
		Response: &Response{
			Success: false,
			Code:    code,
			Message: code.Message,
		},
	}
}