package com.curso.rest;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.cfg.Configuracion;
import com.curso.servicio.dto.PedidoDto;

@FeignClient(name="pedidos", url = "http://localhost:8080/Ej12_Spring_Rest/servicios", configuration = Configuracion.class)
@RequestMapping("pedidos")
public interface PedidosRestProxy {

	@GetMapping(produces=MimeTypeUtils.APPLICATION_JSON_VALUE)
	List<PedidoDto> listar();

	@GetMapping(path="{id}", produces=MimeTypeUtils.APPLICATION_JSON_VALUE)
	ResponseEntity<PedidoDto> buscar(@PathVariable("id") Integer id);
	
	@PostMapping(consumes=MimeTypeUtils.APPLICATION_JSON_VALUE)
	ResponseEntity<PedidoDto> insertar(@RequestBody() PedidoDto pedidoDto);
	
	@PutMapping(path="{id}", consumes=MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<PedidoDto> modificar(@PathVariable("id") Integer idPedido);
	
	@DeleteMapping(path="{id}", produces=MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> borrar(@PathVariable("id") Integer idPedido);
	
}
