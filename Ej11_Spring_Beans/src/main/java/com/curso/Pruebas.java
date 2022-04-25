package com.curso;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.GestorPeliculas;
import com.curso.modelo.persistencia.PeliculaDao;
import com.curso.modelo.persistencia.util.DataSourceUtil;

public class Pruebas {

	public static void main(String[] args) {
		
		/*
		DataSource ds = DataSourceUtil.getDataSource();
		PeliculaDao pDao = new PeliculaDao();
		pDao.setDataSource(ds);
		GestorPeliculas gp = new GestorPeliculas();
		gp.setPeliculaDao(pDao);
		Pelicula p = new Pelicula(null, "Blade Runner", null, "CI-FI");
		gp.insertar(p);
		*/
		
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans.xml");
		
		GestorPeliculas gp = null;
		gp = (GestorPeliculas) appCtx.getBean("gestorPeliculas");
		gp = appCtx.getBean(GestorPeliculas.class);
		gp = appCtx.getBean("gestorPeliculas", GestorPeliculas.class);
		
		System.out.println(gp);
		gp.insertar(new Pelicula());
		
		
		
	}
	
}
