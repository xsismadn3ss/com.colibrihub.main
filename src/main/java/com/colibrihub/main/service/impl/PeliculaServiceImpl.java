package com.colibrihub.main.service.impl;

import com.colibrihub.main.dto.PeliculaDTO;
import com.colibrihub.main.payload.RequestDTO;
import com.colibrihub.main.service.PeliculaService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PeliculaServiceImpl implements PeliculaService {
    private static final Logger log = LoggerFactory.getLogger(PeliculaServiceImpl.class);

    @Override
    public PeliculaDTO obtenePelicula(RequestDTO requestDTO) {
        log.info("El servicio se esta ejecutando");
        List<PeliculaDTO> misPeliculas = generarDatosDummy();
        Optional<PeliculaDTO> peliculaDTO = buscarPorNombre(misPeliculas, requestDTO.getNombre());
        return peliculaDTO.orElse(null);
    }

    @Override
    public List<PeliculaDTO> buscarPeliculas(RequestDTO requestDTO) {
        log.info("El servicio se esta ejecutando");
        List<PeliculaDTO> misPeliculas = generarDatosDummy();
        List<PeliculaDTO> peliculas = filtrarPorNombreParcial(misPeliculas, requestDTO.getNombre());
        return  peliculas;
    }


    public static Optional<PeliculaDTO> buscarPorId(List<PeliculaDTO> peliculas, Integer id) {
        return peliculas.stream().filter(pelicula -> pelicula.getId().equals(id)).findFirst();
    }

    public static Optional<PeliculaDTO> buscarPorNombre(List<PeliculaDTO> peliculas, String nombre) {
        return peliculas.stream().filter(pelicula -> pelicula.getNombre().equalsIgnoreCase(nombre)).findFirst();
    }

    // Método para filtrar las películas que contienen la cadena de búsqueda en el nombre
    public static List<PeliculaDTO> filtrarPorNombreParcial(List<PeliculaDTO> peliculas, String fragmento) {
        return peliculas.stream()
                .filter(pelicula -> pelicula.getNombre().toLowerCase().contains(fragmento.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<PeliculaDTO> generarDatosDummy() {
        List<PeliculaDTO> peliculas = new ArrayList<>();
        peliculas.add(new PeliculaDTO(1, "Titanic", "Un romance épico en un desastre natural.", 1997, "Paramount Pictures"));
        peliculas.add(new PeliculaDTO(2, "Avatar", "Una historia sobre la exploración de un mundo alienígena.", 2009, "20th Century Fox"));
        peliculas.add(new PeliculaDTO(3, "Avengers: Endgame", "Los Vengadores luchan para salvar el universo.", 2019, "Marvel Studios"));
        peliculas.add(new PeliculaDTO(4, "The Dark Knight", "Batman enfrenta a un villano psicópata que amenaza Gotham.", 2008, "Warner Bros"));
        peliculas.add(new PeliculaDTO(5, "Inception", "Un ladrón experimentado en la invasión de sueños es contratado para una tarea arriesgada.", 2010, "Warner Bros"));

        return peliculas;
    }
}
