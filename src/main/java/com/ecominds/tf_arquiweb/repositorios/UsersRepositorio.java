package com.ecominds.tf_arquiweb.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecominds.tf_arquiweb.entidades.Users;

@Repository
public interface UsersRepositorio extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Users findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}