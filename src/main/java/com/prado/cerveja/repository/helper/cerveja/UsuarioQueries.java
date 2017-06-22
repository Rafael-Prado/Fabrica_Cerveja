package com.prado.cerveja.repository.helper.cerveja;

import java.util.List;
import java.util.Optional;

import com.prado.cerveja.model.Usuario;

public interface UsuarioQueries {

	public Optional<Usuario> porEmailEAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);
}
