package com.mycompany.mercaderia.model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mercaderia")
public class Mercaderia implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Size(min = 5, max = 200)
    private String nombre;
    private String descripcion;
    @NotNull
    @Min(value = 1)
    private Integer cantidad;
    @NotNull
    @Min(value = 1)
    private Double precio;
    @Version
    private Integer version;
    private static final NumberFormat numberFmt = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

    public Mercaderia() {
    }

    public Mercaderia(Integer id, String nome, String descricao, Integer quantidade, Double preco, Integer version) {
        this.id = id;
        this.nombre = nome;
        this.descripcion = descricao;
        this.cantidad = quantidade;
        this.precio = preco;
        this.version = version;
    }

    @Override
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getPrecioFormateado() {
        return convertPrecioToString(this.precio);
    }

    public Integer getVersion() {
        return version;
    }

    public static String convertPrecioToString(double precio) {
        return numberFmt.format(precio);
    }

    public static double formatStringToPrecio(String strPrecio) throws ParseException {
        return numberFmt.parse(strPrecio).doubleValue();
    }

    @Override
    public String toString() {
        return "[ " + nombre + " - " + descripcion + " - " + cantidad + " - " + precio + " ]";
    }
}
