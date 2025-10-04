package com.ecominds.tf_arquiweb.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private Double pesoRecolectado;
    private Integer puntos;
}