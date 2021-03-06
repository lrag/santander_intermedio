package com.curso.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curso.rest.dto.Mensaje;

@ControllerAdvice
public class ControladorExcepciones {

	public ControladorExcepciones() {
		super();
	}

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ResponseEntity<?> handleValidationException(Exception e) {
		
		System.out.println("=EXCEPCION CAPTURADA EN CONTROLADOR EXCEPCIONES==============================");
		e.printStackTrace();
		
		return new ResponseEntity<Mensaje>(new Mensaje("500","Error:"+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
}
