package com.prado.cerveja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prado.cerveja.model.Cerveja;
import com.prado.cerveja.repository.Cervejas;

@Service
public class CadastroCervejaService {

	@Autowired
	private Cervejas cervejas;
	
	@Transactional
	public void salvar(Cerveja cerveja){
		
		cervejas.save(cerveja);
		
	}
}
