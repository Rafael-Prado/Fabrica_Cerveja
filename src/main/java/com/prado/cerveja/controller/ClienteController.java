package com.prado.cerveja.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.prado.cerveja.model.Cliente;
import com.prado.cerveja.model.TipoPessoa;
import com.prado.cerveja.repository.Estados;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private Estados estados;

	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente){
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());	
		return mv; 
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result){
		if(result.hasErrors()){
			return novo(cliente);
		}
		
		return new ModelAndView("redirect:/cliente/novo");
	}
}
