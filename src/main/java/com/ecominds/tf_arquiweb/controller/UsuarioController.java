package com.ecominds.tf_arquiweb.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.ecominds.tf_arquiweb.dto.UsuarioDTO;
import com.ecominds.tf_arquiweb.entidades.Usuario;
import com.ecominds.tf_arquiweb.intefaces.iUsuarioServices;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private iUsuarioServices usuarioServices;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        List<Usuario> usuarios = usuarioServices.list();
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> registrar(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioServices.insert(usuario);
        UsuarioDTO nuevoUsuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return new ResponseEntity<>(nuevoUsuarioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(id);
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioServices.update(usuario);
        UsuarioDTO usuarioActualizadoDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return new ResponseEntity<>(usuarioActualizadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        usuarioServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioServices.listId(id);
        if (usuario != null) {
            UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<UsuarioDTO> obtenerPorCorreo(@PathVariable String correo) {
        Usuario usuario = usuarioServices.findByCorreo(correo);
        if (usuario != null) {
            UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/top-puntos")
    public ResponseEntity<List<UsuarioDTO>> obtenerTopPorPuntos() {
        List<Usuario> usuarios = usuarioServices.getTopUsuariosByPuntos();
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
    }

    @GetMapping("/top-peso")
    public ResponseEntity<List<UsuarioDTO>> obtenerTopPorPeso() {
        List<Usuario> usuarios = usuarioServices.getTopUsuariosByPesoRecolectado();
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
    }
}