package com.ecominds.tf_arquiweb.repositorios;

import com.ecominds.tf_arquiweb.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    // Buscar usuario por correo
    Optional<Usuario> findByCorreo(String correo);

    // Buscar usuarios por nombre y apellido
    List<Usuario> findByNombreContainingAndApellidoContaining(String nombre, String apellido);

    // Buscar usuarios con más de ciertos puntos
    @Query("SELECT u FROM Usuario u WHERE u.puntos >= :puntos ORDER BY u.puntos DESC")
    List<Usuario> findUsuariosByPuntosMinimos(@Param("puntos") Integer puntos);

    // Top usuarios por peso recolectado
    @Query("SELECT u FROM Usuario u ORDER BY u.pesoRecolectado DESC")
    List<Usuario> findTopUsuariosByPesoRecolectado();

    // Verificar si existe usuario por correo
    boolean existsByCorreo(String correo);
}