package com.apss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {

	// build docket build object -- will generate api f

	@Bean
	public Docket swaggerConfig() {

		/*
		 * return new
		 * Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant(
		 * "/book/*"))
		 * 
		 * .build() .apiInfo(new ApiInfo("Aws50-SpringBoot",
		 * "Aws-50 Spring Boot Training", "1.0", null, null, null, null));
		 */

		return new Docket(DocumentationType.SWAGGER_2).groupName("Spring Training").apiInfo(apiInfo()).select() // .paths(PathSelectors.ant(("/employee.*")))
				.paths(PathSelectors.regex("/User.*")).build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Appointment Scheduling System ")
				.description("Sample documentation generated using SWAGGER 2 for our rest API")
				.termsOfServiceUrl("some URL Name").license("Java batch license").licenseUrl("Specify licence URL name")
				.version("1.0").build();
	}

}
