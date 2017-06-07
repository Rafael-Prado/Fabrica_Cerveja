package com.prado.cerveja.service.exception;

public class NomeEstadoJaCadastrado extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NomeEstadoJaCadastrado(String message) {
		super(message);
	}

}
