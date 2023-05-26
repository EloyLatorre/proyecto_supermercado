package com.hibernate.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Producto")
public class Producto {

	/**
	 * Creación de atributos y relación de la base de datos con los mismos
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "precio")
	private double precio;

	@Column(name = "cantidad")
	private int cantidad;

	/**
	 * Relación many-to-one con la clase Categoria
	 */
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	/**
	 * Relación many-to-one con la clase Oferta
	 */
	@ManyToOne
	@JoinColumn(name = "oferta_id")
	private Oferta oferta;

	/**
	 * Constructores de la clase
	 * 
	 * @param codigo    - codigo identidicador del producto
	 * @param nombre    - nombre del producto
	 * @param precio    - precio a la venta
	 * @param cantidad  - stock del producto en el almacen
	 * @param categoria - categoría a la que pertenece el producto
	 * @param oferta    - oferta que tiene el producto
	 */
	public Producto(String codigo, String nombre, double precio, int cantidad, Categoria categoria, Oferta oferta) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
		this.categoria = categoria;
		this.oferta = oferta;
	}

	public Producto() {
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidadStock() {
		return cantidad;
	}

	public void setCantidadStock(int cantidadStock) {
		this.cantidad = cantidadStock;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	/**
	 * Sobrescritura del método toString para mostrar el nombre en un JComboBox
	 */
	@Override
	public String toString() {
		return nombre;
	}
}