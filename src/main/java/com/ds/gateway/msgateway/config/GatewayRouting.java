package com.ds.gateway.msgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatewayRouting {
    private static final String MS_ADDRESS_LABEL = "lb://ms-address";

    //@Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("country-service", r->r.path("/country/**")
                        .filters(f -> f.rewritePath("/country/(?<path>.*)", "/$\\{path}"))
                        .uri(MS_ADDRESS_LABEL)) //static routing
                .route("state-service", r->r.path("/state/**")
                        //.filters(f -> f.rewritePath("/state/(?<path>.*)", "/$\\{path}"))
                        .uri(MS_ADDRESS_LABEL)) //dynamic routing
                //.route("city", r->r.path("/city/**").uri(MS_ADDRESS_LABEL))
                .route("city-service", r->r.path("/city/**")
                        //.filters(f -> f.rewritePath("/city/(?<path>.*)", "/$\\{path}"))
                        .uri(MS_ADDRESS_LABEL))
                /*
                .route("gateway-service", r -> r.path("/gateway-service/*")
                        .filters(f -> f.rewritePath("/city/(?<path>.*)", "/$\\{path}"))
                        .uri("gateway-service"))
                 */
                .route("openapi", r -> r.path("/v3/api-docs/**")
                        .filters(f -> f.rewritePath("/v3/api-docs/(?<path>.*))", "/$\\{path}/v3/api-docs"))
                        .uri("http://localhost:8099"))
                .build();

        /*
              - id: openapi
      uri: http://localhost:${server.port}
      predicates:
        - Path=/v3/api-docs/**
      filters:
        - RewritePath=/v3/api-docs/(?<path>.*), /$\{path}/v3/api-docs



            - id: ${spring.application.name}-service
      uri: lb://${spring.application.name}
      predicates:
        - Path=/${spring.application.name}/**
      filters:
        - RewritePath=/${spring.application.name}/(?<path>.*), /$\{path}
         */
    }
}
