package com.shuai.springboot3_http_client.config;

import com.shuai.springboot3_http_client.holder.RemoteTokenContextHolder;
import com.shuai.springboot3_http_client.service.http.UmsAuthHttpApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class HttpInterfaceConfig {

    @Value("${remote.base-url.open-server}")
    private String baseUrl;

    @Resource
    private RemoteTokenContextHolder remoteTokenContextHolder;

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                // 添加请求过滤器，自动携带token
                .filter(addTokenFilter())
                .build();
    }

    /**
     * 请求过滤器：从session中获取token并添加到请求头
     */
    private ExchangeFilterFunction addTokenFilter() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            String token = remoteTokenContextHolder.getToken();
            if (token != null) {
                log.info("添加Authorization请求头: {}", token);
                ClientRequest newRequest = ClientRequest.from(clientRequest)
                        .header("Authorization", token)
                        .build();
                return Mono.just(newRequest);
            }
            return Mono.just(clientRequest);
        });
    }

    @Bean
    UmsAuthHttpApi umsAuthApi(WebClient webClient) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder()
                .exchangeAdapter(WebClientAdapter.create(webClient)).build();
        return factory.createClient(UmsAuthHttpApi.class);
    }
}