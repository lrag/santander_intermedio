package com.curso.modelo.persistencia.util;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceUtil {

	private static DataSource dataSource;
	
	public synchronized static DataSource getDataSource() {
		//Inicialización perezosa
		if(dataSource == null) {
			dataSource = new DriverManagerDataSource("jdbc:h2:file:c:/h2/bbdd_FNMT","sa","");
		}
		return dataSource;
	}
	
}
