package com.curso.modelo.negocio;

import org.springframework.stereotype.Service;

import com.curso.modelo.entidad.Cliente;

public interface GestorClientes {

	void insertar(Cliente cliente);
	void borrar(Cliente cliente);

}