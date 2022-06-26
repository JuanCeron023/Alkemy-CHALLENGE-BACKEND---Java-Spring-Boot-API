package com.example.disney.peliculaSerie;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

@Value
@Builder
public class PeliculaSerie {
    private static final long serialVersionUID = 2396654715019746672L;

    String Imagen;
    String Titulo;
    String Fecha;
    double Calificacion;
    ArrayList<String>Personajes;

    @JsonCreator
    PeliculaSerie(@JsonProperty("Titulo") final String Titulo,
         @JsonProperty("Imagen") final String Imagen,
              @JsonProperty("Fecha") final String Fecha,
              @JsonProperty("Calificacion") final double Calificacion,
         @JsonProperty("Personajes") final  ArrayList<String>  Personajes) {
        super();
        this.Titulo = requireNonNull(Titulo);
        this.Imagen = requireNonNull(Imagen);

        this.Fecha = requireNonNull(Fecha);
        this.Calificacion = requireNonNull(Calificacion);
        this.Personajes = requireNonNull(Personajes);
    }

   // @JsonIgnore


    public String getTitulo() {
        return Titulo;
    }

    public String getFecha() {
        return Fecha;
    }

    public double getCalificacion() {
        return Calificacion;
    }

    public  ArrayList<String> getPersonajes() {
        return Personajes;
    }

    public String getImagen() {
        return Imagen;
    }


}