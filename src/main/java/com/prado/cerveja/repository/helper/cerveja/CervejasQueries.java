package com.prado.cerveja.repository.helper.cerveja;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prado.cerveja.model.Cerveja;
import com.prado.cerveja.repository.filter.CervejaFilter;



public interface CervejasQueries {

	public Page<Cerveja>filtra(CervejaFilter filtro, Pageable pageable);
}
