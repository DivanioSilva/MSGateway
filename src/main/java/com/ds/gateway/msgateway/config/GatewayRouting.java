package com.ds.gateway.msgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouting {
    private static final String MS_ADDRESS_LABEL = "lb://address-service";

    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("country", r->r.path("/country/**").uri(MS_ADDRESS_LABEL)) //static routing
                .route("state", r->r.path("/state/**").uri(MS_ADDRESS_LABEL)) //dynamic routing
                .route("city", r->r.path("/city/**").uri(MS_ADDRESS_LABEL))
                .build();
    }
}
