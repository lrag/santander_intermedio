package com.curso.cfg;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.curso.modelo.negocio.GestorPeliculas;
import com.curso.modelo.persistencia.PeliculaDao;
import com.curso.util.Logger;

@Configuration
@ComponentScan(basePackages = "com.curso")
public class Configuracion {
	
	@Bean
	public DataSource dataSource() {
		System.out.println("Creando el dataSource");
		
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:file:c:/h2/bbdd_borrar");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
	}

	@Bean
	Logger logger() {
		Logger logger = new Logger();
		logger.setNombreFichero("logs/log.txt");
		return logger;
	}

	@Bean
	Logger loggerError() {
		Logger logger = new Logger();
		logger.setNombreFichero("logs/error.txt");
		return logger;
	}
		
}
