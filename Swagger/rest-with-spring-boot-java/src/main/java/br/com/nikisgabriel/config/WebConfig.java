package br.com.nikisgabriel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// @Value o spring boot vai ler nosso application.yml e ser um valor
	@Value("${cors.originPatterns:default}") // após o : é colocado um valor default caso nada seja encontrado
	private String corsOriginPatterns = "";

	// método para cors
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String[] allowedOrigins = corsOriginPatterns.split(",");
		// "/**" indica que todas as rotas da API vão receber a config
		registry.addMapping("/**")
				// métodos que receberão o cors - ex: "GET", "POST"
				// "*" indica que todos os métodos receberão
				.allowedMethods("*")
				// as origens permitidas que neste caso estão sendo instanciadas pelo string via parâmetros atribuídos no arquivo .yml
				.allowedOrigins(allowedOrigins)
				// possibilita autenticação
				.allowCredentials(true);
				
	}

	// método para content negotiation
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// utilizando URL params para content negotiation
//		configurer
//		.parameterName("mediaType")//nome do parâmetro
//		.favorParameter(true)//aceitar parâmetro via URL
//		.ignoreAcceptHeader(true)//ignorar parâmetro via header
//		.useRegisteredExtensionsOnly(false)//receber tipo como extensão algo depreciado
//		.defaultContentType(MediaType.APPLICATION_JSON)//tipo de retorno padrão
//		.mediaType("xml", MediaType.APPLICATION_XML)//em XML
//		.mediaType("json", MediaType.APPLICATION_JSON);

		// utilizando HEADER params para content negotiation
		configurer.favorParameter(false)// aceitar parâmetro via URL
				.ignoreAcceptHeader(false)// ignorar parâmetro via header
				.useRegisteredExtensionsOnly(false)// receber tipo como extensão algo depreciado
				.defaultContentType(MediaType.APPLICATION_JSON)// tipo de retorno padrão
				.mediaType("xml", MediaType.APPLICATION_XML)// em XML
				.mediaType("json", MediaType.APPLICATION_JSON);
	}

}
