package com.ecominds.tf_arquiweb.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecominds.tf_arquiweb.entidades.Recompensa;

@Repository
public interface RecompensaRepositorio extends JpaRepository<Recompensa, Integer> {

    List<Recompensa> findByDescripcionContaining(String descripcion);

    @Query("SELECT r FROM Recompensa r WHERE r.requisito LIKE %:requisito%")
    List<Recompensa> findByRequisitoContaining(@Param("requisito") String requisito);

    // US-018: Ordenar recompensas por descripción ascendente (A-Z)
    List<Recompensa> findAllByOrderByDescripcionAsc();

    // US-019: Ordenar recompensas por descripción descendente (Z-A)  
    List<Recompensa> findAllByOrderByDescripcionDesc();
}