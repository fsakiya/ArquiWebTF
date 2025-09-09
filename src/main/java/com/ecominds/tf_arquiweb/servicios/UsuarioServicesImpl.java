package com.ecominds.tf_arquiweb.servicios;

import com.ecominds.tf_arquiweb.entidades.Usuario;
import com.ecominds.tf_arquiweb.interfaces.iUsuarioServices;
import com.ecominds.tf_arquiweb.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicesImpl implements iUsuarioServices {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> list() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public void insert(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    @Override
    public void delete(int id) {
        usuarioRepositorio.deleteById(id);
    }

    @Override
    public Usuario listId(int id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    @Override
    public Usuario findByCorreo(String correo) {
        return usuarioRepositorio.findByCorreo(correo).orElse(null);
    }

    @Override
    public List<Usuario> findByNombreAndApellido(String nombre, String apellido) {
        return usuarioRepositorio.findByNombreContainingAndApellidoContaining(nombre, apellido);
    }

    @Override
    public List<Usuario> getTopUsuariosByPuntos() {
        return usuarioRepositorio.findUsuariosByPuntosMinimos(0);
    }

    @Override
    public List<Usuario> getTopUsuariosByPesoRecolectado() {
        return usuarioRepositorio.findTopUsuariosByPesoRecolectado();
    }
}