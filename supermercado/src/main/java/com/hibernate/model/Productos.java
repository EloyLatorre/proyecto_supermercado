package com.hibernate.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Productos")
public class Productos {

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

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categorias Categorias;

    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private Ofertas oferta;

    public Productos(String codigo, String nombre, double precio, int cantidad, Categorias Categorias, Ofertas oferta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.Categorias = Categorias;
        this.oferta = oferta;
    }

    public Productos() {
    }

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

    public Categorias getCategorias() {
        return Categorias;
    }

    public void setCategorias(Categorias Categorias) {
        this.Categorias = Categorias;
    }

    public Ofertas getOferta() {
        return oferta;
    }

    public void setOferta(Ofertas oferta) {
        this.oferta = oferta;
    }

    @Override
    public String toString() {
        return nombre;
    }
}