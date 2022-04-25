
package com.curso.modelo.persistencia;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.curso.modelo.entidad.Pelicula;

//@Repository
public class PeliculaDao {

	//@Autowired
	private DataSource dataSource; //null
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
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















