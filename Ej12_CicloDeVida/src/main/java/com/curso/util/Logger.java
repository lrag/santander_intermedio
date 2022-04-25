package com.curso.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//Ciclo de vida de una bean de Spring
//-----------------------------------
//1-Se instancia el objeto con new
//2-Se inicializan los atributos de la clase
//3-Se invoca el constructor
//4-Se inyectan las dependencias
//5-Si la clase implementa 'InitializingBean' spring invoca 'AfterPropertiesSet'
//6-Si se cierra el contendedor de Spring y la bean implementa 'DisposableBean' se invoca 'destroy'
//7-FIN

public class Logger implements InitializingBean, DisposableBean {

	private String nombreFichero;
	private BufferedWriter bw;
	
	//Cuando se está ejecutando el constructor todavia no se han inyectado las dependencias
	//Es demasiado pronto...
	public Logger() {
		super();
		System.out.println("Instanciando Logger");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		//Aqui ya tenemos inyectadas las dependencias
		//Esto es una suerte de constructor diferido
		System.out.println("afterPropertiesSet de Logger");
		try {
			System.out.println("NombreFichero:"+nombreFichero);
			FileWriter fw = new FileWriter(nombreFichero);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}	
	
	@Override
	//Este método será invocado por spring cuando la bean ya no sea necesaria
	public void destroy() throws Exception {
		System.out.println("destroy de Logger");
		//bw.flush();
		//Close tb. hace flush		
		bw.close();
	}	

	//Es Spring el que inyecta aqui el valor con el nombre del fichero
	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	
	public synchronized void escribir(String texto) {
		try {
			bw.write(texto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
