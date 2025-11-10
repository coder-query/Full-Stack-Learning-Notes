package org.shuai.nacosgateway8001.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 前置处理（PRE-FILTER）
        System.out.println("AuthGlobalFilter 过滤器被调用了。。。正在执行全局过滤器的认证");

        // 2. 执行过滤器链（核心业务）
        Mono<Void> filterChain = chain.filter(exchange);

        // 3. 后置处理（POST-FILTER）
        return filterChain.then(Mono.fromRunnable(() -> {
            // 这个 Runnable 会在收到响应后执行
            System.out.println("AuthGlobalFilter 响应被处理了。。。已经执行完全局过滤器的认证");
        }));
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
