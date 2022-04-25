package com.curso.modelo.negocio;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.persistencia.PeliculaDao;

public class GestorPeliculas {
	
	//Dependencia
	private PeliculaDao peliculaDao;//null!
	
	public GestorPeliculas() {
		super();
		System.out.println("Creando un GestorPeliculas");
	}

	public void setPeliculaDao(PeliculaDao peliculaDao) {
		this.peliculaDao = peliculaDao;
	}

	public void insertar(Pelicula pelicula){
		//LN
		//...
		peliculaDao.insertar(pelicula);	
	}
	
	//modificarPelicula
	//listarPeliculas
	//borrarPelicula...
	
}
