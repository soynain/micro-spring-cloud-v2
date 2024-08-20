package com.gatewaymicroservice.main.Configurations;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    @Bean
    public RouteLocator gatewayPrincipalLocator(RouteLocatorBuilder builderPrincipal) {
        return builderPrincipal.routes()
                .route("servicio_login", p -> p.path("/login")
                        .and()
                        .method("POST")
                        .uri("http://localhost:8085"))
                .route("servicio_usuarios",p->p.path("/get/all")
                    .and()
                    .method("GET")
                    .uri("http://localhost:8086")
                )
                .route("servicio_create_usuario", r -> r.path("/api/v1/mx/create")
                .and()
                .method("POST")
                .uri("http://localhost:8085"))
                .build();
    }
}
