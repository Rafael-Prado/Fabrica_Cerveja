package com.prado.cerveja.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.prado.cerveja.model.Estado;

public class EstadoConverter implements Converter<String, Estado>{

	@Override
	public Estado convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)){
			Estado estado = new Estado();
			estado.setCodigo(Long.valueOf(codigo));
			return estado;
		}	
		
		return null;
	}

}
