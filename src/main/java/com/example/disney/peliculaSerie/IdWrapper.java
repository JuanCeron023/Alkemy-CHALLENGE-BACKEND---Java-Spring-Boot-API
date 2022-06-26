package com.example.disney.peliculaSerie;

import com.example.disney.personaje.personaje;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class IdWrapper {
    private String id;
    String Imagen;
    String Titulo;
    String Fecha;
    double Calificacion;
    @JsonProperty("Personajes")
    private List<personaje1> personajes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public double getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(double calificacion) {
        Calificacion = calificacion;
    }

     public void setPersonajes(List<personaje1> personajes) {
        this.personajes = personajes;
    }

    public List<personaje1> getPersonajes() {
        return personajes;
    }
}


class personaje1 {
    String Imagen;
    String Nombre;
    Integer Edad;
    Double Peso;
    String Historia;
    ArrayList<String> PeliculasSeries;

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public double getPeso() {
        return Peso;
    }

    public void setPeso(double peso) {
        Peso = peso;
    }

    public String getHistoria() {
        return Historia;
    }

    public void setHistoria(String historia) {
        Historia = historia;
    }

    public  ArrayList<String>  getPeliculasSeries() {
        return PeliculasSeries;
    }

    public void setPeliculasSeries( ArrayList<String>  peliculasSeries) {
        PeliculasSeries = peliculasSeries;
    }
}