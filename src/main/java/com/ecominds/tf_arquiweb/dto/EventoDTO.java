package com.ecominds.tf_arquiweb.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventoDTO {
    private Integer id;
    private String nombre;
    private LocalDate fecha;
    private LocalTime hora;
    private String lugar;
    private String descripcion;
    private Integer idOrganizador;
    private String nombreOrganizador;
}
