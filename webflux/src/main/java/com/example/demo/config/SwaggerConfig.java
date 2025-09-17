package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(OpenApiProps props) {
        final String securitySchemeName = "bearerAuth";

        Contact contact = new Contact()
                .email(props.contact().email())
                .url(props.url())
                .name(props.contact().name());
        License mitLicense = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title(props.title())
                .version(props.version())
                .contact(contact)
                .description(props.description())
                .termsOfService("https://opensource.org/licenses/MIT")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(new Server().url(props.host()).description(props.description())))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}

