--------------------------
recaptcha
--------------------------
	# 注册地址（需要翻墙）
		https://www.google.com/recaptcha/admin/create
	
	# 管理控制台
		https://www.google.com/recaptcha/admin/site/350006937
	
	# 文档地址
		https://developers.google.com/recaptcha/intro

----------------------------------------------
recaptcha v2 - “进行人机身份验证”复选框
----------------------------------------------
	# 注册的是选择类型
		* “进行人机身份验证”复选框 √
		* 隐形 reCAPTCHA 徽章
		* reCAPTCHA Android
	

	# 自动渲染reCAPTCHA小部件
		<script src="https://www.google.com/recaptcha/api.js" async defer></script>
			* 必须使用https加载

		<div class="g-recaptcha" data-sitekey="your_site_key"></div>
	

	# 手动渲染reCAPTCHA小部件
		<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
			* onload参数设置为onload回调函数的名称，在这个回调函数中自己调用api来完成渲染
			* 并将render参数设置为explicit。
	
	# 国内的用户，需要把js库的地址更换为: https://www.recaptcha.net/recaptcha/api.js
	
	# js库的查询参数
		onload	可选参数。指定回调方法的名称
		render	可选的。是否显式呈现窗口小部件。默认值为onload，它将在g-recaptcha找到的第一个标签中呈现窗口小部件。
		hl		可选的。强制窗口小部件以特定语言呈现。如果未指定，则自动检测用户的语言。
	
	# 标签的属性
		data-sitekey			站点密钥。

		data-theme				可选的。小部件的颜色主题。
			dark
				* 深色，贼丑
			light（默认）

		data-size				可选的。小部件的大小。
			compact				
				* 方块儿？？
			normal（默认）

		data-tabindex			可选的。小部件和质询的tabindex。如果页面中的其他元素使用tabindex，则应将其设置为使用户导航更容易。
			默认0

		data-callback			可选的。验证后的回调函数，会把token作为参数传递进来
		data-expired-callback	可选的。您的回调函数的名称，当reCAPTCHA响应到期且用户需要重新验证时执行。
		data-error-callback		可选的。回调函数的名称，在reCAPTCHA遇到错误（通常是网络连接）时执行，并且在恢复连接之前无法继续执行。如果在此处指定功能，则负责通知用户应重试。
	
	# js的api
		grecaptcha.render(container,parameters)
			* 渲染容器
				container 元素的id，或者dom对象
				parameters 初始化参数（就是标签的属性），例如 {'siteKey': 'balababaaaa'}
			
			* 它会返回一个唯一的id，就是小部件的id(opt_widget_id)。

		grecaptcha.reset(opt_widget_id)
			* 重置验证码
			* 可选的小部件id，如果没指定，则重置第一个

		grecaptcha.getResponse(opt_widget_id)
			* 获取小部件的响应
			* 可选的小部件id，如果没指定，则获取第一个
	
	

--------------------------
recaptcha v3
--------------------------
	# 客户端集成
		<script src="https://www.google.com/recaptcha/api.js?render=_reCAPTCHA_site_key"></script>
		<script>
			grecaptcha.ready(function() {
				grecaptcha.execute('_reCAPTCHA_site_key_', {action: 'homepage'}).then(function(token) {
				
				});
			});
		</script>
		

		* grecaptcha.ready 会在reCAPTCHA库加载时运行

		* '_reCAPTCHA_site_key_' 是网页密钥

		* 通过执行: grecaptcha.execute(...) 方法来执行验证
	
		* action参数, 表示验证的场景，可以自定义。在后台，可以针对不同的场景做不同的设置
			homepage		主页
			login			登录
			social			社交
			e-commerce		电商
		
		* token 需要提交给后端，后端通过验证API判断请求是否合法

		* (Fuck GFW)国内的用户，需要把js库的地址更换为: https://www.recaptcha.net/recaptcha/api.js

	
	# 问题
		* 尝试在控制台手动调用js的api生成token，该token也可以通过服务端的验证
		* 如果一直尝试用程序去获取token，那这个人机验证不就失去意义了？

--------------------------
服务端接入
--------------------------
	#  POST 接口
		https://www.google.com/recaptcha/api/siteverify
			secret			服务端的 secret
			response		客户端生成的token
			remoteip		可选的参数，客户的ip地址
		
		* (Fuck GFW)国内的用户，需要把api地址更换为: https://www.recaptcha.net/recaptcha/api/siteverify
		

		* v2版本的响应
			{
				success: true							是否验证通过
				challenge_ts: "2020-02-28T03:52:03Z"
				hostname: "localhost"
			}
		
		* v3版本的响应
			{
			  "success": true|false,      // 此请求是否是站点的有效reCAPTCHA令牌
			  "score": number             // 此请求的分数（0.0-1.0），人机判断的参考值。1 是人类，0是机器。
			  "action": string            // 定义的验证场景
			  "challenge_ts": timestamp,  // 质询加载的时间戳（ISO格式yyyy-MM-dd'T'HH:MM:ssZZ）
			  "hostname": string,         // 使用reCAPTCHA的站点的主机名
			  "error-codes": [...]        // 可选的错误代码
			}
		
			* error-codes 异常状态码
				Error code				Description
				missing-input-secret	secret参数丢失。
				invalid-input-secret	secret参数无效或格式错误。
				missing-input-response	缺少响应参数。
				invalid-input-response	响应参数无效或格式错误。
				bad-request	The request 该请求无效或格式错误。
				timeout-or-duplicate	响应不再有效：太旧或以前使用过。
	

	# 隐藏reCAPTCHA图标
		* 添加css
			.grecaptcha-badge { 
				display: none; 
			} 

	

----------------------------------------------
recaptcha v2 - 整合demo
----------------------------------------------
# 客户端

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>谷歌ReCaptcha</title>
        <script src="https://www.recaptcha.net/recaptcha/api.js" async defer></script>
	</head>
	<body>
         <div class="g-recaptcha" data-sitekey="{{ clientSecret }}"></div>
         <button>点击我完成验证</button>
	<script type="text/javascript">
        window.onload = () => {
            document.querySelector('button').addEventListener('click', () => {
                // 获取验证码的token
                const token = grecaptcha.getResponse();
                if (!token){
                    alert('请先点击，“进行人机身份验证”');
                    return;
                }
                fetch('/validate?token=' + token, {
                    method: 'GET'
                }).then(response => {
                    if (response.ok) {
                        response.json().then(message => {
                           console.log(message);
                           // 请求成功，重置验证码
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

# 通用的服务端拦截器

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
			throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "非法 ReCaptcha Token"));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("secret", this.serverSecret);
		requestBody.add("response", token);
		requestBody.add("remoteip", WebUtils.getRemoteAddr(request)); // 客户的ip地址，不是必须的参数。
		
		ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(this.validateApi, new HttpEntity<>(requestBody, httpHeaders), JSONObject.class);
		
		if (!responseEntity.getStatusCode().is2xxSuccessful()) {
			throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "ReCaptcha 校验请求异常，HttpCode=" + responseEntity.getStatusCodeValue()));
		}
		
		JSONObject body = responseEntity.getBody();
		
		LOGGER.debug("recaptcha response={}", body);
		
		
		if (!body.getBoolean("success")){
			throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "ReCaptcha 校验请求失败，error-codes=" + body.getJSONArray("error-codes").toJSONString()));
		}
		
		
		/**
		 * 包含“score”属性，是v3版本的ReCaptcha。
		 * 需要校验“action”的合法性，以及机器人评分值
		 */
		Double source = body.getDouble(SCORE_KEY);
		if (source != null) {
			
			// action 不匹配
			if (!StringUtils.isEmpty(reCaptcha.value()) && !reCaptcha.value().equals(body.getString(ACTION_KEY))) {
				throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "人机验证失败。"));
			}
			
			// 机器人，或者是评分低于接口的最低分
			if (source.equals(ROBOT) || source < reCaptcha.minScore()) {
				throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "人机验证失败。"));
			}
		}
		
		return true;
	}
}
