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

import com.ecominds.tf_arquiweb.dto.RecompensaDTO;
import com.ecominds.tf_arquiweb.entidades.Recompensa;
import com.ecominds.tf_arquiweb.intefaces.iRecompensaServices;

@RestController
@RequestMapping("/api/recompensas")
@CrossOrigin(origins = "*")
public class RecompensaController {

    @Autowired
    private iRecompensaServices recompensaServices;

    @Autowired
    private ModelMapper modelMapper;

    // US-005: Visualizar catálogo de recompensas
    @GetMapping
    public ResponseEntity<List<RecompensaDTO>> listar() {
        List<Recompensa> recompensas = recompensaServices.list();
        List<RecompensaDTO> recompensasDTO = recompensas.stream()
                .map(recompensa -> modelMapper.map(recompensa, RecompensaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(recompensasDTO, HttpStatus.OK);
    }

    // US-012: Crear Recompensa (Solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<RecompensaDTO> registrar(@RequestBody RecompensaDTO recompensaDTO) {
        Recompensa recompensa = modelMapper.map(recompensaDTO, Recompensa.class);
        recompensaServices.insert(recompensa);
        RecompensaDTO nuevaRecompensaDTO = modelMapper.map(recompensa, RecompensaDTO.class);
        return new ResponseEntity<>(nuevaRecompensaDTO, HttpStatus.CREATED);
    }

    // US-013: Editar Recompensa (Solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RecompensaDTO> actualizar(@PathVariable Integer id, @RequestBody RecompensaDTO recompensaDTO) {
        recompensaDTO.setId(id);
        Recompensa recompensa = modelMapper.map(recompensaDTO, Recompensa.class);
        recompensaServices.update(recompensa);
        RecompensaDTO recompensaActualizadaDTO = modelMapper.map(recompensa, RecompensaDTO.class);
        return new ResponseEntity<>(recompensaActualizadaDTO, HttpStatus.OK);
    }

    // US-014: Eliminar Recompensa (Solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        recompensaServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecompensaDTO> obtenerPorId(@PathVariable Integer id) {
        Recompensa recompensa = recompensaServices.listId(id);
        if (recompensa != null) {
            RecompensaDTO recompensaDTO = modelMapper.map(recompensa, RecompensaDTO.class);
            return new ResponseEntity<>(recompensaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar-descripcion")
    public ResponseEntity<List<RecompensaDTO>> buscarPorDescripcion(@RequestParam String descripcion) {
        List<Recompensa> recompensas = recompensaServices.findByDescripcion(descripcion);
        List<RecompensaDTO> recompensasDTO = recompensas.stream()
                .map(recompensa -> modelMapper.map(recompensa, RecompensaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(recompensasDTO, HttpStatus.OK);
    }

    @GetMapping("/buscar-requisito")
    public ResponseEntity<List<RecompensaDTO>> buscarPorRequisito(@RequestParam String requisito) {
        List<Recompensa> recompensas = recompensaServices.findByRequisito(requisito);
        List<RecompensaDTO> recompensasDTO = recompensas.stream()
                .map(recompensa -> modelMapper.map(recompensa, RecompensaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(recompensasDTO, HttpStatus.OK);
    }

    // US-018: Ordenar recompensas por descripción (A-Z)
    @GetMapping("/ordenar-menor-mayor")
    public ResponseEntity<List<RecompensaDTO>> ordenarMenorMayorCosto() {
        List<Recompensa> recompensas = recompensaServices.findAllOrderByPuntosAsc();
        List<RecompensaDTO> recompensasDTO = recompensas.stream()
                .map(recompensa -> modelMapper.map(recompensa, RecompensaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(recompensasDTO, HttpStatus.OK);
    }

    // US-019: Ordenar recompensas por descripción (Z-A)
    @GetMapping("/ordenar-mayor-menor")
    public ResponseEntity<List<RecompensaDTO>> ordenarMayorMenorCosto() {
        List<Recompensa> recompensas = recompensaServices.findAllOrderByPuntosDesc();
        List<RecompensaDTO> recompensasDTO = recompensas.stream()
                .map(recompensa -> modelMapper.map(recompensa, RecompensaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(recompensasDTO, HttpStatus.OK);
    }
}