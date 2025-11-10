package org.shuai.nacosgateway8001.predictor;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.function.Predicate;

@Component
public class HeRoutePredicateFactory extends AbstractRoutePredicateFactory<HeRoutePredicateFactory.Config> {

    public HeRoutePredicateFactory() {
        super(Config.class);
    }
    public static final String NAME_KEY = "name";

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of(NAME_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return (GatewayPredicate) serverWebExchange -> {
            HttpHeaders headers = serverWebExchange.getRequest().getHeaders();
            List<String> strings = headers.get(config.getName());
            return (strings != null && !strings.isEmpty());
        };
    }

    public static class Config {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
