package com.prado.cerveja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prado.cerveja.model.Estado;

public interface Estados extends JpaRepository<Estado, Long> {

	Optional<Estado> findByNomeIgnoreCase(String nome);

}
