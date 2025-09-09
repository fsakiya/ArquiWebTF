package com.ecominds.tf_arquiweb.interfaces;

import com.ecominds.tf_arquiweb.entidades.Usuario;
import java.util.List;

public interface iUsuarioServices {
    List<Usuario> list();
    void insert(Usuario usuario);
    void update(Usuario usuario);
    void delete(int id);
    Usuario listId(int id);
    Usuario findByCorreo(String correo);
    List<Usuario> findByNombreAndApellido(String nombre, String apellido);
    List<Usuario> getTopUsuariosByPuntos();
    List<Usuario> getTopUsuariosByPesoRecolectado();
}