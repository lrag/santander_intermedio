package com.curso.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.negocio.GestorPedidos;
import com.curso.rest.dto.PedidoDto;

//@Controller
@RestController
@RequestMapping("/pedidos")
public class PedidosRest {

	@Autowired
	private GestorPedidos gestorPedidos;
	
	
	@GetMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public List<PedidoDto> listar() {
		List<Pedido> pedidos = gestorPedidos.listar();
		
		/*Como toda la vida de Dios
		List<PedidoDto> pedidosDto = new ArrayList<>();
		for(Pedido pAux: pedidos) {
			pedidosDto.add(new PedidoDto(pAux));
		}
		*/
		
		/*Con el m�todo 'forEach' de Collection y clase interna an�nima
		List<PedidoDto> pedidosDto = new ArrayList<>();
		pedidos.forEach(new Consumer<Pedido>() {
			public void accept(Pedido p) {
				pedidosDto.add(new PedidoDto(p));
			}
		});
		*/
		
		//Con el m�todo 'forEach' y una expresi�n lambda
		//List<PedidoDto> pedidosDto = new ArrayList<>();
		//pedidos.forEach( p -> pedidosDto.add(new PedidoDto(p)));
		
		//Con el api de streams (solo para flipados)
		List<PedidoDto> pedidosDto = 
			pedidos
				.stream()
					.map( p -> new PedidoDto(p))
						.collect(Collectors.toList());
		
		return pedidosDto;
	}

	@GetMapping(path="/{id}",
				produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<PedidoDto> buscar(@PathVariable("id") Integer id) {
		
		
		int numero = 10 / 0;
		
		
		Pedido pedido = gestorPedidos.buscar(id);
		if(pedido == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PedidoDto>(new PedidoDto(pedido), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
				 produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<PedidoDto> insertar(@Valid() @RequestBody() PedidoDto pedidoDto) {
		Pedido pedido = pedidoDto.asPedido();
		gestorPedidos.insertar(pedido);
		return new ResponseEntity<>(new PedidoDto(pedido), HttpStatus.OK);		
	}
	
	@PutMapping(path = "/{id}",
			    consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
			    produces = MimeTypeUtils.APPLICATION_JSON_VALUE)	
	public ResponseEntity<PedidoDto> modificar(@PathVariable("id") Integer idPedido,
									           @Valid() @RequestBody() PedidoDto pedidoDto) {
		
		Pedido pedido = pedidoDto.asPedido();
		//Nos fiamos solo del id que viene en la url, no en el pedido del body
		pedido.setId(idPedido);
		gestorPedidos.modificar(pedido);
		return new ResponseEntity<>(new PedidoDto(pedido), HttpStatus.OK);		
	}	
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> borrar(@PathVariable("id") Integer idPedido){
		gestorPedidos.borrar(idPedido);
		return new ResponseEntity<>("Pedido borrado", HttpStatus.OK);
	}
	
	//
	// Si en todos los RestControler vamos a controlar así
	// los errores de validación lo mejor es dar de alta
	// este exception handler en un @ControlerAdvice
	//
	/*
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {
		Map<String, String> errores = 
				e.getBindingResult().getFieldErrors()
					.stream()
						.collect(Collectors.toMap( fe -> (String) fe.getField(), 
								                   fe -> (String) fe.getDefaultMessage()));
		return new ResponseEntity<Object>(errores, HttpStatus.BAD_REQUEST);		
	}
	*/
	
	
}
