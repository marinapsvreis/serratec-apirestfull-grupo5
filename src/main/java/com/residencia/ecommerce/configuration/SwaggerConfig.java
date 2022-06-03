package com.residencia.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
	 return new OpenAPI()
	      .components(new Components().addSecuritySchemes("basicScheme", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
	      .addParameters("myHeader1", new Parameter().in("header").schema(new StringSchema()).name("myHeader1")).addHeaders("myHeader2", new Header().description("myHeader2 header").schema(new StringSchema())))
	      .info(new Info()
	      .title("Ecommerce API RestFull Serratec")
	      .version(appVersion)
	      .description("Grupo 5 - Breno de Medeiros, Ester Baltazar, Kauã Cassiano, Marina Portugal, Pedro Curioni e Sophia Araujo.")
	      .termsOfService("http://swagger.io/terms/")
	      .license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
