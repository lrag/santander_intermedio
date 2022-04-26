package com.curso.servicio.dto;

import java.beans.Transient;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Producto {

	private Integer id;
	private String nombre;
	private String fabricante;
	private Double precio;
	private Integer existencias;
	
	public Producto() {
		super();
	}

	public Producto(Integer id, String nombre, String fabricante, Double precio, Integer existencias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fabricante = fabricante;
		this.precio = precio;
		this.existencias = existencias;
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

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getExistencias() {
		return existencias;
	}

	public void setExistencias(Integer existencias) {
		this.existencias = existencias;
	}

}
