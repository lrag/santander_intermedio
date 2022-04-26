package com.curso.cfg;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.curso.rest") //Y sub paquetes
@EnableWebMvc
public class ConfiguracionMVC {

	public ConfiguracionMVC() {
		super();
		System.out.println("Creando una instancia de ConfiguracionMVC");
	}
	
}

