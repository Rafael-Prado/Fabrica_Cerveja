package com.prado.cerveja.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prado.cerveja.model.Cliente;
import com.prado.cerveja.model.TipoPessoa;
import com.prado.cerveja.repository.Estados;
import com.prado.cerveja.service.CadastroClienteService;
import com.prado.cerveja.service.exception.CpfCnpjClienteJaCadastrado;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private CadastroClienteService cadastroClienteService;

	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente){
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());	
		return mv; 
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return novo(cliente);
		}
		try {
			cadastroClienteService.salvar(cliente);
		} catch (CpfCnpjClienteJaCadastrado e) {
			result.rejectValue("CpfouCnpj", e.getMessage(), e.getMessage());
			return novo(cliente);
		}
		
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		return new ModelAndView("redirect:/cliente/novo");		
		
	}
}
