package com.example.disney.Genero;



import com.example.disney.Genero.GeneroCrudService;
import com.example.disney.Genero.Genero;
import com.example.disney.peliculaSerie.PeliculaSerie;
import com.example.disney.personaje.personaje;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/genre")  //todotodotodo
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public final class PublicGenerosController {

    @NonNull
    GeneroCrudService Generos;

    @GetMapping("/get")
    public Genero get(@RequestParam("Titulo") final String Titulo) {
        return  Generos.find(Titulo).get();
    }

    @DeleteMapping("/delete")
    public Boolean delete(@RequestParam("Titulo") final String Titulo) {
        return  Generos.delete(Titulo).get();
    }



    @PostMapping("/register")
    Genero register(@RequestParam("Nombre") final String Nombre,
                    @RequestParam("Imagen") final String Imagen,
                    @RequestParam("PeliculasSeries") final ArrayList<String>  PeliculasSeries) {
        Genero created= Genero
                .builder()
                .Nombre(Nombre)
                .Imagen(Imagen)
                .PeliculasSeries(PeliculasSeries)
                .build();
        Generos
                .save(created);
        return created;
    }

    @PostMapping("/update")
    Genero register1(@RequestParam("Nombre") final String Nombre,
                    @RequestParam("Imagen") final String Imagen
              ) {
        Genero created= Genero
                .builder()
                .Nombre(Nombre)
                .Imagen(Imagen)
                .PeliculasSeries(Generos.find(Nombre).get().getPeliculasSeries())
                .build();
        Generos
                .update(created.getNombre(),created);
        return created;
    }


    @DeleteMapping(value = "/{idGenero}/movies/{idMovie}")
    public Boolean deleteCharacter(@PathVariable("idGenero") String nombre,@PathVariable("idMovie") String idMovie) {
        ArrayList<String> x=  Generos.find(nombre).get()
                .getPeliculasSeries();
        int index =x.indexOf(idMovie);
        return x.remove(index).equals(idMovie);
    }

    @PostMapping(value = "/{idGenero}/movies/{idMovie}")
    public boolean addACharacter(@PathVariable("idGenero") String nombre,
                                 @PathVariable("idMovie") String idMovie) {
        ArrayList<String> x=  Generos.find(nombre).get()
                .getPeliculasSeries();
        return x.add(idMovie);

    }

}




