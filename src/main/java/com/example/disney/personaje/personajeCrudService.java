package com.example.disney.personaje;



import java.util.Map;
import java.util.Optional;

/**
 */
public interface personajeCrudService {

    personaje save(personaje personaje);

    personaje create(personaje personaje);

    Optional<personaje> find(String name);

    Optional<Boolean> delete(String name);

    personaje update(String name, personaje personaje);

    Map<String, personaje> get();

}