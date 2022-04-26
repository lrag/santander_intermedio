package com.curso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;

@SpringBootApplication
public class AplicacionCommandLineRunner implements CommandLineRunner {

	@Autowired
	private GestorClientes gestorClientes;
	
	public static void main(String[] args) {
		SpringApplication.run(AplicacionCommandLineRunner.class, args);
	}

	@Override
	//El método run recibirá lo mismo que recibió el main
	//Es una especia de main diferido que se ejecuta cuando el contenedor de spring ya existe
	public void run(String... args) throws Exception {
		System.out.println("================================================");
		Cliente cli = new Cliente(null,"Maggie","C/Evergreen Terrace","555",5000);
		gestorClientes.insertar(cli);
	
		System.out.println("================================================");
		List<Cliente> clientes = gestorClientes.listar();
		clientes.forEach(c -> System.out.println(c));		
	}

}
