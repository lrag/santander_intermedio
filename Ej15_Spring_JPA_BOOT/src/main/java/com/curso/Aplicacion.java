package com.curso;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;

//@SpringBootApplication
public class Aplicacion {

	public static void main(String[] args) {

		ApplicationContext appCtx = SpringApplication.run(Aplicacion.class, args);
		
		//Esto es perfectamente correcto y legal
		GestorClientes gc = appCtx.getBean(GestorClientes.class);
		
		Cliente cli = new Cliente(null,"Maggie","C/Evergreen Terrace","555",5000);
		gc.insertar(cli);
		
		System.out.println("================================================");
		List<Cliente> clientes = gc.listar();
		clientes.forEach(c -> System.out.println(c));		
		
	}

}
