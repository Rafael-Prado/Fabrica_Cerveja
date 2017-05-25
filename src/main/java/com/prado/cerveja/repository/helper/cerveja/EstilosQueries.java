package com.prado.cerveja.repository.helper.cerveja;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prado.cerveja.model.Estilo;
import com.prado.cerveja.repository.filter.EstiloFilter;

public interface EstilosQueries {


	public Page<Estilo>filtrar(EstiloFilter filtro, Pageable pageable);
}
