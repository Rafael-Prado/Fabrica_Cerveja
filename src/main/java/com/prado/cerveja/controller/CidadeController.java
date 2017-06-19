package com.prado.cerveja.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prado.cerveja.controller.page.PageWrapper;
import com.prado.cerveja.model.Cidade;
import com.prado.cerveja.repository.Cidades;
import com.prado.cerveja.repository.Estados;
import com.prado.cerveja.repository.filter.CidadeFilter;
import com.prado.cerveja.service.CadastroCidadeService;
import com.prado.cerveja.service.exception.NomeCidadeJaCadastradaException;

@Controller
@RequestMapping("/cidade")
public class CidadeController {
	
	@Autowired
	private Cidades cidades;
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private CadastroCidadeService cadastroCervejaService;

	@RequestMapping("/nova")
	public ModelAndView nova(Cidade cidade){
		ModelAndView mv = new ModelAndView("cidade/CadastroCidade");
		mv.addObject("estados", estados.findAll());
		return mv;
	}
	
	@RequestMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisaCodigoEstado( @RequestParam(name= "estado", defaultValue= "-1") Long codigoEstado){		
		return cidades.findByEstadoCodigo(codigoEstado);
	}
	
	@PostMapping("/nova")
	public ModelAndView salvar( @Valid Cidade cidade , BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()){
			return nova(cidade);
		}
		try {			
			cadastroCervejaService.salvar(cidade);
			
		} catch (NomeCidadeJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(cidade);
		}
		
		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
		return new ModelAndView("redirect:/cidade/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CidadeFilter cidadeFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cidade/PesquisaCidade");
		mv.addObject("estados", estados.findAll());
		
		PageWrapper<Cidade> paginaWrapper = new PageWrapper<>(cidades.filtrar(cidadeFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
}
