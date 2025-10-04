package com.ecominds.tf_arquiweb.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecominds.tf_arquiweb.entidades.Evento;
import com.ecominds.tf_arquiweb.intefaces.iEventoServices;
import com.ecominds.tf_arquiweb.repositorios.EventoRepositorio;

@Service
public class EventoServicesImpl implements iEventoServices {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Override
    public List<Evento> list() {
        return eventoRepositorio.findAll();
    }

    @Override
    public void insert(Evento evento) {
        eventoRepositorio.save(evento);
    }

    @Override
    public void update(Evento evento) {
        eventoRepositorio.save(evento);
    }

    @Override
    public void delete(int id) {
        eventoRepositorio.deleteById(id);
    }

    @Override
    public Evento listId(int id) {
        return eventoRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Evento> findEventosFuturos() {
        return eventoRepositorio.findByFechaAfter(LocalDate.now());
    }

    @Override
    public List<Evento> findByOrganizador(Integer organizadorId) {
        return eventoRepositorio.findByIdOrganizadorId(organizadorId);
    }

    @Override
    public List<Evento> findByNombre(String nombre) {
        return eventoRepositorio.findByNombreContaining(nombre);
    }

    @Override
    public List<Evento> findByLugar(String lugar) {
        return eventoRepositorio.findEventosByLugar(lugar);
    }

    @Override
    public List<Evento> findEventosEnRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return eventoRepositorio.findEventosEnRangoFechas(fechaInicio, fechaFin);
    }
}