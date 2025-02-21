package com.colibrihub.main.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer anio;
    private String productora;
    private LocalDate fecha_estreno;
    private List<String> plataformas;
}
