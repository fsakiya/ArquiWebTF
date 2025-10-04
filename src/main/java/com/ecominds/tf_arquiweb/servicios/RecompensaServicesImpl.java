package com.ecominds.tf_arquiweb.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecominds.tf_arquiweb.entidades.Recompensa;
import com.ecominds.tf_arquiweb.intefaces.iRecompensaServices;
import com.ecominds.tf_arquiweb.repositorios.RecompensaRepositorio;

@Service
public class RecompensaServicesImpl implements iRecompensaServices {

    @Autowired
    private RecompensaRepositorio recompensaRepositorio;

    @Override
    public List<Recompensa> list() {
        return recompensaRepositorio.findAll();
    }

    @Override
    public void insert(Recompensa recompensa) {
        recompensaRepositorio.save(recompensa);
    }

    @Override
    public void update(Recompensa recompensa) {
        recompensaRepositorio.save(recompensa);
    }

    @Override
    public void delete(int id) {
        recompensaRepositorio.deleteById(id);
    }

    @Override
    public Recompensa listId(int id) {
        return recompensaRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Recompensa> findByDescripcion(String descripcion) {
        return recompensaRepositorio.findByDescripcionContaining(descripcion);
    }

    @Override
    public List<Recompensa> findByRequisito(String requisito) {
        return recompensaRepositorio.findByRequisitoContaining(requisito);
    }

    // US-018: Ordenar recompensas por descripción ascendente (A-Z)
    @Override
    public List<Recompensa> findAllOrderByPuntosAsc() {
        return recompensaRepositorio.findAllByOrderByDescripcionAsc();
    }

    // US-019: Ordenar recompensas por descripción descendente (Z-A)
    @Override
    public List<Recompensa> findAllOrderByPuntosDesc() {
        return recompensaRepositorio.findAllByOrderByDescripcionDesc();
    }
}