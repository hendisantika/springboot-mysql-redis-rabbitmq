package com.hendisantika.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-mysql-redis-rabbitmq
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/04/22
 * Time: 21.42
 */
@Configuration
public class OpenAPIConfiguration {
    @Value("${app.local}")
    private String localhost;

    @Value("${app.dev}")
    private String development;

    @Bean
    public OpenAPI openAPI() {
        io.swagger.v3.oas.models.servers.Server localServer = new io.swagger.v3.oas.models.servers.Server();
        localServer.setDescription("local");
        localServer.setUrl(localhost);

        io.swagger.v3.oas.models.servers.Server devServer = new io.swagger.v3.oas.models.servers.Server();
        devServer.setDescription("dev");
        devServer.setUrl(development);

        OpenAPI openAPI = new OpenAPI();
        openAPI.info(new io.swagger.v3.oas.models.info.Info()
                .title("Spring Boot MySQL Redis RabbitMQ")
                .description(
                        "Spring Boot MySQL Redis RabbitMQ")
                .version("1.0.0")
                .contact(new io.swagger.v3.oas.models.info.Contact()
                        .name("Hendi Santika")
                        .url("https://linktr.ee/hendisantika")
                        .email("hendisantika@yahoo.co.id"))
                .termsOfService("TOC")
                .license(new io.swagger.v3.oas.models.info.License().name("License").url("https://linktr" +
                        ".ee/hendisantika")));
        openAPI.setServers(Arrays.asList(localServer, devServer));

        return openAPI;
    }
}
