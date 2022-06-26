package com.example.disney.personaje;



import com.example.disney.peliculaSerie.PeliculaSerie;
import com.example.disney.peliculaSerie.PeliculaSerieCrudService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/characters")

@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
final class PublicpersonajesController {

    @NonNull
    personajeCrudService personajes;
    @NonNull
    PeliculaSerieCrudService PeliculaSeries;

    @GetMapping(value="/",params = {"name","age","movies","weight"})
    public List<filtroPersonaje> get1(@RequestParam("name") final String name,
                               @RequestParam("age") final Integer age,
                               @RequestParam("movies") final String movies,
                                @RequestParam("weight") final Double weight) {
        List<filtroPersonaje> lista = new ArrayList<filtroPersonaje>();
        if(name!=null)
        {
            if((personajes.find(name).isPresent())) {
            filtroPersonaje temporal = new filtroPersonaje();
            temporal.setNombre(personajes.find(name).get().getNombre());
            temporal.setImagen(personajes.find(name).get().getImagen());
            lista.add(temporal);
        }
            return  lista;

        }
        else if(age!=null)
        {
            personajes.get().entrySet().stream().forEach
                            (k -> {
                                if(k.getValue().getEdad() == age)
                                {
                                    filtroPersonaje temporal = new filtroPersonaje();
                                    temporal.setNombre(k.getValue().getNombre());
                                    temporal.setImagen(k.getValue().getImagen());
                                    lista.add(temporal);
                                }
                            });
            return lista;
        }
        else if(movies!=null)
        {
            personajes.get().entrySet().stream().forEach
                    (k -> {
                        if(k.getValue().getPeliculasSeries().contains(movies))
                        {
                            filtroPersonaje temporal = new filtroPersonaje();
                            temporal.setNombre(k.getValue().getNombre());
                            temporal.setImagen(k.getValue().getImagen());
                            lista.add(temporal);
                        }
                    });
            return lista;
        }
        else if(weight!=null)
        {
            personajes.get().entrySet().stream().forEach
                    (k -> {
                        if(k.getValue().getPeso() == weight)
                        {
                            filtroPersonaje temporal = new filtroPersonaje();
                            temporal.setNombre(k.getValue().getNombre());
                            temporal.setImagen(k.getValue().getImagen());
                            lista.add(temporal);
                        }
                    });
            return lista;
        }
        personajes.get().entrySet().stream().forEach
                (k -> {

                    filtroPersonaje temporal = new filtroPersonaje();
                    temporal.setNombre(k.getValue().getNombre());
                    temporal.setImagen(k.getValue().getImagen());
                    lista.add(temporal);

                });
        return lista;
    }

    @GetMapping("/get")
    public personaje get(@RequestParam("nombre") final String nombre) {
        return  personajes.find(nombre).get();
    }


    @DeleteMapping("/delete")
    public Boolean delete(@RequestParam("Titulo") final String Titulo) {
        return  personajes.delete(Titulo).get();
    }


    @PostMapping("/register")
    personaje register(@RequestParam("Nombre") final String Nombre,
                       @RequestParam("Imagen") final String Imagen,
                       @RequestParam("Edad") final Integer Edad,
                       @RequestParam("Peso") final double Peso,
                       @RequestParam("Historia") final String Historia,
                       @RequestParam("PeliculasSeries") final ArrayList<String> PeliculasSeries) {
        personaje created= personaje
                .builder()
                .Nombre(Nombre)
                .Imagen(Imagen)
                .Edad(Edad)
                .Peso(Peso)
                .Historia(Historia)
                .PeliculasSeries(PeliculasSeries)
                .build();
        personajes
                .save(created);
        return created;
    }


    @PostMapping("/update")
    personaje update(@RequestParam("Nombre") final String Nombre,
                         @RequestParam("Imagen") final String Imagen,
                         @RequestParam("Edad") final Integer Edad,
                         @RequestParam("Peso") final double Peso,
                         @RequestParam("Historia") final String Historia
                        ) {
        personaje created= personaje
                .builder()
                .Nombre(Nombre)
                .Imagen(Imagen)
                .Edad(Edad)
                .Peso(Peso)
                .Historia(Historia)
                .PeliculasSeries(personajes.find(Nombre).get().getPeliculasSeries())
                .build();
        personajes
                .update(created.getNombre(),created);
        return created;
    }

    @DeleteMapping(value = "/{idCharacter}/movies/{idPelicula}")
    public Boolean deleteMovie(@PathVariable("idCharacter") String nombre,@PathVariable("idPelicula") String idPelicula) {
        ArrayList<String> x=  PeliculaSeries.find(idPelicula).get()
                .getPersonajes();
        int index =x.indexOf(idPelicula);
        x.remove(index);

        ArrayList<String> x1=  personajes.find(nombre).get().getPeliculasSeries();
        return x1.remove(x1.indexOf(idPelicula)).equals(idPelicula);

    }

    @PostMapping(value = "/{idCharacter}/movies/{idPelicula}")
    public boolean addAMovie(@PathVariable("idCharacter") String nombre,
                                 @PathVariable("idPelicula") String idPelicula) {
        ArrayList<String> x=  personajes.find(nombre).get()
                .getPeliculasSeries();

        return x.add(idPelicula);

    }

}




