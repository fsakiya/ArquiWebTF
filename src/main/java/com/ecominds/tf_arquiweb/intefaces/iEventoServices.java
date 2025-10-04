package com.ecominds.tf_arquiweb.intefaces;

import java.time.LocalDate;
import java.util.List;

import com.ecominds.tf_arquiweb.entidades.Evento;

public interface iEventoServices {
    List<Evento> list();
    void insert(Evento evento);
    void update(Evento evento);
    void delete(int id);
    Evento listId(int id);
    List<Evento> findEventosFuturos();
    List<Evento> findByOrganizador(Integer organizadorId);
    List<Evento> findByNombre(String nombre);
    List<Evento> findByLugar(String lugar);
    List<Evento> findEventosEnRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
}