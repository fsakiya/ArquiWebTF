package com.ecominds.tf_arquiweb.servicios;

import com.ecominds.tf_arquiweb.entidades.MaterialDeReciclaje;
import com.ecominds.tf_arquiweb.interfaces.iMaterialDeReciclajeServices;
import com.ecominds.tf_arquiweb.repositorios.MaterialDeReciclajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialDeReciclajeServicesImpl implements iMaterialDeReciclajeServices {

    @Autowired
    private MaterialDeReciclajeRepositorio materialRepositorio;

    @Override
    public List<MaterialDeReciclaje> list() {
        return materialRepositorio.findAll();
    }

    @Override
    public void insert(MaterialDeReciclaje material) {
        materialRepositorio.save(material);
    }

    @Override
    public void update(MaterialDeReciclaje material) {
        materialRepositorio.save(material);
    }

    @Override
    public void delete(int id) {
        materialRepositorio.deleteById(id);
    }

    @Override
    public MaterialDeReciclaje listId(int id) {
        return materialRepositorio.findById(id).orElse(null);
    }

    @Override
    public MaterialDeReciclaje findByNombre(String nombre) {
        return materialRepositorio.findByNombre(nombre).orElse(null);
    }

    @Override
    public List<MaterialDeReciclaje> findByNombreContaining(String nombre) {
        return materialRepositorio.findByNombreContaining(nombre);
    }

    @Override
    public List<MaterialDeReciclaje> findByDescripcion(String descripcion) {
        return materialRepositorio.findByDescripcionContaining(descripcion);
    }
}