package com.rajeev.Blogging_App.Configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfiguration {

    public static  final String AUTHORIZATION_HEADER =  "Authorization";


    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Blog App API")
                        .version("1.0")
                        .description("API Documentation for Blog Application using Spring OpenAPI"))
        .tags(Arrays.asList(
                new Tag().name("Authentication Api's"),
                new Tag().name("User Api's"),
                new Tag().name("Post API's"),
                new Tag().name("Category API's"),
                new Tag().name("Comment API's")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes(
                "bearerAuth" , new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                ));
    }
}
