package com.curso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.curso.servicio.dto.PedidoDto;

@SpringBootApplication
public class Aplicacion implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println(restTemplate);
		
		String url = "http://localhost:8090";
		
		
		//restTemplate.delete("http://localhost:8090/clientes/6");
		
		PedidoDto pedido = restTemplate.getForObject(url+"/pedidos/1", PedidoDto.class);
		System.out.println(pedido.getCodigo());
		
		ResponseEntity<List<PedidoDto>> respuesta =
		        restTemplate.exchange(url+"/pedidos",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<PedidoDto>>() {
		            });
		List<PedidoDto> pedidos = respuesta.getBody();
		pedidos.forEach( p -> System.out.println(p.getCodigo()));		
				
	}
}
