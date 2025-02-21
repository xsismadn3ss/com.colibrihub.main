package com.colibrihub.main.controller;

import com.colibrihub.main.dto.PeliculaDTO;
import com.colibrihub.main.payload.RequestDTO;
import com.colibrihub.main.payload.RequestStringDTO;
import com.colibrihub.main.service.PeliculaService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class PeliculaController {

    private static final Logger log = LoggerFactory.getLogger(PeliculaController.class);
    private PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @PostMapping("/obtenerPelicula")
    public ResponseEntity<PeliculaDTO> obtenerPelicula(@RequestBody RequestDTO requestDTO) {
        log.info("La petición ha llegado al controlador");
        log.info("El parametro recibido es {}:", requestDTO);
        return new ResponseEntity<>(peliculaService.obtenePelicula(requestDTO), HttpStatus.OK);
    }

    @PostMapping("/pelicula/nombre")
    public ResponseEntity<List<PeliculaDTO>> buscarPeliculas(@RequestBody RequestDTO requestDTO) {
        return new ResponseEntity<>(peliculaService.buscarPeliculas(requestDTO), HttpStatus.OK);
    }

    @PostMapping("/pelicula/productora")
    public ResponseEntity<List<PeliculaDTO>> filtrarPorProductora(@RequestBody RequestStringDTO requestStringDTO) {
        return new ResponseEntity<>(peliculaService.filtrarPeliculasProductora(requestStringDTO), HttpStatus.OK);
    }

    @PostMapping("/pelicula/plataforma")
    public ResponseEntity<List<PeliculaDTO>> filtrarPorPlataforma(@RequestBody RequestStringDTO requestStringDTO) {
        return new ResponseEntity<>(peliculaService.filtrarPeliculasPlataforma(requestStringDTO), HttpStatus.OK);
    }
}
