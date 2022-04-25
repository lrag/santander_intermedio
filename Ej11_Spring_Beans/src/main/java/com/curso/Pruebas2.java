package com.curso;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.GestorPeliculas;

public class Pruebas2 {

	public static void main(String[] args) {
		
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans2.xml");

		System.out.println("=========================================");
		GestorPeliculas gp1 = (GestorPeliculas) appCtx.getBean("gestorPeliculasSingleton");
		GestorPeliculas gp2 = (GestorPeliculas) appCtx.getBean("gestorPeliculasSingleton");
		GestorPeliculas gp3 = (GestorPeliculas) appCtx.getBean("gestorPeliculasSingleton");
		GestorPeliculas gp4 = (GestorPeliculas) appCtx.getBean("gestorPeliculasSingleton");
		GestorPeliculas gp5 = (GestorPeliculas) appCtx.getBean("gestorPeliculasSingleton");
		System.out.println(gp1);
		System.out.println(gp2);
		System.out.println(gp3);
		System.out.println(gp4);
		System.out.println(gp5);

		System.out.println("=========================================");
		GestorPeliculas gp6 = (GestorPeliculas) appCtx.getBean("gestorPeliculasPrototype");
		GestorPeliculas gp7 = (GestorPeliculas) appCtx.getBean("gestorPeliculasPrototype");
		GestorPeliculas gp8 = (GestorPeliculas) appCtx.getBean("gestorPeliculasPrototype");
		GestorPeliculas gp9 = (GestorPeliculas) appCtx.getBean("gestorPeliculasPrototype");
		GestorPeliculas gp10 = (GestorPeliculas) appCtx.getBean("gestorPeliculasPrototype");
		System.out.println(gp6);
		System.out.println(gp7);
		System.out.println(gp8);
		System.out.println(gp9);
		System.out.println(gp10);

	}
	
}

