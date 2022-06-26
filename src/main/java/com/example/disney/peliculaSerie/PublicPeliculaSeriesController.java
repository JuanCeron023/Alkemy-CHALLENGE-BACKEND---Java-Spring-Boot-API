package com.example.disney.peliculaSerie;



import com.example.disney.Genero.Genero;
import com.example.disney.Genero.GeneroCrudService;
import com.example.disney.personaje.filtroPersonaje;
import com.example.disney.personaje.personaje;
import com.example.disney.personaje.personajeCrudService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/movies")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public final class PublicPeliculaSeriesController {

    @NonNull
    PeliculaSerieCrudService PeliculaSeries;
    @NonNull
    personajeCrudService personajes;

    @NonNull
    GeneroCrudService Generos;


    @GetMapping("/get")
    public PeliculaSerie get(@RequestParam("Titulo") final String Titulo) {
        return  PeliculaSeries.find(Titulo).get();
    }


    @GetMapping(value="/",params = {"name","genre","order"})
    public List<filtroMovie> get1(@RequestParam("name") final String name,
                                      @RequestParam("genre") final Integer genre,
                                      @RequestParam("order") final String order) {
        List<filtroMovie> lista = new ArrayList<filtroMovie>();
        if(name!=null)
        {
            if((PeliculaSeries.find(name).isPresent())) {
                filtroMovie temporal = new filtroMovie();
                temporal.setTitulo(PeliculaSeries.find(name).get().getTitulo());
                temporal.setImagen(PeliculaSeries.find(name).get().getImagen());
                temporal.setFecha(PeliculaSeries.find(name).get().getFecha());
                lista.add(temporal);
            }
            return  lista;

        }
        else if(genre!=null)
        {
            ArrayList<String> x=Generos.get().get(genre).getPeliculasSeries();
            x.forEach
                    (k -> {

                            filtroMovie temporal = new filtroMovie();
                            temporal.setTitulo(PeliculaSeries.find(k).get().getTitulo());
                            temporal.setImagen(PeliculaSeries.find(k).get().getImagen());
                            temporal.setFecha(PeliculaSeries.find(k).get().getFecha());
                            lista.add(temporal);
                    });
            return lista;
        }
        else if(order!=null)
        {
            PeliculaSeries.get().entrySet().stream().forEach
                    (k -> {

                        filtroMovie temporal = new filtroMovie();
                        temporal.setTitulo(PeliculaSeries.find(k.getValue().getTitulo()).get().getTitulo());
                        temporal.setImagen(PeliculaSeries.find(k.getValue().getTitulo()).get().getImagen());
                        temporal.setFecha(PeliculaSeries.find(k.getValue().getTitulo()).get().getFecha());
                        lista.add(temporal);

                    });
         if(order.equals("ASC")) {
             lista.sort((a1, a2) -> {
                 try {
                     Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(a1.getFecha());
                     Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(a2.getFecha());
                     return date1.compareTo(date2);
                 } catch (ParseException e) {
                     throw new RuntimeException(e);
                 }
             });
         }
            else if(order.equals("DESC")) {
                lista.sort((a1, a2) -> {
                    try {
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(a1.getFecha());
                        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(a2.getFecha());
                        return date2.compareTo(date1);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            return lista;
        }

        PeliculaSeries.get().entrySet().forEach
                (k -> {
                    filtroMovie temporal = new filtroMovie();
                    temporal.setTitulo(PeliculaSeries.find(k.getValue().getTitulo()).get().getTitulo());
                    temporal.setImagen(PeliculaSeries.find(k.getValue().getTitulo()).get().getImagen());
                    temporal.setFecha(PeliculaSeries.find(k.getValue().getTitulo()).get().getFecha());
                    lista.add(temporal);

                });
        return lista;
    }



    @DeleteMapping("/delete")
    public Boolean delete(@RequestParam("Titulo") final String Titulo) {
        return  PeliculaSeries.delete(Titulo).get();
    }



    @PostMapping("/register")
        public PeliculaSerie register3(@RequestBody IdWrapper productsWrapper) {

        ArrayList<String> x= new ArrayList<String>();
        for (personaje1 c:productsWrapper.getPersonajes()) {
            x.add(c.getNombre());
            personaje created1= personaje
                    .builder()
                    .Nombre(c.getNombre())
                    .Imagen(c.getImagen())
                    .Edad(c.getEdad())
                    .Peso(c.getPeso())
                    .Historia(c.getHistoria())
                    .PeliculasSeries(c.getPeliculasSeries())
                    .build();
            personajes
                    .save(created1);
        }

        
        PeliculaSerie created= PeliculaSerie
                .builder()
                .Titulo(productsWrapper.getTitulo())
                .Imagen(productsWrapper.getImagen())
                .Fecha(productsWrapper.getFecha())
                .Calificacion(productsWrapper.getCalificacion())
                .Personajes(x)
                .build();
        PeliculaSeries
                .save(created);
        return created;
        }


    @PostMapping("/update")
    PeliculaSerie update(@RequestParam("Titulo") final String Titulo,
                           @RequestParam("Imagen") final String Imagen,
                           @RequestParam("Fecha") final String Fecha,
                           @RequestParam("Calificacion") final double Calificacion) {


        PeliculaSerie created= PeliculaSerie
                .builder()
                .Titulo(Titulo)
                .Imagen(Imagen)
                .Fecha(Fecha)
                .Calificacion(Calificacion)
                .Personajes(PeliculaSeries.find(Titulo).get().getPersonajes())
                .build();
        PeliculaSeries
                .update(created.getTitulo(),created);
        return created;
    }

    @DeleteMapping(value = "/{idMovie}/characters/{idCharacter}")
    public Boolean deleteCharacter(@PathVariable("idMovie") String nombre,@PathVariable("idCharacter") String idCharacter) {
        ArrayList<String> x=  PeliculaSeries.find(nombre).get()
                .getPersonajes();
        int index =x.indexOf(idCharacter);

        ArrayList<String> x1=  personajes.find(idCharacter).get().getPeliculasSeries();
        x1.remove(x1.indexOf(idCharacter));
        return x.remove(index).equals(idCharacter);
    }

    @PostMapping(value = "/{idMovie}/characters/{idCharacter}")
    public boolean addACharacter(@PathVariable("idMovie") String nombre,
                           @PathVariable("idCharacter") String idCharacter,
                           @RequestBody personaje1 personaje1) {
        ArrayList<String> x=  PeliculaSeries.find(nombre).get()
                .getPersonajes();
        personaje created1= personaje
                .builder()
                .Nombre(personaje1.getNombre())
                .Imagen(personaje1.getImagen())
                .Edad(personaje1.getEdad())
                .Peso(personaje1.getPeso())
                .Historia(personaje1.getHistoria())
                .PeliculasSeries(personaje1.getPeliculasSeries())
                .build();
        personajes
                .save(created1);


        return x.add(personaje1.getNombre());

    }
}




