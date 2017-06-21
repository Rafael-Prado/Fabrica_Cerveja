package com.prado.cerveja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prado.cerveja.model.Usuario;
import com.prado.cerveja.repository.Usuarios;
import com.prado.cerveja.service.exception.EmailUsuarioJaCadastrado;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Transactional
	public void salvar(Usuario usuario){
		Optional<Usuario> emailExitente = usuarios.findByEmail(usuario.getEmail());
		if(emailExitente.isPresent()){
			throw new EmailUsuarioJaCadastrado("Email já cadastrado!");
		}
		usuarios.save(usuario);
		
	}

}
