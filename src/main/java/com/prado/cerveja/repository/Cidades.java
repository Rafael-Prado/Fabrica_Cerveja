package com.prado.cerveja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prado.cerveja.model.Cidade;
import com.prado.cerveja.model.Estado;
import com.prado.cerveja.repository.helper.cerveja.CidadesQueries;

public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQueries{

	public List<Cidade> findByEstadoCodigo(Long codigoEstado);

	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
}
