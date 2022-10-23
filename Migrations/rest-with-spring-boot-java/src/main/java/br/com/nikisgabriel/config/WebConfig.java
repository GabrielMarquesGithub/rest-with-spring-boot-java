package br.com.nikisgabriel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		//utilizando URL params para content negotiation
//		configurer
//		.parameterName("mediaType")//nome do parâmetro
//		.favorParameter(true)//aceitar parâmetro via URL
//		.ignoreAcceptHeader(true)//ignorar parâmetro via header
//		.useRegisteredExtensionsOnly(false)//receber tipo como extensão algo depreciado
//		.defaultContentType(MediaType.APPLICATION_JSON)//tipo de retorno padrão
//		.mediaType("xml", MediaType.APPLICATION_XML)//em XML
//		.mediaType("json", MediaType.APPLICATION_JSON);
		
		//utilizando HEADER params para content negotiation
		configurer
		.favorParameter(false)//aceitar parâmetro via URL
		.ignoreAcceptHeader(false)//ignorar parâmetro via header
		.useRegisteredExtensionsOnly(false)//receber tipo como extensão algo depreciado
		.defaultContentType(MediaType.APPLICATION_JSON)//tipo de retorno padrão
		.mediaType("xml", MediaType.APPLICATION_XML)//em XML
		.mediaType("json", MediaType.APPLICATION_JSON);
	}
	
}
