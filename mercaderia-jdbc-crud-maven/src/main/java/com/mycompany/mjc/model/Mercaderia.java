/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mjc.model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author josediaz
 */
public class Mercaderia {


    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private Double precio;
    private static final NumberFormat numberFmt =
            NumberFormat.getNumberInstance(new Locale("es", "PE"));

    public Mercaderia() {
    }

    public Mercaderia(Integer id, String nombre, String descripcion, Integer cantidad, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

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

    public static String convertPrecioToString(double precio) {
        return numberFmt.format(precio);
    }

    public static double formatStringToPrecio(String strPrecio) throws ParseException {
        return numberFmt.parse(strPrecio).doubleValue();
    }

    @Override
    public String toString() {
        return "Mercaderia{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mercaderia other = (Mercaderia) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
