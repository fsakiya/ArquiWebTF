package com.ecominds.tf_arquiweb.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecominds.tf_arquiweb.entidades.PuntoDeAcopio;
import com.ecominds.tf_arquiweb.intefaces.iPuntoDeAcopioServices;
import com.ecominds.tf_arquiweb.repositorios.PuntoDeAcopioRepositorio;

@Service
public class PuntoDeAcopioServicesImpl implements iPuntoDeAcopioServices {

    @Autowired
    private PuntoDeAcopioRepositorio puntoDeAcopioRepositorio;

    @Override
    public List<PuntoDeAcopio> list() {
        return puntoDeAcopioRepositorio.findAll();
    }

    @Override
    public void insert(PuntoDeAcopio puntoDeAcopio) {
        puntoDeAcopioRepositorio.save(puntoDeAcopio);
    }

    @Override
    public void update(PuntoDeAcopio puntoDeAcopio) {
        puntoDeAcopioRepositorio.save(puntoDeAcopio);
    }

    @Override
    public void delete(int id) {
        puntoDeAcopioRepositorio.deleteById(id);
    }

    @Override
    public PuntoDeAcopio listId(int id) {
        return puntoDeAcopioRepositorio.findById(id).orElse(null);
    }

    @Override
    public PuntoDeAcopio findByNombre(String nombre) {
        return puntoDeAcopioRepositorio.findByNombre(nombre).orElse(null);
    }

    @Override
    public List<PuntoDeAcopio> findByNombreContaining(String nombre) {
        return puntoDeAcopioRepositorio.findByNombreContaining(nombre);
    }

    @Override
    public List<PuntoDeAcopio> findByUbicacion(String ubicacion) {
        return puntoDeAcopioRepositorio.findByUbicacionContaining(ubicacion);
    }
}