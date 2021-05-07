package com.demo.web.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.NestedServletException;

@Component
@WebFilter(filterName = "accessLogFilter", urlPatterns = "/*")
@Order(-9999) 		// ��֤����ִ��
public class AccessLogFilter extends HttpFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccessLogFilter.class);
	
	private static final long serialVersionUID = -7791168563871425753L;
	
	// ��Ϣ�����
	@SuppressWarnings("unused")
	private static class PayloadTooLargeException extends RuntimeException {
		private static final long serialVersionUID = 3273651429076015456L;
		private final int maxBodySize;
		public PayloadTooLargeException(int maxBodySize) {
			super();
			this.maxBodySize = maxBodySize;
		}
	}

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(req, 30) { // ����30���ֽ�
			@Override
			protected void handleContentOverflow(int contentCacheLimit) {
				throw new PayloadTooLargeException(contentCacheLimit);
			}
		};
		
		ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(res);
		
		
		long start = System.currentTimeMillis();
		try {
			// ִ��������
			super.doFilter(cachingRequestWrapper, cachingResponseWrapper, chain);
		} catch (NestedServletException e) {
			Throwable cause = e.getCause();
			// �����峬�����ƣ����ı���ʽ���ͻ�����Ӧ�쳣��Ϣ��ʾ
			if (cause instanceof PayloadTooLargeException) {
				cachingResponseWrapper.setStatus(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE);
				cachingResponseWrapper.setContentType(MediaType.TEXT_PLAIN_VALUE);
				cachingResponseWrapper.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
				cachingResponseWrapper.getOutputStream().write("���������".getBytes(StandardCharsets.UTF_8));
			} else {
				throw new RuntimeException(e);
			}
		}
		
		long end = System.currentTimeMillis();
		
		String requestId = UUID.randomUUID().toString();		// ����Ψһ������ID
		cachingResponseWrapper.setHeader("x-request-id", requestId);
		
		String requestUri = req.getRequestURI();		// �����
		String queryParam = req.getQueryString();		// ��ѯ����
		String method = req.getMethod();				// ���󷽷�
		int status = cachingResponseWrapper.getStatus();// ��Ӧ״̬��
		
		// ������
		// ת��Ϊ�ַ������������������С������£���Ϊ�ֽ����ݲ�����������������룬
		String requestBody = new String(cachingRequestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);	
		// ��Ӧ��
		String responseBody = new String(cachingResponseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
		
		LOGGER.info("{} {}ms", requestId, end - start);
		LOGGER.info("{} {} {} {}", method, requestUri, queryParam, status);
		LOGGER.info("{}", requestBody);
		LOGGER.info("{}", responseBody);
		
		// ��һ������Ҫ���ѻ������Ӧ���ݣ�������ͻ���
		cachingResponseWrapper.copyBodyToResponse();
	}
}
