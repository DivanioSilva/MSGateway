package com.ds.gateway.msgateway.config;

import org.springdoc.core.GroupedOpenApi;
//import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    private final RouteDefinitionLocator locator;


    @Autowired
    public SwaggerConfig(RouteDefinitionLocator locator) {
        this.locator = locator;
    }



    @Bean
    public CommandLineRunner openApiGroups(RouteDefinitionLocator locator, SwaggerUiConfigParameters swaggerUiParameters) {
        CommandLineRunner commandLineRunner = args -> locator
                .getRouteDefinitions().collectList().block()
                .stream()
                .map(RouteDefinition::getId)
                .filter(id -> id.matches(".*-service"))
                .map(id -> id.replace("-service", ""))
                .forEach(swaggerUiParameters::addGroup);
        return commandLineRunner;
    }


/*
    @Bean
    public List<GroupedOpenApi> apis() {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("-service", "");
            GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").setGroup(name).build();
        });
        return groups;
    }*/
}
