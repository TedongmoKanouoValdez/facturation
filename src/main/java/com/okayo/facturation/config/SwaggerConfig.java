package com.okayo.facturation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API Facturation OKAYO")
                .version("1.0.0")
                .description("Documentation interactive de l'API de gestion des factures"));
    }
}
