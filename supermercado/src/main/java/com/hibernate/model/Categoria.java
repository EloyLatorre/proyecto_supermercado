package com.hibernate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Categoria")
public class Categoria {

	/**
	 * Creación de atributos y relación de la base de datos con los mismos
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	/**
	 * Constructores de la clase
	 * 
	 * @param nombre - nombre de la categoría
	 */
	public Categoria(String nombre) {
		this.nombre = nombre;
	}

	public Categoria() {
	}

	/**
	 * Getters y setters para los atributos de la clase
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Sobrescritura del método toString para mostrar el nombre en un JComboBox
	 */
	@Override
	public String toString() {
		return nombre;
	}
}