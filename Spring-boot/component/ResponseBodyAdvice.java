---------------------------------
Adviceϵ��
---------------------------------
	# ResponseBodyAdvice

		boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType);

		@Nullable
		T beforeBodyWrite(@Nullable T body, MethodParameter returnType, MediaType selectedContentType,
				Class<? extends HttpMessageConverter<?>> selectedConverterType,
				ServerHttpRequest request, ServerHttpResponse response);
	
	# RequestBodyAdvice


		boolean supports(MethodParameter methodParameter, Type targetType,
				Class<? extends HttpMessageConverter<?>> converterType);

		HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
				Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException;

		Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
				Type targetType, Class<? extends HttpMessageConverter<?>> converterType);

		@Nullable
		Object handleEmptyBody(@Nullable Object body, HttpInputMessage inputMessage, MethodParameter parameter,
				Type targetType, Class<? extends HttpMessageConverter<?>> converterType);


	
	
	# �������Ӧ Advice, ���������������Ӧ�屻����/����ǰ, �Խ��������д���
		* ��������, HttpMessageConverter ִ��֮ǰ

	# �Զ���ʵ�ֺ�, ��� @ControllerAdvice ����

------------------------------------------
����Ӧ���е� data�ֶΣ�����base64����
------------------------------------------

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


import com.google.gson.Gson;

import io.streamer.common.Message;
/**
 * 
 * ����Ӧ����м���
 * @author Administrator
 *
 */
@ControllerAdvice
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice<Message<Object>> {
	
	static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EncodeResponseBodyAdvice.class);
	
	@Autowired
	Gson gson;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		/**
		 * ���ض��������  Message ����ʹ���� GsonHttpMessageConverter 
		 */
		return Message.class.isAssignableFrom(returnType.getParameterType()) 
				// && GsonHttpMessageConverter.class.isAssignableFrom(converterType)
				;
	}

	@Override
	public Message<Object> beforeBodyWrite(Message<Object> body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		Object data = body.getData();
		if (data != null) {
			// ��дvalue
			body.setData(this.encode(data));
		}
		return body;
	}
	
	private String encode(Object data) {
		String jsonValue = gson.toJson(data);
		String encodeValue = Base64.getEncoder().encodeToString(jsonValue.getBytes(StandardCharsets.UTF_8));
		LOGGER.debug("����Ӧ����б��룺raw={},cipher={}", jsonValue, encodeValue);
		return encodeValue;
	}
}

	