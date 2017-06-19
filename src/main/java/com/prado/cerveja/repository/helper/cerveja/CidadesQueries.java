package com.prado.cerveja.repository.helper.cerveja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prado.cerveja.model.Cidade;
import com.prado.cerveja.repository.filter.CidadeFilter;

public interface CidadesQueries {

	Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable);
}
