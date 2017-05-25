package com.prado.cerveja.controller;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prado.cerveja.controller.page.PageWrapper;
import com.prado.cerveja.model.Estilo;
import com.prado.cerveja.repository.Estilos;
import com.prado.cerveja.repository.filter.EstiloFilter;
import com.prado.cerveja.service.CadastroEstiloService;
import com.prado.cerveja.service.exception.NomeEstiloJaCadastradoException;

@Controller
@RequestMapping(value="/estilo")
public class EstilosController {
	
	
	@Autowired
	private CadastroEstiloService cadastroEstiloService;
	
	@Autowired
	private Estilos estilos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Estilo estilo){
		ModelAndView mv = new ModelAndView("estilo/CadastroEstilo");		
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastra(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes){
		if(result.hasErrors()){
			return novo(estilo);
		}
		try {
			cadastroEstiloService.salvar(estilo);
		} catch (NomeEstiloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}		
		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso!");
		return new ModelAndView("redirect:/estilo/novo");
	}
	
	@RequestMapping( method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result ){
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}	
		
		estilo = cadastroEstiloService.salvar(estilo);		
		return 	ResponseEntity.ok(estilo);		
	}
	
	@RequestMapping
	public ModelAndView pesquisar(EstiloFilter estilofilter, BindingResult result, 
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest){
		ModelAndView mv = new ModelAndView("estilo/PesquisaEstilo");
		
		PageWrapper<Estilo> pageWrapper = new PageWrapper<>(estilos.filtrar(estilofilter,  pageable), httpServletRequest);
		mv.addObject("pagina", pageWrapper);
		return mv;
		
	}
}
