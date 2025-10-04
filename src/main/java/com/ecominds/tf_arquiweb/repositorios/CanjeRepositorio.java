package com.ecominds.tf_arquiweb.repositorios;

import com.ecominds.tf_arquiweb.entidades.Canje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CanjeRepositorio extends JpaRepository<Canje, Integer> {

    List<Canje> findByIdUsuarioId(Integer usuarioId);

    List<Canje> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);

    @Query("SELECT c FROM Canje c WHERE c.idUsuario.id = :usuarioId ORDER BY c.fecha DESC")
    List<Canje> findCanjesByUsuarioOrderByFecha(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT SUM(c.costo) FROM Canje c WHERE c.idUsuario.id = :usuarioId")
    Integer getTotalCostoPorUsuario(@Param("usuarioId") Integer usuarioId);
}
