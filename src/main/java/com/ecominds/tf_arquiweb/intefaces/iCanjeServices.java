package com.ecominds.tf_arquiweb.interfaces;

import com.ecominds.tf_arquiweb.entidades.Canje;
import java.time.LocalDate;
import java.util.List;

public interface iCanjeServices {
    List<Canje> list();
    void insert(Canje canje);
    void update(Canje canje);
    void delete(int id);
    Canje listId(int id);
    List<Canje> findByUsuario(Integer usuarioId);
    List<Canje> findByFechaRange(LocalDate fechaInicio, LocalDate fechaFin);
    Integer getTotalCostoPorUsuario(Integer usuarioId);
}

// ========