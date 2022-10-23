package br.com.nikisgabriel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	//o @Bean possibilita ao spring gerenciar injeções de classes de terceiros
	//criamos um método responsável por instanciar uma classe e adicionamos @Bean
	//a classe retornada por esse método será gerenciada pelo Spring
	@Bean 
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()//info possibilita a personalização das informações da UI gerada
						.title("Rest API with spring boot")
						.version("v1")
						.description("Descrição do projeto")
						.termsOfService("Termos de serviço")
						.license(new License().name("Apache 2.0").url("")))
				;
	}
}
