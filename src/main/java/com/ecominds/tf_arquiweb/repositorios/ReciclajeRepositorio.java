package com.ecominds.tf_arquiweb.repositorios;

import com.ecominds.tf_arquiweb.entidades.Reciclaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReciclajeRepositorio extends JpaRepository<Reciclaje, Integer> {

    List<Reciclaje> findByIdUsuarioId(Integer usuarioId);

    List<Reciclaje> findByIdMaterialesId(Integer materialId);

    List<Reciclaje> findByIdAcopioId(Integer acopioId);

    List<Reciclaje> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);

    @Query("SELECT r FROM Reciclaje r WHERE r.idUsuario.id = :usuarioId ORDER BY r.fecha DESC, r.hora DESC")
    List<Reciclaje> findReciclajesByUsuarioOrderByFechaHora(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT SUM(r.peso) FROM Reciclaje r WHERE r.idUsuario.id = :usuarioId")
    Double getTotalPesoPorUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT SUM(r.peso) FROM Reciclaje r WHERE r.idMateriales.id = :materialId")
    Double getTotalPesoPorMaterial(@Param("materialId") Integer materialId);

    @Query("SELECT r FROM Reciclaje r WHERE r.peso >= :pesoMinimo ORDER BY r.peso DESC")
    List<Reciclaje> findReciclajesByPesoMinimo(@Param("pesoMinimo") Double pesoMinimo);
}
