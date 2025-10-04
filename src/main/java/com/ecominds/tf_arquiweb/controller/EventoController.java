package com.ecominds.tf_arquiweb.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecominds.tf_arquiweb.dto.EventoDTO;
import com.ecominds.tf_arquiweb.entidades.Evento;
import com.ecominds.tf_arquiweb.intefaces.iEventoServices;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private iEventoServices eventoServices;

    @Autowired
    private ModelMapper modelMapper;

    // US-004: Consultar eventos ambientales
    @GetMapping
    public ResponseEntity<List<EventoDTO>> listar() {
        List<Evento> eventos = eventoServices.list();
        List<EventoDTO> eventosDTO = eventos.stream()
                .map(evento -> {
                    EventoDTO dto = modelMapper.map(evento, EventoDTO.class);
                    dto.setNombreOrganizador(evento.getIdOrganizador().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(eventosDTO, HttpStatus.OK);
    }

    // US-009: Crear Evento (Solo ORGANIZADOR)
    @PreAuthorize("hasAuthority('ORGANIZADOR')")
    @PostMapping
    public ResponseEntity<EventoDTO> registrar(@RequestBody EventoDTO eventoDTO) {
        Evento evento = modelMapper.map(eventoDTO, Evento.class);
        eventoServices.insert(evento);
        EventoDTO nuevoEventoDTO = modelMapper.map(evento, EventoDTO.class);
        return new ResponseEntity<>(nuevoEventoDTO, HttpStatus.CREATED);
    }

    // US-010: Editar Evento (Solo ORGANIZADOR)
    @PreAuthorize("hasAuthority('ORGANIZADOR')")
    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> actualizar(@PathVariable Integer id, @RequestBody EventoDTO eventoDTO) {
        eventoDTO.setId(id);
        Evento evento = modelMapper.map(eventoDTO, Evento.class);
        eventoServices.update(evento);
        EventoDTO eventoActualizadoDTO = modelMapper.map(evento, EventoDTO.class);
        return new ResponseEntity<>(eventoActualizadoDTO, HttpStatus.OK);
    }

    // US-011: Eliminar Evento (Solo ORGANIZADOR)
    @PreAuthorize("hasAuthority('ORGANIZADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        eventoServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> obtenerPorId(@PathVariable Integer id) {
        Evento evento = eventoServices.listId(id);
        if (evento != null) {
            EventoDTO eventoDTO = modelMapper.map(evento, EventoDTO.class);
            eventoDTO.setNombreOrganizador(evento.getIdOrganizador().getNombre());
            return new ResponseEntity<>(eventoDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // US-004: Consultar eventos ambientales ordenados por fecha (más próximos primero)
    @GetMapping("/futuros")
    public ResponseEntity<List<EventoDTO>> obtenerEventosFuturos() {
        List<Evento> eventos = eventoServices.findEventosFuturos();
        List<EventoDTO> eventosDTO = eventos.stream()
                .map(evento -> {
                    EventoDTO dto = modelMapper.map(evento, EventoDTO.class);
                    dto.setNombreOrganizador(evento.getIdOrganizador().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(eventosDTO, HttpStatus.OK);
    }

    @GetMapping("/organizador/{organizadorId}")
    public ResponseEntity<List<EventoDTO>> obtenerPorOrganizador(@PathVariable Integer organizadorId) {
        List<Evento> eventos = eventoServices.findByOrganizador(organizadorId);
        List<EventoDTO> eventosDTO = eventos.stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(eventosDTO, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EventoDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<Evento> eventos = eventoServices.findByNombre(nombre);
        List<EventoDTO> eventosDTO = eventos.stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(eventosDTO, HttpStatus.OK);
    }

    @GetMapping("/lugar")
    public ResponseEntity<List<EventoDTO>> buscarPorLugar(@RequestParam String lugar) {
        List<Evento> eventos = eventoServices.findByLugar(lugar);
        List<EventoDTO> eventosDTO = eventos.stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(eventosDTO, HttpStatus.OK);
    }
}