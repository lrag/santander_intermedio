package com.curso.cfg;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configuracion {

	//Como todo el mundo sabe la RestTemplate es thread safe
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		//return restTemplateBuilder.basicAuthorization("admin", "admin").build();
		return restTemplateBuilder.build(); // new RestTemplate();
	}

}
