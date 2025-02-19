package com.colibrihub.main.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer anio;
    private String productora;

}
