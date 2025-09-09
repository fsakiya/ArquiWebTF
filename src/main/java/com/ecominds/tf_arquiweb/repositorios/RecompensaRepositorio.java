package com.ecominds.tf_arquiweb.repositorios;

import com.ecominds.tf_arquiweb.entidades.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecompensaRepositorio extends JpaRepository<Recompensa, Integer> {

    List<Recompensa> findByDescripcionContaining(String descripcion);

    @Query("SELECT r FROM Recompensa r WHERE r.requisito LIKE %:requisito%")
    List<Recompensa> findByRequisitoContaining(@Param("requisito") String requisito);
}