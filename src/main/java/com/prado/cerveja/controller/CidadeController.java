package com.prado.cerveja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CidadeController {

	@RequestMapping("/cidade/novo")
	public String cadastroCidade(){
		return "cidade/CadastroCidade";
	}
}