package com.prado.cerveja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.prado.cerveja.model.Estado;

@Controller
@RequestMapping("/estado")
public class EstadoController {

	@RequestMapping("/novo")
	public ModelAndView novo(Estado estado){
		ModelAndView mv = new ModelAndView("estado/CadastroEstado");
		return mv;
	}
}
