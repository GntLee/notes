--------------------
FastJson-����springmvc|
--------------------
	# ��ͨת����
		FastJsonHttpMessageConverter
		FastJsonHttpMessageConverter4


	  <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>application/json</value>
                    </list>
                </property>
                <property name="fastJsonConfig">
                    <bean class="com.alibaba.fastjson.support.config.FastJsonConfig">
                        <property name="charset" value="UTF-8"/>
                        <property name="serializerFeatures">
                            <array>
                                <!-- ���null�ֶ� -->
                                <value>WriteMapNullValue</value>
                                <!-- ���keyʹ��˫���� -->
                                <value>QuoteFieldNames</value>
                                <!-- �ռ������[] -->
                                <value>WriteNullListAsEmpty</value>
                            </array>
                        </property>
                        <!-- ���ڸ�ʽ -->
                        <property name="dateFormat" value="yyyy-MM-dd HH:mm:ss"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

	# ��������
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonConfig.setCharset(StandardCharsets.UTF_8);
		fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);

		fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

		return new HttpMessageConverters(fastJsonHttpMessageConverter);
	
--------------------
FastJson-����֧��	|
--------------------
	# ��� JSONPResponseBodyAdvice
		@Bean
		public JSONPResponseBodyAdvice jSONPResponseBodyAdvice() {
			return new JSONPResponseBodyAdvice();
		}
	
	# Jsonp�ӿڷ��������ע�� @ResponseJSONP
		@RequestMapping("/test")
		@Controller
		public class TestController {
			
			@GetMapping("/jsonp")
			@ResponseJSONP(callback = "callback")
			@ResponseBody
			public Object jsonp() {
				return Message.success("Hello");
			}
		}

		* ��ע���һ�����ԣ�callback ������ָ���ͻ����ṩ�Ļص�����