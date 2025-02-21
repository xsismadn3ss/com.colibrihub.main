package com.colibrihub.main.service.impl;

import com.colibrihub.main.dto.PeliculaDTO;
import com.colibrihub.main.payload.RequestDTO;
import com.colibrihub.main.payload.RequestStringDTO;
import com.colibrihub.main.service.PeliculaService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return peliculas;
    }

    @Override
    public List<PeliculaDTO> filtrarPeliculasProductora(RequestStringDTO requestStringDTO) {
        log.info("El servicio está filtrando por productora");
        List<PeliculaDTO> misPeliculas = generarDatosDummy();
        return filtrarPorProductora(misPeliculas, requestStringDTO.getKeyword());
    }

    @Override
    public List<PeliculaDTO> filtrarPeliculasPlataforma(RequestStringDTO requestStringDTO) {
        log.info("El servicio está filtrando por plataforma");
        List<PeliculaDTO> misPeliculas = generarDatosDummy();
        return filtrarPorPlataforma(misPeliculas, requestStringDTO.getKeyword());
    }

    public List<PeliculaDTO> filtrarPorProductora(List<PeliculaDTO> peliculas, String productora) {
        return peliculas.stream()
                .filter(pelicula -> pelicula.getProductora().toLowerCase().contains(productora.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<PeliculaDTO> filtrarPorPlataforma(List<PeliculaDTO> peliculas, String plataforma) {
        return peliculas.stream()
                .filter(pelicula -> pelicula.getPlataformas().stream()
                        .anyMatch(p -> p.toLowerCase().contains(plataforma.toLowerCase())))
                .collect(Collectors.toList());
    }


    public static Optional<PeliculaDTO> buscarPorId(List<PeliculaDTO> peliculas, Integer id) {
        return peliculas.stream().filter(pelicula -> pelicula.getId().equals(id)).findFirst();
    }

    public static Optional<PeliculaDTO> buscarPorNombre(List<PeliculaDTO> peliculas, String nombre) {
        return peliculas.stream().filter(pelicula -> pelicula.getNombre().equalsIgnoreCase(nombre)).findFirst();
    }

    public List<PeliculaDTO> filtrarPorNombreParcial(List<PeliculaDTO> peliculas, String fragmento) {
        return peliculas.stream()
                .filter(pelicula -> pelicula.getNombre().toLowerCase().contains(fragmento.toLowerCase()))
                .collect(Collectors.toList());
    }


    public static List<PeliculaDTO> generarDatosDummy() {
        List<String> plataformasAll = new ArrayList<>();
        plataformasAll.add("Netflix");
        plataformasAll.add("Prime Video");
        plataformasAll.add("HBO MAX");
        plataformasAll.add("Hulu");
        plataformasAll.add("Apple TV");

        List<String> plataformasPremium = new ArrayList<>();
        plataformasPremium.add(plataformasAll.get(0)); // Netflix
        plataformasPremium.add(plataformasAll.get(1)); // Prime Video
        plataformasPremium.add(plataformasAll.get(4)); // Apple TV

        List<String> plataformasLite = new ArrayList<>();
        plataformasLite.add(plataformasAll.get(3)); // Hulu
        plataformasLite.add(plataformasAll.get(2)); // HBO MAX

        List<PeliculaDTO> peliculas = new ArrayList<>();

        peliculas.add(new PeliculaDTO(1, "Inception", "Un ladrón roba secretos a través de los sueños.",
                2010, "Warner Bros.", LocalDate.of(2010, 7, 16), plataformasPremium));

        peliculas.add(new PeliculaDTO(2, "Interstellar", "Exploración espacial en busca de un nuevo hogar.",
                2014, "Paramount Pictures", LocalDate.of(2014, 11, 7), plataformasPremium));

        peliculas.add(new PeliculaDTO(3, "The Dark Knight", "Batman enfrenta al Joker en Gotham.",
                2008, "Warner Bros.", LocalDate.of(2008, 7, 18), plataformasLite));

        peliculas.add(new PeliculaDTO(4, "Avatar", "Un soldado en un mundo alienígena.",
                2009, "20th Century Fox", LocalDate.of(2009, 12, 18), plataformasLite));
        peliculas.add(new PeliculaDTO(5, "Titanic", "Historia de amor en el trágico barco.",
                1997, "Paramount Pictures", LocalDate.of(1997, 12, 19), plataformasPremium));

        peliculas.add(new PeliculaDTO(6, "The Matrix", "Un hacker descubre la verdad sobre su mundo.",
                1999, "Warner Bros.", LocalDate.of(1999, 3, 31), plataformasPremium));

        peliculas.add(new PeliculaDTO(7, "Gladiator", "Un general romano busca venganza.",
                2000, "DreamWorks", LocalDate.of(2000, 5, 5), plataformasLite));

        peliculas.add(new PeliculaDTO(8, "Joker", "La transformación de un hombre en el villano.",
                2019, "Warner Bros.", LocalDate.of(2019, 10, 4), plataformasLite));

        peliculas.add(new PeliculaDTO(9, "Forrest Gump", "Un hombre con gran corazón vive eventos históricos.",
                1994, "Paramount Pictures", LocalDate.of(1994, 7, 6), plataformasPremium));

        return peliculas;
    }
}
