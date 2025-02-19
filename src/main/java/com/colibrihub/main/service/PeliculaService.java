package com.colibrihub.main.service;

import com.colibrihub.main.dto.PeliculaDTO;
import com.colibrihub.main.payload.RequestDTO;

import java.util.List;

public interface PeliculaService {

    PeliculaDTO obtenePelicula(RequestDTO requestDTO);
    List<PeliculaDTO> buscarPeliculas(RequestDTO requestDTO);
}
