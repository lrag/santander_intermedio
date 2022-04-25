package com.curso.modelo.entidad;

public class Pelicula {

	private Integer id;
	private String titulo;
	private String genero;
	private Director director;
	
	// alt+shift+s c
	// alt+shift+s o
	// alt+shift+s r
	// alt+shift+s s

	private Pelicula() {
		super();
		System.out.println("Creando una película");
	}

	public Pelicula(Integer id, String titulo, String genero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		System.out.println("Creando una película");
	}

	public Pelicula(Integer id, String titulo, String genero, Director director) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.director = director;
		System.out.println("Creando una película");
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

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	
	
}
