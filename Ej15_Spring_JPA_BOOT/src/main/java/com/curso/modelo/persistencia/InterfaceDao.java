package com.curso.modelo.persistencia;

import java.util.List;

import com.curso.modelo.entidad.Cliente;

public interface InterfaceDao<T, k> {

	//Aqui definimos los métodos comunes a todos los dao
	void insertar(T cliente);
	void modificar(T cliente);
	void borrar(T cliente);
	T buscar(k idCliente);
	List<T> listar();
	
}
