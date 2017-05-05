package com.prado.cerveja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prado.cerveja.model.Cerveja;

@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long> {

	
}
