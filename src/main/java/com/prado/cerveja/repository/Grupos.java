package com.prado.cerveja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prado.cerveja.model.Grupo;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long>{

}
