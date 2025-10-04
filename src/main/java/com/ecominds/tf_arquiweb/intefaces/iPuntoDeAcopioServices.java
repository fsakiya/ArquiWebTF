package com.ecominds.tf_arquiweb.intefaces;

import java.util.List;

import com.ecominds.tf_arquiweb.entidades.PuntoDeAcopio;

public interface iPuntoDeAcopioServices {
    List<PuntoDeAcopio> list();
    void insert(PuntoDeAcopio puntoDeAcopio);
    void update(PuntoDeAcopio puntoDeAcopio);
    void delete(int id);
    PuntoDeAcopio listId(int id);
    PuntoDeAcopio findByNombre(String nombre);
    List<PuntoDeAcopio> findByNombreContaining(String nombre);
    List<PuntoDeAcopio> findByUbicacion(String ubicacion);
}