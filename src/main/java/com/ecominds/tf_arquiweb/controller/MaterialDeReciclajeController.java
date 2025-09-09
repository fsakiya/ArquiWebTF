package com.ecominds.tf_arquiweb.controller;

import com.ecominds.tf_arquiweb.dto.MaterialDeReciclajeDTO;
import com.ecominds.tf_arquiweb.entidades.MaterialDeReciclaje;
import com.ecominds.tf_arquiweb.interfaces.iMaterialDeReciclajeServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/materiales")
@CrossOrigin(origins = "*")
public class MaterialDeReciclajeController {

    @Autowired
    private iMaterialDeReciclajeServices materialServices;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MaterialDeReciclajeDTO>> listar() {
        List<MaterialDeReciclaje> materiales = materialServices.list();
        List<MaterialDeReciclajeDTO> materialesDTO = materiales.stream()
                .map(material -> modelMapper.map(material, MaterialDeReciclajeDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(materialesDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MaterialDeReciclajeDTO> registrar(@RequestBody MaterialDeReciclajeDTO materialDTO) {
        MaterialDeReciclaje material = modelMapper.map(materialDTO, MaterialDeReciclaje.class);
        materialServices.insert(material);
        MaterialDeReciclajeDTO nuevoMaterialDTO = modelMapper.map(material, MaterialDeReciclajeDTO.class);
        return new ResponseEntity<>(nuevoMaterialDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDeReciclajeDTO> actualizar(@PathVariable Integer id, @RequestBody MaterialDeReciclajeDTO materialDTO) {
        materialDTO.setId(id);
        MaterialDeReciclaje material = modelMapper.map(materialDTO, MaterialDeReciclaje.class);
        materialServices.update(material);
        MaterialDeReciclajeDTO materialActualizadoDTO = modelMapper.map(material, MaterialDeReciclajeDTO.class);
        return new ResponseEntity<>(materialActualizadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        materialServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDeReciclajeDTO> obtenerPorId(@PathVariable Integer id) {
        MaterialDeReciclaje material = materialServices.listId(id);
        if (material != null) {
            MaterialDeReciclajeDTO materialDTO = modelMapper.map(material, MaterialDeReciclajeDTO.class);
            return new ResponseEntity<>(materialDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MaterialDeReciclajeDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<MaterialDeReciclaje> materiales = materialServices.findByNombreContaining(nombre);
        List<MaterialDeReciclajeDTO> materialesDTO = materiales.stream()
                .map(material -> modelMapper.map(material, MaterialDeReciclajeDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(materialesDTO, HttpStatus.OK);
    }
}
