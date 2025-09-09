package com.ecominds.tf_arquiweb.repositorios;

import com.ecominds.tf_arquiweb.entidades.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizadorRepositorio extends JpaRepository<Organizador, Integer> {

    Optional<Organizador> findByNombre(String nombre);

    List<Organizador> findByNombreContaining(String nombre);

    boolean existsByNombre(String nombre);
}