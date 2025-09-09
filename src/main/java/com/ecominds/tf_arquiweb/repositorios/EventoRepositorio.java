package com.ecominds.tf_arquiweb.repositorios;

import com.ecominds.tf_arquiweb.entidades.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Integer> {

    List<Evento> findByFechaAfter(LocalDate fecha);

    List<Evento> findByIdOrganizadorId(Integer organizadorId);

    List<Evento> findByNombreContaining(String nombre);

    @Query("SELECT e FROM Evento e WHERE e.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY e.fecha ASC")
    List<Evento> findEventosEnRangoFechas(@Param("fechaInicio") LocalDate fechaInicio,
                                          @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT e FROM Evento e WHERE e.lugar LIKE %:lugar%")
    List<Evento> findEventosByLugar(@Param("lugar") String lugar);
}
