package com.prado.cerveja.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prado.cerveja.model.Usuario;
import com.prado.cerveja.repository.Usuarios;

@Service
public class CadastroUsuarioService {
	
	private Usuarios usuarios;
	
	@Transactional
	public void salvar(Usuario usuario){
		
		usuarios.save(usuario);
		
	}

}
