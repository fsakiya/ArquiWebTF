package com.ecominds.tf_arquiweb.servicios;

import com.ecominds.tf_arquiweb.entidades.Canje;
import com.ecominds.tf_arquiweb.interfaces.iCanjeServices;
import com.ecominds.tf_arquiweb.repositorios.CanjeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CanjeServicesImpl implements iCanjeServices {

    @Autowired
    private CanjeRepositorio canjeRepositorio;

    @Override
    public List<Canje> list() {
        return canjeRepositorio.findAll();
    }

    @Override
    public void insert(Canje canje) {
        canjeRepositorio.save(canje);
    }

    @Override
    public void update(Canje canje) {
        canjeRepositorio.save(canje);
    }

    @Override
    public void delete(int id) {
        canjeRepositorio.deleteById(id);
    }

    @Override
    public Canje listId(int id) {
        return canjeRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Canje> findByUsuario(Integer usuarioId) {
        return canjeRepositorio.findByIdUsuarioId(usuarioId);
    }

    @Override
    public List<Canje> findByFechaRange(LocalDate fechaInicio, LocalDate fechaFin) {
        return canjeRepositorio.findByFechaBetween(fechaInicio, fechaFin);
    }

    @Override
    public Integer getTotalCostoPorUsuario(Integer usuarioId) {
        return canjeRepositorio.getTotalCostoPorUsuario(usuarioId);
    }
}