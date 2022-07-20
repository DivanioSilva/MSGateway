package com.ds.gateway.msgateway.config;

import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private final RouteDefinitionLocator locator;

    @Autowired
    public SwaggerConfig(RouteDefinitionLocator locator) {
        this.locator = locator;
    }

    /*
    @Bean
    public CommandLineRunner openApiGroups(RouteDefinitionLocator locator, SwaggerUiConfigParameters swaggerUiParameters) {
        return args -> locator
                .getRouteDefinitions().collectList().block()
                .stream()
                .map(RouteDefinition::getId)
                .filter(id -> id.matches(".*-service"))
                .map(id -> id.replace("-service", ""))
                .forEach(swaggerUiParameters::addGroup);
    }

     */
    @Bean
    public List<RouteDefinition> apis(RouteDefinitionLocator locator, SwaggerUiConfigParameters swaggerUiParameters) {

        locator
                .getRouteDefinitions().collectList().block()
                .stream()
                .map(RouteDefinition::getId)
                .filter(id -> id.matches(".*-service"))
                .map(id -> id.replace("-service", ""))
                .forEach(swaggerUiParameters::addGroup);

        List<RouteDefinition> block = locator.getRouteDefinitions().collectList().block();

        return block;
    }
}
