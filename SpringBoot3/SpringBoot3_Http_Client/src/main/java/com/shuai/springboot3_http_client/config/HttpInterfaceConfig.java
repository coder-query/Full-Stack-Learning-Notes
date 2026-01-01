package com.shuai.springboot3_http_client.config;

import com.shuai.springboot3_http_client.service.http.UmsAuthHttpApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpInterfaceConfig {

    @Value("${remote.base-url.open-server}")
    private String baseUrl;

    @Bean
    WebClient webClient() {
        return WebClient.builder().baseUrl(baseUrl).build();
    }

    @Bean
    UmsAuthHttpApi umsAuthApi(WebClient webClient) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder()
                .exchangeAdapter(WebClientAdapter.create(webClient)).build();
        return factory.createClient(UmsAuthHttpApi.class);
    }

}