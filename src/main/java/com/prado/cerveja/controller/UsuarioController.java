package com.prado.cerveja.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.prado.cerveja.model.Usuario;
import com.prado.cerveja.repository.Grupos;
import com.prado.cerveja.repository.Usuarios;
import com.prado.cerveja.repository.filter.UsuarioFilter;
import com.prado.cerveja.service.CadastroUsuarioService;
import com.prado.cerveja.service.exception.EmailUsuarioJaCadastrado;
import com.prado.cerveja.service.exception.SenhaObrigatoriaUsuarioException;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Grupos grupos;

	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario){
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");	
		mv.addObject("grupos", grupos.findAll());
		return mv; 
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return novo(usuario);
		}
		try {
			usuarioService.salvar(usuario);
		} catch (EmailUsuarioJaCadastrado e) {
			result.rejectValue("Email", e.getMessage(), e.getMessage());
			return novo(usuario);
		}catch(SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso!");
		return new ModelAndView("redirect:/usuario/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter) {
		ModelAndView mv = new ModelAndView("/usuario/PesquisaUsuario");
		mv.addObject("usuarios", usuarios.filtrar(usuarioFilter));
		mv.addObject("grupos", grupos.findAll());
		return mv;
	}
}
