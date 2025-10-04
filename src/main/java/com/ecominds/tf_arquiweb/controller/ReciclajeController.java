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

import com.ecominds.tf_arquiweb.dto.ReciclajeDTO;
import com.ecominds.tf_arquiweb.entidades.Reciclaje;
import com.ecominds.tf_arquiweb.intefaces.iReciclajeServices;

@RestController
@RequestMapping("/api/reciclajes")
@CrossOrigin(origins = "*")
public class ReciclajeController {

    @Autowired
    private iReciclajeServices reciclajeServices;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ReciclajeDTO>> listar() {
        List<Reciclaje> reciclajes = reciclajeServices.list();
        List<ReciclajeDTO> reciclajesDTO = reciclajes.stream()
                .map(reciclaje -> {
                    ReciclajeDTO dto = modelMapper.map(reciclaje, ReciclajeDTO.class);
                    dto.setNombreUsuario(reciclaje.getIdUsuario().getNombre() + " " + reciclaje.getIdUsuario().getApellido());
                    dto.setNombreMaterial(reciclaje.getIdMateriales().getNombre());
                    dto.setNombreAcopio(reciclaje.getIdAcopio().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(reciclajesDTO, HttpStatus.OK);
    }

    // US-002: Registrar recolección de residuos
    @PostMapping
    public ResponseEntity<ReciclajeDTO> registrar(@RequestBody ReciclajeDTO reciclajeDTO) {
        Reciclaje reciclaje = modelMapper.map(reciclajeDTO, Reciclaje.class);
        reciclajeServices.insert(reciclaje);
        ReciclajeDTO nuevoReciclajeDTO = modelMapper.map(reciclaje, ReciclajeDTO.class);
        return new ResponseEntity<>(nuevoReciclajeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReciclajeDTO> actualizar(@PathVariable Integer id, @RequestBody ReciclajeDTO reciclajeDTO) {
        reciclajeDTO.setId(id);
        Reciclaje reciclaje = modelMapper.map(reciclajeDTO, Reciclaje.class);
        reciclajeServices.update(reciclaje);
        ReciclajeDTO reciclajeActualizadoDTO = modelMapper.map(reciclaje, ReciclajeDTO.class);
        return new ResponseEntity<>(reciclajeActualizadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        reciclajeServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReciclajeDTO> obtenerPorId(@PathVariable Integer id) {
        Reciclaje reciclaje = reciclajeServices.listId(id);
        if (reciclaje != null) {
            ReciclajeDTO reciclajeDTO = modelMapper.map(reciclaje, ReciclajeDTO.class);
            reciclajeDTO.setNombreUsuario(reciclaje.getIdUsuario().getNombre() + " " + reciclaje.getIdUsuario().getApellido());
            reciclajeDTO.setNombreMaterial(reciclaje.getIdMateriales().getNombre());
            reciclajeDTO.setNombreAcopio(reciclaje.getIdAcopio().getNombre());
            return new ResponseEntity<>(reciclajeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ReciclajeDTO>> obtenerPorUsuario(@PathVariable Integer usuarioId) {
        List<Reciclaje> reciclajes = reciclajeServices.findByUsuario(usuarioId);
        List<ReciclajeDTO> reciclajesDTO = reciclajes.stream()
                .map(reciclaje -> {
                    ReciclajeDTO dto = modelMapper.map(reciclaje, ReciclajeDTO.class);
                    dto.setNombreUsuario(reciclaje.getIdUsuario().getNombre() + " " + reciclaje.getIdUsuario().getApellido());
                    dto.setNombreMaterial(reciclaje.getIdMateriales().getNombre());
                    dto.setNombreAcopio(reciclaje.getIdAcopio().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(reciclajesDTO, HttpStatus.OK);
    }

    @GetMapping("/material/{materialId}")
    public ResponseEntity<List<ReciclajeDTO>> obtenerPorMaterial(@PathVariable Integer materialId) {
        List<Reciclaje> reciclajes = reciclajeServices.findByMaterial(materialId);
        List<ReciclajeDTO> reciclajesDTO = reciclajes.stream()
                .map(reciclaje -> {
                    ReciclajeDTO dto = modelMapper.map(reciclaje, ReciclajeDTO.class);
                    dto.setNombreUsuario(reciclaje.getIdUsuario().getNombre() + " " + reciclaje.getIdUsuario().getApellido());
                    dto.setNombreMaterial(reciclaje.getIdMateriales().getNombre());
                    dto.setNombreAcopio(reciclaje.getIdAcopio().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(reciclajesDTO, HttpStatus.OK);
    }

    @GetMapping("/punto-acopio/{acopioId}")
    public ResponseEntity<List<ReciclajeDTO>> obtenerPorPuntoAcopio(@PathVariable Integer acopioId) {
        List<Reciclaje> reciclajes = reciclajeServices.findByPuntoAcopio(acopioId);
        List<ReciclajeDTO> reciclajesDTO = reciclajes.stream()
                .map(reciclaje -> {
                    ReciclajeDTO dto = modelMapper.map(reciclaje, ReciclajeDTO.class);
                    dto.setNombreUsuario(reciclaje.getIdUsuario().getNombre() + " " + reciclaje.getIdUsuario().getApellido());
                    dto.setNombreMaterial(reciclaje.getIdMateriales().getNombre());
                    dto.setNombreAcopio(reciclaje.getIdAcopio().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(reciclajesDTO, HttpStatus.OK);
    }

    @GetMapping("/fecha-rango")
    public ResponseEntity<List<ReciclajeDTO>> obtenerPorRangoFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        List<Reciclaje> reciclajes = reciclajeServices.findByFechaRange(fechaInicio, fechaFin);
        List<ReciclajeDTO> reciclajesDTO = reciclajes.stream()
                .map(reciclaje -> {
                    ReciclajeDTO dto = modelMapper.map(reciclaje, ReciclajeDTO.class);
                    dto.setNombreUsuario(reciclaje.getIdUsuario().getNombre() + " " + reciclaje.getIdUsuario().getApellido());
                    dto.setNombreMaterial(reciclaje.getIdMateriales().getNombre());
                    dto.setNombreAcopio(reciclaje.getIdAcopio().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(reciclajesDTO, HttpStatus.OK);
    }

    @GetMapping("/total-peso/usuario/{usuarioId}")
    public ResponseEntity<Double> obtenerTotalPesoPorUsuario(@PathVariable Integer usuarioId) {
        Double totalPeso = reciclajeServices.getTotalPesoPorUsuario(usuarioId);
        return new ResponseEntity<>(totalPeso != null ? totalPeso : 0.0, HttpStatus.OK);
    }

    @GetMapping("/total-peso/material/{materialId}")
    public ResponseEntity<Double> obtenerTotalPesoPorMaterial(@PathVariable Integer materialId) {
        Double totalPeso = reciclajeServices.getTotalPesoPorMaterial(materialId);
        return new ResponseEntity<>(totalPeso != null ? totalPeso : 0.0, HttpStatus.OK);
    }

    @GetMapping("/peso-minimo")
    public ResponseEntity<List<ReciclajeDTO>> obtenerPorPesoMinimo(@RequestParam Double peso) {
        List<Reciclaje> reciclajes = reciclajeServices.findByPesoMinimo(peso);
        List<ReciclajeDTO> reciclajesDTO = reciclajes.stream()
                .map(reciclaje -> {
                    ReciclajeDTO dto = modelMapper.map(reciclaje, ReciclajeDTO.class);
                    dto.setNombreUsuario(reciclaje.getIdUsuario().getNombre() + " " + reciclaje.getIdUsuario().getApellido());
                    dto.setNombreMaterial(reciclaje.getIdMateriales().getNombre());
                    dto.setNombreAcopio(reciclaje.getIdAcopio().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(reciclajesDTO, HttpStatus.OK);
    }
}