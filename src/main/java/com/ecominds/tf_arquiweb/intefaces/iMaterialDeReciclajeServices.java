package com.ecominds.tf_arquiweb.interfaces;

import com.ecominds.tf_arquiweb.entidades.MaterialDeReciclaje;
import java.util.List;

public interface iMaterialDeReciclajeServices {
    List<MaterialDeReciclaje> list();
    void insert(MaterialDeReciclaje material);
    void update(MaterialDeReciclaje material);
    void delete(int id);
    MaterialDeReciclaje listId(int id);
    MaterialDeReciclaje findByNombre(String nombre);
    List<MaterialDeReciclaje> findByNombreContaining(String nombre);
    List<MaterialDeReciclaje> findByDescripcion(String descripcion);
}
