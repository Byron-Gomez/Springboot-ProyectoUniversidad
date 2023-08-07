package org.gomez.springproyectouniverisdad_byrongomez.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("org.gomez")
                .packagesToScan("org.gomez.springproyectouniverisdad_byrongomez.controller.dto")
                .build();
    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Universidad")
                        .description("Registros de universidad")
                        .version("v2")
                        .license(new License().name("Byron Gomez").url(""))
                        .contact(new Contact()
                                .name("Byron Gomez")
                                .email("byronxgomez@gmail.com")
                                )
                );
    }
}
