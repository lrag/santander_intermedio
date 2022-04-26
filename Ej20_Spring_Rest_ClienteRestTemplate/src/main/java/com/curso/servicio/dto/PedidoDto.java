package com.curso.servicio.dto;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class PedidoDto {
	
	private Integer id;
	private String codigo;
	private String fecha;
	private String estado;
	//Cliente deber�a ser ClienteDto
	private Cliente cliente;
	//private List<DetallePedido> detalles;

	public PedidoDto() {
		super();
	}

	public PedidoDto(Integer id, String codigo, String fecha, String estado, Cliente cliente) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.fecha = fecha;
		this.estado = estado;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
