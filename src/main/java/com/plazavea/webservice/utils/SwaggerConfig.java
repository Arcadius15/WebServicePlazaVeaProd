package com.plazavea.webservice.utils;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
                .securitySchemes(Arrays.asList(apiKey()))
				;
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Plaza Vea Service API",
				"Plaza Vea API Description",
				"1.0",
				"www.google.com",
				new Contact("Diego Vicente", "", "a19012386@idat.edu.pe"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList()
				);
	}

    private ApiKey apiKey() {
        return new ApiKey("jwtToken", "Authorization", "header");
    }
}
