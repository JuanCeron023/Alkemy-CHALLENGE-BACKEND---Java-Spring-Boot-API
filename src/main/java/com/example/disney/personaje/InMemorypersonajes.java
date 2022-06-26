package com.example.disney.personaje;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
final class InMemorypersonajes implements personajeCrudService {

    Map<String, personaje> personajes = new HashMap<>();

    @Override
    public personaje save(final personaje personaje) {
        return personajes.put(personaje.getNombre(), personaje);
    }

    @Override
    public Optional<personaje> find(final String name) {
        return ofNullable(personajes.get(name));
    }

    @Override
    public Optional<Boolean> delete(String name) {
        if(find(name).isPresent())
        {
            personajes.remove(name);
            return ofNullable(true);
        }
        else{
            return ofNullable(false);
        }
    }

    @Override
    public personaje update(String name, personaje personaje) {
        return personajes.put(personaje.getNombre(), personaje);
    }

    @Override
    public Map<String, personaje> get() {
        return personajes;
    }


    public Optional<personaje> findBypersonajename(final String name) {
        return personajes
                .values()
                .stream()
                .filter(u -> Objects.equals(name, u.getNombre()))
                .findFirst();
    }

    public personaje create(personaje item) {
        personaje copy = new personaje(
                item.getNombre(),
                item.getImagen(),
                item.getEdad(),
                item.getPeso(),
                item.getHistoria(),
                item.getPeliculasSeries()
        );
        return save(copy);
    }
}