package com.hibernate.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Oferta")
public class Oferta {

	/**
	 * Creación de atributos y relación de la base de datos con los mismos
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "descuento")
	private int descuento;

	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	@Column(name = "fecha_fin")
	private Date fechaFin;

	@Column(name = "precio_oferta")
	private double precioOferta;

	/**
	 * Relación many-to-one con la clase Producto
	 */
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	/**
	 * Constructores de la clase
	 * 
	 * @param fechaInicio  - cuando comienza de la oferta
	 * @param fechaFin     - cuando acaba de la oferta
	 * @param precioOferta - precio del producto aplicando la oferta
	 * @param descuento    - descuento aplicado en la oferta
	 */
	public Oferta(Date fechaInicio, Date fechaFin, double precioOferta, int descuento) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precioOferta = precioOferta;
		this.descuento = descuento;
	}

	public Oferta() {
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getPrecioOferta() {
		return precioOferta;
	}

	public void setPrecioOferta(double precioOferta) {
		this.precioOferta = precioOferta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

}