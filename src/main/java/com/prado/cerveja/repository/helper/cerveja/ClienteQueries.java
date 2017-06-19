package com.prado.cerveja.repository.helper.cerveja;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prado.cerveja.model.Cliente;
import com.prado.cerveja.repository.filter.ClienteFilter;

public interface ClienteQueries {	

	Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
	
}
