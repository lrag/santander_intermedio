package com.curso.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.curso.modelo.entidad.Cliente;

@XmlRootElement
public class ClienteDto {

	private Integer id;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String direccion;
	@NotEmpty
	private String telefono;
	private Integer numeroTC;

	public ClienteDto() {
		super();
	}

	public ClienteDto(Integer id, String nombre, String direccion, String telefono, Integer numeroTC) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.numeroTC = numeroTC;
	}
	
	public ClienteDto(Cliente cliente) {
		if(cliente==null) {
			return;
		}
		this.id        = cliente.getId();
		this.nombre    = cliente.getNombre();
		this.direccion = cliente.getDireccion();
		this.telefono  = cliente.getTelefono();
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
	
	public Cliente asCliente() {
		return new Cliente(id,nombre,direccion,telefono,numeroTC);
	}

}






















