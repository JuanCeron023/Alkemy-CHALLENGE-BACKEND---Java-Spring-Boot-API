package com.example.disney.peliculaSerie;



import com.example.disney.personaje.personaje;

import java.util.Map;
import java.util.Optional;

/**
 */
public interface PeliculaSerieCrudService {

    PeliculaSerie save(PeliculaSerie PeliculaSerie);

    PeliculaSerie create(PeliculaSerie PeliculaSerie);

    Optional<PeliculaSerie> find(String name);

    Optional<Boolean> delete(String name);

    PeliculaSerie update(String name, PeliculaSerie PeliculaSerie);
    Map<String, PeliculaSerie> get();
}