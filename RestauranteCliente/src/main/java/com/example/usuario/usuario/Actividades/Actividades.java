package com.example.usuario.usuario.Actividades;

import java.util.Date;

public class Actividades {
    private String nombre;
    private Date horario;
    private int precio;
    private String categoria;
    private int capacidad;



    public Actividades() {
    }

    public Actividades(String nombre, Date horario, int precio, String categoria, int capacidad) {
        this.nombre = nombre;
        this.horario = horario;
        this.precio = precio;
        this.categoria = categoria;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Actividades{" +
                "nombre='" + nombre + '\'' +
                ", horario=" + horario +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}