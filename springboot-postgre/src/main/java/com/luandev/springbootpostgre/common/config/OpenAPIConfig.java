package com.luandev.springbootpostgre.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class OpenAPIConfig {

    @Value("${packages.scan}")
    private String packageScan;

    @Value("${openapi.service.api-docs}")
    private String apiDocs;

    @Value("${openapi.service.title}")
    private String title;

    @Value("${openapi.service.description}")
    private String description;

    @Value("${openapi.service.version}")
    private String version;

    @Value("${openapi.service.server}")
    private String serverUrl;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group(apiDocs)
                .packagesToScan(packageScan)
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url(serverUrl)))
                .info(new Info().title(title)
                        .description(description)
                        .version(version)
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}
