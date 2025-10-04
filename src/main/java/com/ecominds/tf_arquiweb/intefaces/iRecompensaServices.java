package com.ecominds.tf_arquiweb.intefaces;

import java.util.List;

import com.ecominds.tf_arquiweb.entidades.Recompensa;

public interface iRecompensaServices {
    List<Recompensa> list();
    void insert(Recompensa recompensa);
    void update(Recompensa recompensa);
    void delete(int id);
    Recompensa listId(int id);
    List<Recompensa> findByDescripcion(String descripcion);
    List<Recompensa> findByRequisito(String requisito);
    // US-018 y US-019: Ordenamiento por descripción
    List<Recompensa> findAllOrderByPuntosAsc();  // Mantener nombres de métodos para compatibilidad
    List<Recompensa> findAllOrderByPuntosDesc();
}