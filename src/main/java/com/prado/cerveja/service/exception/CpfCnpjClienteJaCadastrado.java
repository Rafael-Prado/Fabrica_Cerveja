package com.prado.cerveja.service.exception;

public class CpfCnpjClienteJaCadastrado extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CpfCnpjClienteJaCadastrado(String message) {
		super(message);
	}

}
