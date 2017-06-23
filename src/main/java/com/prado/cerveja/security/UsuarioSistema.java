package com.prado.cerveja.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.prado.cerveja.model.Usuario;

public class UsuarioSistema extends User{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private Usuario usuario;

	public UsuarioSistema(Usuario usuario,
			Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	

}
