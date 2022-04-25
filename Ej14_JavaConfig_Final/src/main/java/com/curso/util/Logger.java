package com.curso.util;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

//Vamos a necesitar más de un logger asi que no usamos arrobas aqui
public class Logger implements InitializingBean,
                               DisposableBean{

	private String nombreFichero;
	private BufferedWriter bw;
	
	public Logger() {
		System.out.println("Logger:Constructor");
	}	
	
	public void setNombreFichero(String nombreFichero) {
		System.out.println("Logger:SetNombreFichero");
		this.nombreFichero = nombreFichero;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Logger:AfterPropertiesSet-"+nombreFichero);
		FileWriter fw = new FileWriter(nombreFichero);
		bw = new BufferedWriter(fw);
	}

	@Override
	public void destroy() throws Exception {
		//bw.flush();
		//Close tb. hace flush
		System.out.println("Logger:destroy");
		bw.close();
	}
	
	
	public synchronized void escribir(String texto) {
		try{
			bw.write(texto+"\n");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
