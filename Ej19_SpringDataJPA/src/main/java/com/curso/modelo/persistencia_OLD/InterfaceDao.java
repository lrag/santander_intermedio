package com.curso.modelo.persistencia_OLD;

import java.util.List;

public interface InterfaceDao<T, K> {

	void insertar(T obj);
	void modificar(T obj);
	void borrar(T obj);
	T buscar(K id);
	List<T> listarTodos();

}