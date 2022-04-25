
package com.curso.modelo.persistencia;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.curso.modelo.entidad.Pelicula;

//Estereotipos
//@Service   : Una clase con lógica de negocio
//@Repository: Una clase que hace consultas a la base de datos
//@Component : Una clase que no logramos categorizar
//Si tenemos SpringMVC
//@Controller     : Un controlador de Spring MVC
//@RestController : Un endpoint REST de Spring MVC

//@Repository
//<bean id="peliculaDao" class="com.curso.modelo.persistencia.PeliculaDao" scope="singleton"/>

//@Repository("tocoto")
//<bean id="tocoto" class="com.curso.modelo.persistencia.PeliculaDao" scope="singleton"/>

@Repository
@Scope("singleton") //Esto es implícito
public class PeliculaDao {

	@Autowired
	//Por defecto es por tipo
	//Si hay más de un candidato por tipo prueba por nombre utilizando para ello el nombre del atributo
	//Si queremos la inyeccion por nombre añadimos
	//@Qualifier("dataSource")
	private DataSource dataSource;//NULL
	
	/*
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	*/
	
	public void insertar(Pelicula pelicula){
		Connection cx = null;
		try {			
			cx = dataSource.getConnection();
			System.out.println(cx);
			//Insert into...
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
}




