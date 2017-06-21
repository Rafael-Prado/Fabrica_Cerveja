package com.prado.cerveja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prado.cerveja.model.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

}
