package com.ecominds.tf_arquiweb.repositorios;

import com.ecominds.tf_arquiweb.entidades.MaterialDeReciclaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialDeReciclajeRepositorio extends JpaRepository<MaterialDeReciclaje, Integer> {

    Optional<MaterialDeReciclaje> findByNombre(String nombre);

    List<MaterialDeReciclaje> findByNombreContaining(String nombre);

    @Query("SELECT m FROM MaterialDeReciclaje m WHERE m.descripcion LIKE %:descripcion%")
    List<MaterialDeReciclaje> findByDescripcionContaining(@Param("descripcion") String descripcion);

    boolean existsByNombre(String nombre);
}