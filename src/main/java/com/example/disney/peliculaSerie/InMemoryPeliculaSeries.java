package com.example.disney.peliculaSerie;


import com.example.disney.personaje.personaje;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
 final class InMemoryPeliculaSeries implements PeliculaSerieCrudService {

    Map<String, PeliculaSerie> PeliculaSeries = new HashMap<>();

    @Override
    public PeliculaSerie save(final PeliculaSerie PeliculaSerie) {
        return PeliculaSeries.put(PeliculaSerie.getTitulo(), PeliculaSerie);
    }

    @Override
    public Optional<PeliculaSerie> find(final String name) {
        return ofNullable(PeliculaSeries.get(name));
    }

    @Override
    public Optional<Boolean> delete(String name) {
        if(find(name).isPresent())
        {
            PeliculaSeries.remove(name);
            return ofNullable(true);
        }
        else{
            return ofNullable(false);
        }
    }

    @Override
    public PeliculaSerie update(String name, PeliculaSerie PeliculaSerie) {
        return PeliculaSeries.put(PeliculaSerie.getTitulo(), PeliculaSerie);
    }


    public Optional<PeliculaSerie> findByPeliculaSeriename(final String name) {
        return PeliculaSeries
                .values()
                .stream()
                .filter(u -> Objects.equals(name, u.getTitulo()))
                .findFirst();
    }

    public PeliculaSerie create(PeliculaSerie item) {
        // To ensure the item ID remains unique,
        // use the current timestamp.
        PeliculaSerie copy = new PeliculaSerie(
                item.getTitulo(),
                item.getImagen(),
                item.getFecha(),
                item.getCalificacion(),
                item.getPersonajes()
        );
        return save(copy);
    }

    @Override
    public Map<String, PeliculaSerie> get() {
        return PeliculaSeries;
    }
}