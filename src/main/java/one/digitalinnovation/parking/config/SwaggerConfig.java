package one.digitalinnovation.parking.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.security.SecurityScheme;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.OAS_30)
				.select()
				.apis(RequestHandlerSelectors.basePackage("one.digitalinnovation.parking"))
				.build()
				.apiInfo(metaData())
				.securityContexts(Arrays.asList(getSecurityContext()))
				.securitySchemes(Arrays.asList(basicAuthScheme()));
				
	}
	
	//modificação feita
	private springfox.documentation.service.SecurityScheme basicAuthScheme() {
		return new BasicAuth("basicAuth");
	}

	private SecurityContext getSecurityContext() {
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(basicAuthReference()))
				.build();
	}

	private SecurityReference basicAuthReference() {
		return new SecurityReference("basicAuth", new AuthorizationScope[0]);
	}

	
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Parking REST API")
				.description("Spring Boot REST API for Parking")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/Licenses/LICENSE-2.0\"")
				.build();	
	}
}
