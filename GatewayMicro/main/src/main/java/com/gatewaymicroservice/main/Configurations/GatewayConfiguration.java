package com.gatewaymicroservice.main.Configurations;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    /**MAS ADELANTE ALAPLICAR SONAQUBE O MODIFICACIONES DE CALIDAD DE CODIGO
     * HAY QUE CONTEMPLAR USAR CONSTANTES O VARIABLES DE ENTORNO PARA LAS DEFINICIONES
     * DEL YAML Y DEL ROUTE LOCATOR
     * 
     * tambien es posible que la sobredeclaracion de rutas sea inutil cuando
     * puede que se pueda usar prefijos de asterisco, l9o indagaré después.
     */
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
                .route("servicio_tasks_create", r -> r.path("/api/v1/mx/tasks/create/new")
                    .and()
                    .method("POST")
                    .uri("http://localhost:8089"))
                .route("servicio_tasks_gettasks",p->p.path("/api/v1/mx/tasks/list/get")
                    .and()
                    .method("GET")
                    .uri("http://localhost:8089"))
                .route("servicio_tasks_updatetask",p->p.path("/api/v1/mx/tasks/change-status")
                    .and()
                    .method("PUT")
                    .uri("http://localhost:8089"))
                .build();
    }
}
