package com.ecominds.tf_arquiweb.controller;

import com.ecominds.tf_arquiweb.dto.RecompensaDTO;
import com.ecominds.tf_arquiweb.entidades.Recompensa;
import com.ecominds.tf_arquiweb.interfaces.iRecompensaServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recompensas")
@CrossOrigin(origins = "*")
public class RecompensaController {

    @Autowired
    private iRecompensaServices recompensaServices;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RecompensaDTO>> listar() {
        List<Recompensa> recompensas = recompensaServices.list();
        List<RecompensaDTO> recompensasDTO = recompensas.stream()
                .map(recompensa -> modelMapper.map(recompensa, RecompensaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(recompensasDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecompensaDTO> registrar(@RequestBody RecompensaDTO recompensaDTO) {
        Recompensa recompensa = modelMapper.map(recompensaDTO, Recompensa.class);
        recompensaServices.insert(recompensa);
        RecompensaDTO nuevaRecompensaDTO = modelMapper.map(recompensa, RecompensaDTO.class);
        return new ResponseEntity<>(nuevaRecompensaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecompensaDTO> actualizar(@PathVariable Integer id, @RequestBody RecompensaDTO recompensaDTO) {
        recompensaDTO.setId(id);
        Recompensa recompensa = modelMapper.map(recompensaDTO, Recompensa.class);
        recompensaServices.update(recompensa);
        RecompensaDTO recompensaActualizadaDTO = modelMapper.map(recompensa, RecompensaDTO.class);
        return new ResponseEntity<>(recompensaActualizadaDTO, HttpStatus.OK);
    }

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
}