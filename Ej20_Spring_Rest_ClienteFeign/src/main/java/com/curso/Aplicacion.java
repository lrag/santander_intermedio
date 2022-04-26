package com.curso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.curso.rest.PedidosRestProxy;
import com.curso.servicio.dto.PedidoDto;
import com.curso.servicio.dto.PeliculaDTO;

@SpringBootApplication
@EnableFeignClients
public class Aplicacion implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

	@Autowired
	private PedidosRestProxy pedidosRest;	
	
	@Override
	public void run(String... args) throws Exception {		
		List<PedidoDto> pedidos = pedidosRest.listar();
		pedidos.forEach( p -> System.out.println(p.getCodigo()));
	}
}


