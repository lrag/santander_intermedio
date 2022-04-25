package com.curso.cfg;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.curso.modelo.negocio.GestorPeliculas;
import com.curso.modelo.persistencia.PeliculaDao;
import com.curso.util.Logger;

//Para refererenciar otra clase @Configuration
//@Import({ AppConfigOthers.class }) //loads another JavaConfig

//Para referenciar un xml
//@ImportResource("classpath:/config/spring-web-servlet.xml")

//Para indicar que hay clases con anotaciones:
//<context:component-scan basePackage="..."/>
//@ComponentScan(basePackages= { "com.curso.modelo.negocio", "com.curso.modelo.persistencia"})

//Esta ya es opcional, pero si no lo colocamos perdemos algunas cosillas
@Configuration
public class Configuracion {
	
	/*
	<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource" scope="singleton">
		<property name="driverClassName" value="org.h2.Driver"/>
		<property name="url"             value="jdbc:h2:file:c:/h2/bbdd_santander"/>
		<property name="username"        value="sa"/>
		<property name="password"        value=""/>			
	</bean>	
	*/
	@Bean
	@Scope("singleton")
	DataSource dataSource() {
		System.out.println("Configuracion.dataSource()");
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:file:c:/h2/bbdd_santander");
		ds.setUsername("sa");
		ds.setPassword("");		
		return ds;
	}
	
	/*
	<bean id="peliculaDao" class="com.curso.modelo.persistencia.PeliculaDao">
		<property name="dataSource" ref="dataSource"/>
	</bean>
	*/
	@Bean
	PeliculaDao peliculaDao() {
		System.out.println("Configuracion.peliculaDao()");
		PeliculaDao peliculaDao = new PeliculaDao();

		/*Si hacemos esto estaremos creando otro datasource!!!!
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:file:c:/h2/bbdd_santander");
		ds.setUsername("sa");
		ds.setPassword("");*/
		
		//Esto es perfectamente legal si tenemos la anotación @Configuration:
		DataSource ds = dataSource();
		
		peliculaDao.setDataSource(ds);
		return peliculaDao;
	}
	
	/*
	<bean id="logger" class="com.curso.util.Logger">
		<property name="nombreFichero" value="log.txt"/>
	</bean>
	*/
	@Bean
	Logger logger() {
		Logger logger = new Logger();
		logger.setNombreFichero("log.txt");
		//Logger implementa InitializingBean pero no hace falta que invoquemos nosotros el método 'afterPropertiesSet'
		return logger;		
	}
	
	/*
	<bean id="loggerError" class="com.curso.util.Logger">
		<property name="nombreFichero" value="error.txt"/>
	</bean>
	 */
	@Bean
	Logger loggerError() {
		Logger logger = new Logger();
		logger.setNombreFichero("logError.txt");
		return logger;	
	}		
	
	/*
	<bean id="gestorPeliculas" class="com.curso.modelo.negocio.GestorPeliculas">
		<property name="peliculaDao" ref="peliculaDao"/>
	</bean>
	*/
	@Bean
	//Se puede poner @Autowired, pero es completamente opcional
	//Si es preciso, se puede colocar @Qualifier
	GestorPeliculas gestorPeliculas(
			@Autowired PeliculaDao peliculaDao,
			//Si necesitamos indicar el nombre de la bean ponemos @Qualifier
			@Autowired @Qualifier("logger") Logger logger1,
			@Autowired @Qualifier("loggerError") Logger logger2
			//Aqui hubiera bastado con:
			//Logger logger,
			//Logger loggerError
		) {
		GestorPeliculas gestorpeliculas = new GestorPeliculas();
		//Podemos hacerlo así, como en la bean 'peliculaDao'
		//PeliculaDao peliculaDao = peliculaDao();
		gestorpeliculas.setPeliculaDao(peliculaDao);
		gestorpeliculas.setLogger(logger1);
		gestorpeliculas.setLoggerError(logger2);
		return gestorpeliculas;		
	}
		
}
