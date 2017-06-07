package com.prado.cerveja.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prado.cerveja.model.Cidade;
import com.prado.cerveja.repository.Cidades;

@Controller
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private Cidades cidades;

	@RequestMapping("/novo")
	public String cadastroCidade(){
		return "cidade/CadastroCidade";
	}
	
	@RequestMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisaCodigoEstado( @RequestParam(name= "estado", defaultValue= "-1") Long codigoEstado){
		
		return cidades.findByEstadoCodigo(codigoEstado);
	}
}
