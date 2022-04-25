package com.curso.modelo.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.curso.modelo.entidad.Director;
import com.curso.util.Logger;

@Service
public class GestorDirectores {
	
	@Autowired
	//@Qualifier("logger")
	private Logger logger;
	
	@Autowired
	//@Qualifier("loggerError")
	private Logger loggerError;
	
	public GestorDirectores() {
		super();
		System.out.println("Creando un GestorDirectores");
	}

	public void insertar(Director director){
		//LN
		//...
		logger.escribir("Todo OK en insertardirector");
		loggerError.escribir("ZASCA en insertarDirector!");
	}
	
}



