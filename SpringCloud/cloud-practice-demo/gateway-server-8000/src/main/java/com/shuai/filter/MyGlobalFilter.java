package com.shuai.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 执行first filter
        System.out.println("MyGlobalFilter 过滤器 前置 被调用了。。。");

        // 放行执行后续的filter链路
        Mono<Void> mono = chain.filter(exchange);

        // 执行last filter
        return mono.then(Mono.fromRunnable(() -> {
            System.out.println("MyGlobalFilter 响应 被处理了。。。");
        }));
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
