package com.ecominds.tf_arquiweb.repositorios;

import com.ecominds.tf_arquiweb.entidades.PuntoDeAcopio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PuntoDeAcopioRepositorio extends JpaRepository<PuntoDeAcopio, Integer> {

    Optional<PuntoDeAcopio> findByNombre(String nombre);

    List<PuntoDeAcopio> findByNombreContaining(String nombre);

    @Query("SELECT p FROM PuntoDeAcopio p WHERE p.ubicacion LIKE %:ubicacion%")
    List<PuntoDeAcopio> findByUbicacionContaining(@Param("ubicacion") String ubicacion);

    boolean existsByNombre(String nombre);
}
