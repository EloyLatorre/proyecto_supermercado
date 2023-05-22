package com.hibernate.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Ofertas")
public class Ofertas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "precio_oferta")
    private double precioOferta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Productos producto;

    public Ofertas(Date fechaInicio, Date fechaFin, double precioOferta) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioOferta = precioOferta;
    }

    public Ofertas() {
    }

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

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
}