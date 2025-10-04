package com.ecominds.tf_arquiweb.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecominds.tf_arquiweb.entidades.Reciclaje;
import com.ecominds.tf_arquiweb.intefaces.iReciclajeServices;
import com.ecominds.tf_arquiweb.repositorios.ReciclajeRepositorio;

@Service
public class ReciclajeServicesImpl implements iReciclajeServices {

    @Autowired
    private ReciclajeRepositorio reciclajeRepositorio;

    @Override
    public List<Reciclaje> list() {
        return reciclajeRepositorio.findAll();
    }

    @Override
    @Transactional
    public void insert(Reciclaje reciclaje) {
        reciclajeRepositorio.save(reciclaje);
    }

    @Override
    @Transactional
    public void update(Reciclaje reciclaje) {
        reciclajeRepositorio.save(reciclaje);
    }

    @Override
    @Transactional
    public void delete(int id) {
        reciclajeRepositorio.deleteById(id);
    }

    @Override
    public Reciclaje listId(int id) {
        return reciclajeRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Reciclaje> findByUsuario(Integer usuarioId) {
        return reciclajeRepositorio.findByIdUsuarioId(usuarioId);
    }

    @Override
    public List<Reciclaje> findByMaterial(Integer materialId) {
        return reciclajeRepositorio.findByIdMaterialesId(materialId);
    }

    @Override
    public List<Reciclaje> findByPuntoAcopio(Integer acopioId) {
        return reciclajeRepositorio.findByIdAcopioId(acopioId);
    }

    @Override
    public List<Reciclaje> findByFechaRange(LocalDate fechaInicio, LocalDate fechaFin) {
        return reciclajeRepositorio.findByFechaBetween(fechaInicio, fechaFin);
    }

    @Override
    public Double getTotalPesoPorUsuario(Integer usuarioId) {
        return reciclajeRepositorio.getTotalPesoPorUsuario(usuarioId);
    }

    @Override
    public Double getTotalPesoPorMaterial(Integer materialId) {
        return reciclajeRepositorio.getTotalPesoPorMaterial(materialId);
    }

    @Override
    public List<Reciclaje> findByPesoMinimo(Double pesoMinimo) {
        return reciclajeRepositorio.findReciclajesByPesoMinimo(pesoMinimo);
    }
}