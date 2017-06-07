package com.prado.cerveja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prado.cerveja.model.Estado;
import com.prado.cerveja.repository.Estados;
import com.prado.cerveja.service.exception.NomeEstadoJaCadastrado;

@Service
public class CadastroEstadoService {

	@Autowired
	private Estados estados;
	
	private Estado salvar(Estado estado){
		Optional<Estado>estadoOptional = estados.findByNomeIgnoreCase(estado.getNome()); 
		if(estadoOptional.isPresent()){
			throw new NomeEstadoJaCadastrado("Estado já cadastrado");
		}
		return estados.saveAndFlush(estado);
	}
}
