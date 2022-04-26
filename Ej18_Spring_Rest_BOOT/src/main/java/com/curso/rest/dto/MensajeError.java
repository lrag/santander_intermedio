package com.curso.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MensajeError {

	private String codigo;
	private String mensaje;

	public MensajeError() {
		super();
	}

	public MensajeError(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Mensaje [codigo=" + codigo + ", mensaje=" + mensaje + "]";
	}

}
