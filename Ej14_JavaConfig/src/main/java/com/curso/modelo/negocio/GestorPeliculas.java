package com.curso.modelo.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.curso.cfg.Configuracion;
import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.persistencia.PeliculaDao;
import com.curso.util.Logger;

//@Service
public class GestorPeliculas {

	//@Autowired //Por defecto es by type
	private PeliculaDao peliculaDao;
	
	//@Autowired
	//Para cuando la inyección por tipo no basta usamos Qualifier para indicar el id de la bean que queremos inyectar
	//En este caso como el id de la bean a inyectar coincide con el nombre del atributo no haría falta el @Qualifier
	//@Qualifier("logger") 
	private Logger logger;
	//@Autowired
	//@Qualifier("loggerError")
	private Logger loggerError;
	
	public GestorPeliculas() {
		super();
	}

	public void setPeliculaDao(PeliculaDao peliculaDao) {
		this.peliculaDao = peliculaDao;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setLoggerError(Logger loggerError) {
		this.loggerError = loggerError;
	}

	public void insertar(Pelicula pelicula){
		//LN
		//...
		peliculaDao.insertar(pelicula);		
		logger.escribir("Pelicula insertada");
		loggerError.escribir("ZASCA TARRASCA!");
	}
	
}



