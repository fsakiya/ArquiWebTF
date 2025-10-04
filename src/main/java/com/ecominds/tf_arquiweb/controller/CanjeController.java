package com.ecominds.tf_arquiweb.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.ecominds.tf_arquiweb.dto.CanjeDTO;
import com.ecominds.tf_arquiweb.entidades.Canje;
import com.ecominds.tf_arquiweb.intefaces.iCanjeServices;

@RestController
@RequestMapping("/api/canjes")
@CrossOrigin(origins = "*")
public class CanjeController {

    @Autowired
    private iCanjeServices canjeServices;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CanjeDTO>> listar() {
        List<Canje> canjes = canjeServices.list();
        List<CanjeDTO> canjesDTO = canjes.stream()
                .map(canje -> {
                    CanjeDTO dto = modelMapper.map(canje, CanjeDTO.class);
                    dto.setNombreUsuario(canje.getIdUsuario().getNombre() + " " + canje.getIdUsuario().getApellido());
                    dto.setDescripcionRecompensa(canje.getIdRecompensa().getDescripcion());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(canjesDTO, HttpStatus.OK);
    }

    // US-006: Canjear recompensas con puntos
    @PostMapping
    public ResponseEntity<CanjeDTO> registrar(@RequestBody CanjeDTO canjeDTO) {
        Canje canje = modelMapper.map(canjeDTO, Canje.class);
        canjeServices.insert(canje);
        CanjeDTO nuevoCanjeDTO = modelMapper.map(canje, CanjeDTO.class);
        return new ResponseEntity<>(nuevoCanjeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CanjeDTO> actualizar(@PathVariable Integer id, @RequestBody CanjeDTO canjeDTO) {
        canjeDTO.setId(id);
        Canje canje = modelMapper.map(canjeDTO, Canje.class);
        canjeServices.update(canje);
        CanjeDTO canjeActualizadoDTO = modelMapper.map(canje, CanjeDTO.class);
        return new ResponseEntity<>(canjeActualizadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        canjeServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CanjeDTO> obtenerPorId(@PathVariable Integer id) {
        Canje canje = canjeServices.listId(id);
        if (canje != null) {
            CanjeDTO canjeDTO = modelMapper.map(canje, CanjeDTO.class);
            canjeDTO.setNombreUsuario(canje.getIdUsuario().getNombre() + " " + canje.getIdUsuario().getApellido());
            canjeDTO.setDescripcionRecompensa(canje.getIdRecompensa().getDescripcion());
            return new ResponseEntity<>(canjeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CanjeDTO>> obtenerPorUsuario(@PathVariable Integer usuarioId) {
        List<Canje> canjes = canjeServices.findByUsuario(usuarioId);
        List<CanjeDTO> canjesDTO = canjes.stream()
                .map(canje -> {
                    CanjeDTO dto = modelMapper.map(canje, CanjeDTO.class);
                    dto.setNombreUsuario(canje.getIdUsuario().getNombre() + " " + canje.getIdUsuario().getApellido());
                    dto.setDescripcionRecompensa(canje.getIdRecompensa().getDescripcion());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(canjesDTO, HttpStatus.OK);
    }

    @GetMapping("/fecha-rango")
    public ResponseEntity<List<CanjeDTO>> obtenerPorRangoFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        List<Canje> canjes = canjeServices.findByFechaRange(fechaInicio, fechaFin);
        List<CanjeDTO> canjesDTO = canjes.stream()
                .map(canje -> modelMapper.map(canje, CanjeDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(canjesDTO, HttpStatus.OK);
    }

    @GetMapping("/total-costo/usuario/{usuarioId}")
    public ResponseEntity<Integer> obtenerTotalCostoPorUsuario(@PathVariable Integer usuarioId) {
        Integer totalCosto = canjeServices.getTotalCostoPorUsuario(usuarioId);
        return new ResponseEntity<>(totalCosto != null ? totalCosto : 0, HttpStatus.OK);
    }
}