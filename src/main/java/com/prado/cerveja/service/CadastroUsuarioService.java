package com.prado.cerveja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.prado.cerveja.model.Usuario;
import com.prado.cerveja.repository.Usuarios;
import com.prado.cerveja.service.exception.EmailUsuarioJaCadastrado;
import com.prado.cerveja.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario){
		Optional<Usuario> emailExitente = usuarios.findByEmail(usuario.getEmail());
		if(emailExitente.isPresent()){
			throw new EmailUsuarioJaCadastrado("Email já cadastrado!");
		}
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())){
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória");
		}
		if (usuario.isNovo()){
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
		
		usuarios.save(usuario);
		
	}

}
