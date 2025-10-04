package com.ecominds.tf_arquiweb.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CanjeDTO {
    private Integer id;
    private LocalDate fecha;
    private Integer costo;
    private Integer idUsuario;
    private String nombreUsuario;
    private Integer idRecompensa;
    private String descripcionRecompensa;
}
