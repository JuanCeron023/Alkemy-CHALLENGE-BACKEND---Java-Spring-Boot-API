package com.example.disney.Genero;


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
public class Genero {
    private static final long serialVersionUID = 2396654715019746673L;

    String Imagen;
    String Nombre;
    ArrayList<String> PeliculasSeries;

    @JsonCreator
    Genero(@JsonProperty("Nombre") final String Nombre,
         @JsonProperty("Imagen") final String Imagen,
         @JsonProperty("PeliculasSeries") final   ArrayList<String>  PeliculasSeries) {
        super();
        this.Nombre = requireNonNull(Nombre);
        this.Imagen = requireNonNull(Imagen);

        this.PeliculasSeries = requireNonNull(PeliculasSeries);

    }

   // @JsonIgnore


    public String getNombre() {
        return Nombre;
    }

    public  ArrayList<String> getPeliculasSeries() {
        return PeliculasSeries;
    }

    public String getImagen() {
        return Imagen;
    }


}