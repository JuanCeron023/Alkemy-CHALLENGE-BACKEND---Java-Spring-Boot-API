package com.example.disney.Genero;


import com.example.disney.personaje.personaje;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
 final class InMemoryGeneros implements GeneroCrudService {

    Map<String, Genero> Generos = new HashMap<>();

    @Override
    public Genero save(final Genero Genero) {
        return Generos.put(Genero.getNombre(), Genero);
    }

    @Override
    public Optional<Genero> find(final String name) {
        return ofNullable(Generos.get(name));
    }

    @Override
    public Optional<Boolean> delete(String name) {
        if(find(name).isPresent())
        {
            Generos.remove(name);
            return ofNullable(true);
        }
        else{
            return ofNullable(false);
        }
    }

    @Override
    public Genero update(String name, Genero Genero) {
        return Generos.put(Genero.getNombre(), Genero);
    }


    public Optional<Genero> findByGeneroname(final String name) {
        return Generos
                .values()
                .stream()
                .filter(u -> Objects.equals(name, u.getNombre()))
                .findFirst();
    }

    public Genero create(Genero item) {
        // To ensure the item ID remains unique,
        // use the current timestamp.
        Genero copy = new Genero(
                item.getNombre(),
                item.getImagen(),
                item.getPeliculasSeries()
        );
        return save(copy);
    }

    @Override
    public Map<String, Genero> get() {
        return Generos;
    }
}