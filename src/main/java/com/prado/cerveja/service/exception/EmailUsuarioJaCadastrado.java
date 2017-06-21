package com.prado.cerveja.service.exception;

public class EmailUsuarioJaCadastrado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailUsuarioJaCadastrado(String message) {
		super(message);
	}
}
