package com.ecominds.tf_arquiweb.servicios;

import com.ecominds.tf_arquiweb.entidades.Organizador;
import com.ecominds.tf_arquiweb.interfaces.iOrganizadorServices;
import com.ecominds.tf_arquiweb.repositorios.OrganizadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizadorServicesImpl implements iOrganizadorServices {

    @Autowired
    private OrganizadorRepositorio organizadorRepositorio;

    @Override
    public List<Organizador> list() {
        return organizadorRepositorio.findAll();
    }

    @Override
    public void insert(Organizador organizador) {
        organizadorRepositorio.save(organizador);
    }

    @Override
    public void update(Organizador organizador) {
        organizadorRepositorio.save(organizador);
    }

    @Override
    public void delete(int id) {
        organizadorRepositorio.deleteById(id);
    }

    @Override
    public Organizador listId(int id) {
        return organizadorRepositorio.findById(id).orElse(null);
    }

    @Override
    public Organizador findByNombre(String nombre) {
        return organizadorRepositorio.findByNombre(nombre).orElse(null);
    }

    @Override
    public List<Organizador> findByNombreContaining(String nombre) {
        return organizadorRepositorio.findByNombreContaining(nombre);
    }
}
