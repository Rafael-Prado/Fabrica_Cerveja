package com.prado.cerveja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prado.cerveja.model.Cliente;

@Controller
public class UsuarioController {

	@RequestMapping("/usuario/novo")
	public String novo(Cliente cliente){
		return "usuario/CadastroUsuario"; 
	}
}