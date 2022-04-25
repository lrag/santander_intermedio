package com.curso.modelo.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.persistencia.PeliculaDao;
import com.curso.util.Logger;

//Estereotipos
//@Service   : Una clase con lógica de negocio
//@Repository: Una clase que hace consultas a la base de datos
//@Component : Una clase que no logramos categorizar
//Si tenemos SpringMVC
//@Controller     : Un controlador de Spring MVC
//@RestController : Un endpoint REST de Spring MVC

/*
<bean id="gestorPeliculas" class="com.curso.modelo.negocio" scope="singleton"/>

@Service
public class GestorPeliculas {
*/

/*
<bean id="tocoto" class="com.curso.modelo.negocio" scope="singleton"/>

@Service("tocoto")
public class GestorPeliculas {
*/

/*
<bean id="gestorPeliculas" class="com.curso.modelo.negocio" scope="prototype"/>

@Service
@Scope("prototype")
public class GestorPeliculas {
*/

@Service
public class GestorPeliculas {
	
	@Autowired
	//Por defecto es por tipo
	//Si hay más de un candidato por tipo prueba por nombre utilizando para ello el nombre del atributo
	//Si queremos la inyeccion por nombre añadimos
	//@Qualifier("identificador")	
	private PeliculaDao peliculaDao;

	@Autowired
	//@Qualifier("logger")
	private Logger logger;
	
	@Autowired
	//@Qualifier("loggerError")
	private Logger loggerError;
	
	public GestorPeliculas() {
		super();
		System.out.println("Creando un GestorPeliculas");
	}

	/*
	public void setPeliculaDao(PeliculaDao peliculaDao) {
		this.peliculaDao = peliculaDao;
	}
	*/

	public void insertar(Pelicula pelicula){
		//LN
		//...
		logger.escribir("Todo OK en insertarPelicula");
		loggerError.escribir("ZASCA en insertarPelicula!");
		peliculaDao.insertar(pelicula);		
	}
	
}



