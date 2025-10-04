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

import com.ecominds.tf_arquiweb.dto.PuntoDeAcopioDTO;
import com.ecominds.tf_arquiweb.entidades.PuntoDeAcopio;
import com.ecominds.tf_arquiweb.intefaces.iPuntoDeAcopioServices;

@RestController
@RequestMapping("/api/puntos-acopio")
@CrossOrigin(origins = "*")
public class PuntoDeAcopioController {

    @Autowired
    private iPuntoDeAcopioServices puntoDeAcopioServices;

    @Autowired
    private ModelMapper modelMapper;

    // US-003: Consultar puntos de acopio
    @GetMapping
    public ResponseEntity<List<PuntoDeAcopioDTO>> listar() {
        List<PuntoDeAcopio> puntosAcopio = puntoDeAcopioServices.list();
        List<PuntoDeAcopioDTO> puntosAcopioDTO = puntosAcopio.stream()
                .map(punto -> modelMapper.map(punto, PuntoDeAcopioDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(puntosAcopioDTO, HttpStatus.OK);
    }

    // US-015: Crear Punto de Acopio (Solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<PuntoDeAcopioDTO> registrar(@RequestBody PuntoDeAcopioDTO puntoDTO) {
        PuntoDeAcopio punto = modelMapper.map(puntoDTO, PuntoDeAcopio.class);
        puntoDeAcopioServices.insert(punto);
        PuntoDeAcopioDTO nuevoPuntoDTO = modelMapper.map(punto, PuntoDeAcopioDTO.class);
        return new ResponseEntity<>(nuevoPuntoDTO, HttpStatus.CREATED);
    }

    // US-016: Editar Punto de Acopio (Solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PuntoDeAcopioDTO> actualizar(@PathVariable Integer id, @RequestBody PuntoDeAcopioDTO puntoDTO) {
        puntoDTO.setId(id);
        PuntoDeAcopio punto = modelMapper.map(puntoDTO, PuntoDeAcopio.class);
        puntoDeAcopioServices.update(punto);
        PuntoDeAcopioDTO puntoActualizadoDTO = modelMapper.map(punto, PuntoDeAcopioDTO.class);
        return new ResponseEntity<>(puntoActualizadoDTO, HttpStatus.OK);
    }

    // US-017: Eliminar Punto de Acopio (Solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        puntoDeAcopioServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoDeAcopioDTO> obtenerPorId(@PathVariable Integer id) {
        PuntoDeAcopio punto = puntoDeAcopioServices.listId(id);
        if (punto != null) {
            PuntoDeAcopioDTO puntoDTO = modelMapper.map(punto, PuntoDeAcopioDTO.class);
            return new ResponseEntity<>(puntoDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PuntoDeAcopioDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<PuntoDeAcopio> puntos = puntoDeAcopioServices.findByNombreContaining(nombre);
        List<PuntoDeAcopioDTO> puntosDTO = puntos.stream()
                .map(punto -> modelMapper.map(punto, PuntoDeAcopioDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(puntosDTO, HttpStatus.OK);
    }

    @GetMapping("/ubicacion")
    public ResponseEntity<List<PuntoDeAcopioDTO>> buscarPorUbicacion(@RequestParam String ubicacion) {
        List<PuntoDeAcopio> puntos = puntoDeAcopioServices.findByUbicacion(ubicacion);
        List<PuntoDeAcopioDTO> puntosDTO = puntos.stream()
                .map(punto -> modelMapper.map(punto, PuntoDeAcopioDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(puntosDTO, HttpStatus.OK);
    }
}