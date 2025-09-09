package com.ecominds.tf_arquiweb.interfaces;

import com.ecominds.tf_arquiweb.entidades.Recompensa;
import java.util.List;

public interface iRecompensaServices {
    List<Recompensa> list();
    void insert(Recompensa recompensa);
    void update(Recompensa recompensa);
    void delete(int id);
    Recompensa listId(int id);
    List<Recompensa> findByDescripcion(String descripcion);
    List<Recompensa> findByRequisito(String requisito);
}