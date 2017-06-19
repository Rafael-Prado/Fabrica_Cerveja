package com.prado.cerveja.repository.filter;

import com.prado.cerveja.model.TipoPessoa;

public class ClienteFilter {

	private String nome;
	private String cpfouCnpj;

	

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpfouCnpj() {
		return cpfouCnpj;
	}

	public void setCpfouCnpj(String cpfouCnpj) {
		this.cpfouCnpj = cpfouCnpj;
	}



	public Object getCpfOuCnpjSemFormatacao() {
		return TipoPessoa.removerFormatacao(this.cpfouCnpj);
	}

}
