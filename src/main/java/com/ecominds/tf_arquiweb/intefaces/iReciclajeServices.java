package com.ecominds.tf_arquiweb.intefaces;

import java.time.LocalDate;
import java.util.List;

import com.ecominds.tf_arquiweb.entidades.Reciclaje;

public interface iReciclajeServices {
    List<Reciclaje> list();
    void insert(Reciclaje reciclaje);
    void update(Reciclaje reciclaje);
    void delete(int id);
    Reciclaje listId(int id);
    List<Reciclaje> findByUsuario(Integer usuarioId);
    List<Reciclaje> findByMaterial(Integer materialId);
    List<Reciclaje> findByPuntoAcopio(Integer acopioId);
    List<Reciclaje> findByFechaRange(LocalDate fechaInicio, LocalDate fechaFin);
    Double getTotalPesoPorUsuario(Integer usuarioId);
    Double getTotalPesoPorMaterial(Integer materialId);
    List<Reciclaje> findByPesoMinimo(Double pesoMinimo);
}