package com.example.disney.personaje;


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
public class personaje {
    private static final long serialVersionUID = 2396654715019746671L;

    String Imagen;
    String Nombre;
    Integer Edad;
   Double Peso;
    String Historia;
    ArrayList<String>  PeliculasSeries;




    @JsonCreator
    personaje(@JsonProperty("Nombre") final String Nombre,
         @JsonProperty("Imagen") final String Imagen,
              @JsonProperty("Edad") int  Edad,
              @JsonProperty("Peso") final double Peso,
              @JsonProperty("Historia") final String Historia,
         @JsonProperty("PeliculasSeries") final ArrayList<String>   PeliculasSeries) {
        super();
        this.Nombre = requireNonNull(Nombre);
        this.Imagen = requireNonNull(Imagen);
        this.Peso = requireNonNull(Peso);
        this.Historia = requireNonNull(Historia);
        this.Edad = requireNonNull(Edad);
        this.PeliculasSeries = requireNonNull(PeliculasSeries);
    }

   // @JsonIgnore


    public String getImagen() {
        return Imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public double getPeso() {
        return Peso;
    }

    public String getHistoria() {
        return Historia;
    }

    public  ArrayList<String>  getPeliculasSeries() {
        return PeliculasSeries;
    }
}