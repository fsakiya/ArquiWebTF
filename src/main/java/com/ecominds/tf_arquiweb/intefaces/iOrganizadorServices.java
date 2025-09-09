package com.ecominds.tf_arquiweb.interfaces;

import com.ecominds.tf_arquiweb.entidades.Organizador;
import java.util.List;

public interface iOrganizadorServices {
    List<Organizador> list();
    void insert(Organizador organizador);
    void update(Organizador organizador);
    void delete(int id);
    Organizador listId(int id);
    Organizador findByNombre(String nombre);
    List<Organizador> findByNombreContaining(String nombre);
}

// ====