import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;

public class RefererInterceptor extends HandlerInterceptorAdapter {

    static final Logger LOGGER = LoggerFactory.getLogger(RefererInterceptor.class);

    /**
     * ��������İ���������
     */
    static final String[] ALLOW_DOMAIN = new String[] {"www.springboot.io", "127.0.0.1"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // �ͻ��˵� referer ͷ
        String referer = request.getHeader(HttpHeaders.REFERER);
        if (StringUtils.isEmpty(referer)){
            return true;
        }

        // �ͻ������������
        String serverName = request.getServerName();

        LOGGER.info("serverName={}, referer={}", serverName, referer);

        try{
            String refererHost = new URL(referer).getHost();

            if (refererHost.equals(serverName)){
                return true;
            }

            // ��ͬ��������ж��Ƿ��ڰ�������
            for (String domain : ALLOW_DOMAIN){
                if (domain.equalsIgnoreCase(refererHost)){
                    // ƥ�䵽����������
                    return true;
                }
            }

        }catch (MalformedURLException malformedURLException){

        }

        /**
         * �Ƿ�����
         */
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return false;
    }
}
