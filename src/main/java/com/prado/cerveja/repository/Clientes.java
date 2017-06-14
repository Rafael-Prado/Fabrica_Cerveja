package com.prado.cerveja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prado.cerveja.model.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long> {

	public Optional<Cliente> findByCpfouCnpj(String cpfOucnpj);

}
