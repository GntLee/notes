----------------------------
�Զ���������͵�ת��		|
----------------------------
	# spring mvc�Ķ������򵥣�һ��Demo���¶�

	# Date ת��
		1,ʵ�ֽӿ�Converter
			package io.springcloud.web.converter;
			import java.text.ParseException;
			import java.text.SimpleDateFormat;
			import java.util.Date;

			import org.slf4j.Logger;
			import org.slf4j.LoggerFactory;
			import org.springframework.core.convert.converter.Converter;

			import com.alibaba.druid.util.StringUtils;

			import io.springcloud.common.Messages;
			import io.springcloud.exception.BusinessException;

			public class DateConverter implements Converter<String,Date>{
				
				private static final Logger LOGGER = LoggerFactory.getLogger(DateConverter.class);
				
				public static final SimpleDateFormat[] SIMPLE_DATE_FORMATS = new SimpleDateFormat[] {
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
					new SimpleDateFormat("yyyy-MM-dd"),
					new SimpleDateFormat("yyyy-MM"),
					new SimpleDateFormat("yyyy")
				};

				@Override
				public Date convert(String param) {
					LOGGER.debug("�Զ���Dateת��:{}",param);
					if(!StringUtils.isEmpty(param)) {
						for(SimpleDateFormat simpleDateFormat : SIMPLE_DATE_FORMATS) {
							try {
								return simpleDateFormat.parse(param);
							} catch (ParseException e) {
								e.printStackTrace();
								continue;
							}
						}
						throw new RuntimeException(new BusinessException(Messages.BAD_PARAM));
					}
					return null;
				}
			}
		
		2,��д������:WebMvcConfigurer��addFormatters����
			@Override
			public void addFormatters(FormatterRegistry registry) {
				registry.addConverter(new DateConverter());
			}