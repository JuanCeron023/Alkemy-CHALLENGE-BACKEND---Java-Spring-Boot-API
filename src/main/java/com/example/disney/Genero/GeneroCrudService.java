package com.example.disney.Genero;



import com.example.disney.personaje.personaje;

import java.util.Map;
import java.util.Optional;

/**
 */
public interface GeneroCrudService {

    Genero save(Genero Genero);

    Genero create(Genero Genero);

    Optional<Genero> find(String name);

    Optional<Boolean> delete(String name);

    Genero update(String name, Genero Genero);


    public Map<String, Genero> get();

}