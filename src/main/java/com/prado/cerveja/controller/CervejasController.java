package com.prado.cerveja.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prado.cerveja.model.Cerveja;
import com.prado.cerveja.model.Origem;
import com.prado.cerveja.model.Sabor;
import com.prado.cerveja.repository.Cervejas;
import com.prado.cerveja.repository.Estilos;
import com.prado.cerveja.repository.filter.CervejaFilter;
import com.prado.cerveja.service.CadastroCervejaService;

@Controller
@RequestMapping("/cerveja")
public class CervejasController {

	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;
	
	@Autowired
	private Cervejas cervejas;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cerveja cerveja){
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		return mv; 
	}
	
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView Cadastrar( @Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes){
		if(result.hasErrors()){
			return novo(cerveja);
		}		
			
			cadastroCervejaService.salvar(cerveja);
			attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso");
			return new ModelAndView("redirect:/cerveja/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CervejaFilter cervejaFilter, BindingResult result,@PageableDefault(size = 2) Pageable pageable){
		ModelAndView mv = new ModelAndView("cerveja/PesquisaCerveja");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("sabores", Sabor.values());
		mv.addObject("origens", Origem.values());
		
		mv.addObject("cervejas",cervejas.filtra(cervejaFilter, pageable));
		return mv;
	}
	
}
