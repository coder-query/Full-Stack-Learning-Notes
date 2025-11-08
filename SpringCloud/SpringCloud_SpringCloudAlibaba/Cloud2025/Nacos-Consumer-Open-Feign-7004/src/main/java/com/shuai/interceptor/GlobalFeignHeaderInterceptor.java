package com.shuai.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Enumeration;

/**
 * OpenFeign全局拦截器：从当前请求头中提取需要传递的信息，添加到Feign调用的请求头中
 */
@Component // 全局生效：Spring会自动将其注册为所有FeignClient的拦截器
public class GlobalFeignHeaderInterceptor implements RequestInterceptor {

    // 需要传递的请求头列表（根据你的业务场景定义，如网关添加的用户ID、用户名等）
    private static final String[] NEED_TRANSFER_HEADERS = {
            "DETAILS_USER_ID",   // 对应网关添加的用户ID头
            "DETAILS_USERNAME",  // 对应网关添加的用户名头
            "USER_KEY"           // 对应网关添加的用户标识头
            // 可根据实际需求添加其他需要传递的头，如Token、自定义业务头
    };

    @Override
    public void apply(RequestTemplate template) {
        // 1. 获取当前线程的请求上下文（基于ThreadLocal）
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            // 非HTTP请求场景（如定时任务、内部服务调用），无需传递头信息
            return;
        }

        // 2. 获取当前请求的HttpServletRequest
        HttpServletRequest request = attributes.getRequest();
        if (request == null) {
            return;
        }

        // 3. 提取需要传递的头信息，添加到Feign请求头中
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                // 只传递我们需要的头（避免传递所有头，减少冗余）
                if (isNeedTransfer(headerName)) {
                    String headerValue = request.getHeader(headerName);
                    // 添加到Feign请求头（若有多个值，取第一个）
                    if (headerValue != null) {
                        template.header(headerName, headerValue);
                    }
                }
            }
        }
    }

    /**
     * 判断当前头是否需要传递
     */
    private boolean isNeedTransfer(String headerName) {
        for (String needHeader : NEED_TRANSFER_HEADERS) {
            if (needHeader.equalsIgnoreCase(headerName)) {
                return true;
            }
        }
        return false;
    }
}
