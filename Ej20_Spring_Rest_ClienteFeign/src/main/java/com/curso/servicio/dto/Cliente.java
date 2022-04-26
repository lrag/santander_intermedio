package com.curso.servicio.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cliente {

	private Integer id;
	private String nombre;
	private String direccion;
	private String telefono;
	private Integer numeroTC;

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nombre, String direccion, String telefono, Integer numeroTC) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.numeroTC = numeroTC;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getNumeroTC() {
		return numeroTC;
	}

	public void setNumeroTC(Integer numeroTC) {
		this.numeroTC = numeroTC;
	}

}
