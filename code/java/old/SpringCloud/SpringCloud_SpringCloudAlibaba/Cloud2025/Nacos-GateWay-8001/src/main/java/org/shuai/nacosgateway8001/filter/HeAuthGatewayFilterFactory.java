package org.shuai.nacosgateway8001.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class HeAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<HeAuthGatewayFilterFactory.Config> implements Ordered {

    public HeAuthGatewayFilterFactory() {
        super(Config.class);
    }

    public static final String NAME_KEY = "name";

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of(NAME_KEY);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // 1. 前置处理（PRE-FILTER）
            System.out.println("HeAuthGatewayFilterFactory 过滤器被调用了。。。");

            // 2. 执行过滤器链（核心业务）
            Mono<Void> filterChain = chain.filter(exchange);

            // 3. 后置处理（POST-FILTER）
            return filterChain.then(Mono.fromRunnable(() -> {
                // 这个 Runnable 会在收到响应后执行
                System.out.println("HeAuthGatewayFilterFactory 过滤器被调用了。。。");
            }));
        };
    }
//    @Override
//    public GatewayFilter apply(Config config) {
//        // 定义一个过滤器，并指定顺序
//        return new OrderedGatewayFilter((exchange, chain) -> {
//            System.out.println("HeAuthGatewayFilterFactory 过滤器被调用了。。。");
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                System.out.println("HeAuthGatewayFilterFactory 响应被处理了。。。");
//            }));
//        }, 0); // 这里硬编码了0，你也可以从config中读取，或者使用其他方式
//    }
    @Override
    public int getOrder() {
        return 0;
    }

    public static class Config {
        private String name;
        public String getName()
        {
            return name;
        }
        public void setName(String name)
        {
            this.name = name;
        }
    }
}
