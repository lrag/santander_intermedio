package com.curso;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.GestorPeliculas;

public class Pruebas {

	public static void main(String[] args) {
		
		AbstractApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans.xml");

		Pelicula p1 = new Pelicula(null, "Alien", "Ci-Fi");
		GestorPeliculas gp = appCtx.getBean("gestorPeliculas", GestorPeliculas.class);
		gp.insertar(p1);
		
		appCtx.close();
	} 
	
	
	
}
