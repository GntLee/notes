--------------------------
recaptcha
--------------------------
	# ע���ַ����Ҫ��ǽ��
		https://www.google.com/recaptcha/admin/create
	
	# �������̨
		https://www.google.com/recaptcha/admin/site/350006937
	
	# �ĵ���ַ
		https://developers.google.com/recaptcha/intro

----------------------------------------------
recaptcha v2 - �������˻������֤����ѡ��
----------------------------------------------
	# ע�����ѡ������
		* �������˻������֤����ѡ�� ��
		* ���� reCAPTCHA ����
		* reCAPTCHA Android
	

	# �Զ���ȾreCAPTCHAС����
		<script src="https://www.google.com/recaptcha/api.js" async defer></script>
			* ����ʹ��https����

		<div class="g-recaptcha" data-sitekey="your_site_key"></div>
	

	# �ֶ���ȾreCAPTCHAС����
		<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
			* onload��������Ϊonload�ص����������ƣ�������ص��������Լ�����api�������Ⱦ
			* ����render��������Ϊexplicit��
	
	# ���ڵ��û�����Ҫ��js��ĵ�ַ����Ϊ: https://www.recaptcha.net/recaptcha/api.js
	
	# js��Ĳ�ѯ����
		onload	��ѡ������ָ���ص�����������
		render	��ѡ�ġ��Ƿ���ʽ���ִ���С������Ĭ��ֵΪonload��������g-recaptcha�ҵ��ĵ�һ����ǩ�г��ִ���С������
		hl		��ѡ�ġ�ǿ�ƴ���С�������ض����Գ��֡����δָ�������Զ�����û������ԡ�
	
	# ��ǩ������
		data-sitekey			վ����Կ��

		data-theme				��ѡ�ġ�С��������ɫ���⡣
			dark
				* ��ɫ������
			light��Ĭ�ϣ�

		data-size				��ѡ�ġ�С�����Ĵ�С��
			compact				
				* ���������
			normal��Ĭ�ϣ�

		data-tabindex			��ѡ�ġ�С��������ѯ��tabindex�����ҳ���е�����Ԫ��ʹ��tabindex����Ӧ��������Ϊʹ�û����������ס�
			Ĭ��0

		data-callback			��ѡ�ġ���֤��Ļص����������token��Ϊ�������ݽ���
		data-expired-callback	��ѡ�ġ����Ļص����������ƣ���reCAPTCHA��Ӧ�������û���Ҫ������֤ʱִ�С�
		data-error-callback		��ѡ�ġ��ص����������ƣ���reCAPTCHA��������ͨ�����������ӣ�ʱִ�У������ڻָ�����֮ǰ�޷�����ִ�С�����ڴ˴�ָ�����ܣ�����֪ͨ�û�Ӧ���ԡ�
	
	# js��api
		grecaptcha.render(container,parameters)
			* ��Ⱦ����
				container Ԫ�ص�id������dom����
				parameters ��ʼ�����������Ǳ�ǩ�����ԣ������� {'siteKey': 'balababaaaa'}
			
			* ���᷵��һ��Ψһ��id������С������id(opt_widget_id)��

		grecaptcha.reset(opt_widget_id)
			* ������֤��
			* ��ѡ��С����id�����ûָ���������õ�һ��

		grecaptcha.getResponse(opt_widget_id)
			* ��ȡС��������Ӧ
			* ��ѡ��С����id�����ûָ�������ȡ��һ��
	
	

--------------------------
recaptcha v3
--------------------------
	# �ͻ��˼���
		<script src="https://www.google.com/recaptcha/api.js?render=_reCAPTCHA_site_key"></script>
		<script>
			grecaptcha.ready(function() {
				grecaptcha.execute('_reCAPTCHA_site_key_', {action: 'homepage'}).then(function(token) {
				
				});
			});
		</script>
		

		* grecaptcha.ready ����reCAPTCHA�����ʱ����

		* '_reCAPTCHA_site_key_' ����ҳ��Կ

		* ͨ��ִ��: grecaptcha.execute(...) ������ִ����֤
	
		* action����, ��ʾ��֤�ĳ����������Զ��塣�ں�̨��������Բ�ͬ�ĳ�������ͬ������
			homepage		��ҳ
			login			��¼
			social			�罻
			e-commerce		����
		
		* token ��Ҫ�ύ����ˣ����ͨ����֤API�ж������Ƿ�Ϸ�

		* (Fuck GFW)���ڵ��û�����Ҫ��js��ĵ�ַ����Ϊ: https://www.recaptcha.net/recaptcha/api.js

	
	# ����
		* �����ڿ���̨�ֶ�����js��api����token����tokenҲ����ͨ������˵���֤
		* ���һֱ�����ó���ȥ��ȡtoken��������˻���֤����ʧȥ�����ˣ�

--------------------------
����˽���
--------------------------
	#  POST �ӿ�
		https://www.google.com/recaptcha/api/siteverify
			secret			����˵� secret
			response		�ͻ������ɵ�token
			remoteip		��ѡ�Ĳ������ͻ���ip��ַ
		
		* (Fuck GFW)���ڵ��û�����Ҫ��api��ַ����Ϊ: https://www.recaptcha.net/recaptcha/api/siteverify
		

		* v2�汾����Ӧ
			{
				success: true							�Ƿ���֤ͨ��
				challenge_ts: "2020-02-28T03:52:03Z"
				hostname: "localhost"
			}
		
		* v3�汾����Ӧ
			{
			  "success": true|false,      // �������Ƿ���վ�����ЧreCAPTCHA����
			  "score": number             // ������ķ�����0.0-1.0�����˻��жϵĲο�ֵ��1 �����࣬0�ǻ�����
			  "action": string            // �������֤����
			  "challenge_ts": timestamp,  // ��ѯ���ص�ʱ�����ISO��ʽyyyy-MM-dd'T'HH:MM:ssZZ��
			  "hostname": string,         // ʹ��reCAPTCHA��վ���������
			  "error-codes": [...]        // ��ѡ�Ĵ������
			}
		
			* error-codes �쳣״̬��
				Error code				Description
				missing-input-secret	secret������ʧ��
				invalid-input-secret	secret������Ч���ʽ����
				missing-input-response	ȱ����Ӧ������
				invalid-input-response	��Ӧ������Ч���ʽ����
				bad-request	The request ��������Ч���ʽ����
				timeout-or-duplicate	��Ӧ������Ч��̫�ɻ���ǰʹ�ù���
	

	# ����reCAPTCHAͼ��
		* ���css
			.grecaptcha-badge { 
				display: none; 
			} 

	

----------------------------------------------
recaptcha v2 - ����demo
----------------------------------------------
# �ͻ���

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>�ȸ�ReCaptcha</title>
        <script src="https://www.recaptcha.net/recaptcha/api.js" async defer></script>
	</head>
	<body>
         <div class="g-recaptcha" data-sitekey="{{ clientSecret }}"></div>
         <button>����������֤</button>
	<script type="text/javascript">
        window.onload = () => {
            document.querySelector('button').addEventListener('click', () => {
                // ��ȡ��֤���token
                const token = grecaptcha.getResponse();
                if (!token){
                    alert('���ȵ�����������˻������֤��');
                    return;
                }
                fetch('/validate?token=' + token, {
                    method: 'GET'
                }).then(response => {
                    if (response.ok) {
                        response.json().then(message => {
                           console.log(message);
                           // ����ɹ���������֤��
                           grecaptcha.reset();
                        });
                    }else {
                        //TODO
                    }
                })
            });
        }
	</script>
	</body>
</html>

# ͨ�õķ����������

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;

import com.alibaba.fastjson.JSONObject;

import io.streamer.common.Message;
import io.streamer.common.annotations.ReCaptcha;
import io.streamer.common.exception.ServiceException;
import io.streamer.common.utils.WebUtils;
/**
 *  
 *  reCaptcha
 * 
 */
public class ReCaptchaInterceptor extends BaseHandlerInterceptor {
	
	static final Logger LOGGER = LoggerFactory.getLogger(ReCaptchaInterceptor.class);
			
	static final Double ROBOT = 0.0; 
	
	static final String SCORE_KEY = "score";
	
	static final String ACTION_KEY = "action";
	
	static final String TOKEN_PARAM_NAME = "_token";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${google.recaptcha.validate-api}")
	private String validateApi;
	
	@Value("${google.recaptcha.server-secret}")
	private String serverSecret;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
		
		ReCaptcha reCaptcha = handlerMethod.getMethodAnnotation(ReCaptcha.class);
		if (reCaptcha == null) {
			return true;
		}
		
		String token = request.getParameter(TOKEN_PARAM_NAME);
		
		if (StringUtils.isEmpty(token)) {
			throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "�Ƿ� ReCaptcha Token"));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("secret", this.serverSecret);
		requestBody.add("response", token);
		requestBody.add("remoteip", WebUtils.getRemoteAddr(request)); // �ͻ���ip��ַ�����Ǳ���Ĳ�����
		
		ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(this.validateApi, new HttpEntity<>(requestBody, httpHeaders), JSONObject.class);
		
		if (!responseEntity.getStatusCode().is2xxSuccessful()) {
			throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "ReCaptcha У�������쳣��HttpCode=" + responseEntity.getStatusCodeValue()));
		}
		
		JSONObject body = responseEntity.getBody();
		
		LOGGER.debug("recaptcha response={}", body);
		
		
		if (!body.getBoolean("success")){
			throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "ReCaptcha У������ʧ�ܣ�error-codes=" + body.getJSONArray("error-codes").toJSONString()));
		}
		
		
		/**
		 * ������score�����ԣ���v3�汾��ReCaptcha��
		 * ��ҪУ�顰action���ĺϷ��ԣ��Լ�����������ֵ
		 */
		Double source = body.getDouble(SCORE_KEY);
		if (source != null) {
			
			// action ��ƥ��
			if (!StringUtils.isEmpty(reCaptcha.value()) && !reCaptcha.value().equals(body.getString(ACTION_KEY))) {
				throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "�˻���֤ʧ�ܡ�"));
			}
			
			// �����ˣ����������ֵ��ڽӿڵ���ͷ�
			if (source.equals(ROBOT) || source < reCaptcha.minScore()) {
				throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "�˻���֤ʧ�ܡ�"));
			}
		}
		
		return true;
	}
}
