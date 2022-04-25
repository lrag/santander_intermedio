package com.curso.modelo.entidad;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity //Obligatoria
@Table(name = "peliculas") // Opcional
public class Pelicula {

	@Id // Obligatoria
	//@GeneratedValue(strategy = GenerationType.SEQUENCE) //Secuencia
	//@SequenceGenerator(name = "PELICULAS_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Columna autoincremental
	private Integer id;

	private String titulo;
	private String genero;
	private int duracion;
	@Column(name = "fecha_estreno")
	private LocalDate fechaEstreno;

	@Transient
	private String DatoQueNoQueremosQueSePersista;
	private transient String DatoQueNoQueremosQueSePersistaNiQueSeSerialize;

	public Pelicula() {
		super();
	}

	public Pelicula(Integer id, String titulo, String genero, int duracion, LocalDate fechaEstreno) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;
		this.fechaEstreno = fechaEstreno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public LocalDate getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getDatoQueNoQueremosQueSePersista() {
		return DatoQueNoQueremosQueSePersista;
	}

	public void setDatoQueNoQueremosQueSePersista(String datoQueNoQueremosQueSePersista) {
		DatoQueNoQueremosQueSePersista = datoQueNoQueremosQueSePersista;
	}

	public String getDatoQueNoQueremosQueSePersistaNiQueSeSerialize() {
		return DatoQueNoQueremosQueSePersistaNiQueSeSerialize;
	}

	public void setDatoQueNoQueremosQueSePersistaNiQueSeSerialize(
			String datoQueNoQueremosQueSePersistaNiQueSeSerialize) {
		DatoQueNoQueremosQueSePersistaNiQueSeSerialize = datoQueNoQueremosQueSePersistaNiQueSeSerialize;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", genero=" + genero + ", duracion=" + duracion
				+ ", fechaEstreno=" + fechaEstreno + "]";
	}

}
