package com.prado.cerveja.repository.helper.cerveja;

import java.util.List;

import com.prado.cerveja.model.Cerveja;
import com.prado.cerveja.repository.filter.CervejaFilter;



public interface CervejasQueries {

	public List<Cerveja>filtra(CervejaFilter filtro);
}
