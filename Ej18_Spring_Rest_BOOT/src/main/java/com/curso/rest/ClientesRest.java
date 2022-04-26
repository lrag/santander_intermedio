package com.curso.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.rest.dto.ClienteDto;
import com.curso.rest.dto.Mensaje;
import com.curso.rest.dto.MensajeError;

@RestController
@RequestMapping("/clientes") //Esto servirá de prefijo a todos los 'path'
public class ClientesRest {

	@Autowired
	private GestorClientes gestorClientes;
	
	/*
	Metodo  Ruta			Body    Repuesta  Funcionalidad
	-----------------------------------------------------	 
	GET     /clientes       -       {json}    Listar clientes
	GET     /clientes/{id}  -       {json}    Buscar cliente por id
	POST    /clientes       {json}  -         Alta cliente
	PUT     /clientes/{id}  {ison}  -         Modificar cliente
	DELETE  /clientes/{id}  -       -         Baja cliente
	*/
	
	public ClientesRest() {
		super();
		System.out.println("Creando ClientesRest");
	}

	@GetMapping(produces=MimeTypeUtils.APPLICATION_JSON_VALUE)
	public List<ClienteDto> listar(/*@RequestParam("ciudad") String ciudad*/) {
		List<Cliente> clientes = gestorClientes.listar();
		List<ClienteDto> clientesDto = 
			clientes
				.stream()
				.map( c -> new ClienteDto(c))
				.collect(Collectors.toList());		
		return clientesDto;
	}

	@GetMapping(path="/{id}",
				produces=MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> buscar(@PathVariable("id") Integer id) {
		Cliente cliente = gestorClientes.buscar(id);
		if(cliente == null) {
			MensajeError e = new MensajeError("404","No existe un cliente con ese id");
			return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ClienteDto(cliente), HttpStatus.OK);
	}
	
	@PostMapping(consumes=MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertar(@Valid @RequestBody ClienteDto clienteDto, BindingResult br) {
		
		//Este Restcontroller no está utilizando el ExceptionHandler porque recoge el BindingResult
		if(br.hasErrors()) {
			Mensaje m = new Mensaje("400","Datos inválidos");
			return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
		}
		
		Cliente cliente = clienteDto.asCliente();
		gestorClientes.insertar(cliente);
		return new ResponseEntity<>(new ClienteDto(cliente), HttpStatus.CREATED);		
	}
	
	@PutMapping(path="/{id}",
			    consumes=MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> modificar(@PathVariable("id") Integer idCliente,
										    @Valid @RequestBody ClienteDto clienteDto, 
			                                BindingResult br) {
		if(br.hasErrors()) {
			Mensaje m = new Mensaje("400","Datos inválidos");
			return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
		}
		
		Cliente cliente = clienteDto.asCliente();
		//Nos fiamos solo del id que viene en la url, no en el cliente del body
		cliente.setId(idCliente);
		gestorClientes.modificar(cliente);
		return new ResponseEntity<>(new ClienteDto(cliente), HttpStatus.OK);		
	}	
	
	@DeleteMapping(path="/{id}",
			       produces=MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mensaje> borrar(@PathVariable("id") Integer idCliente){
		gestorClientes.borrar(new Cliente(idCliente));
		return new ResponseEntity<>(new Mensaje("200","Cliente borrado"), HttpStatus.OK);
	}	
	
}
