package com.curso.modelo.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.persistencia.PeliculaDao;
import com.curso.util.Logger;

@Service
public class GestorPeliculas {

	@Autowired //Por defecto es by type
	private PeliculaDao peliculaDao;
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private Logger loggerError;
	
	public GestorPeliculas() {
		super();
	}

	public void insertar(Pelicula pelicula){
		//LN
		//...
		peliculaDao.insertar(pelicula);		
		logger.escribir("Pelicula insertada");
		loggerError.escribir("ZASCA TARRASCA!");
	}
	
}



