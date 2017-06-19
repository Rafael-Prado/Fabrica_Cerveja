package com.prado.cerveja.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Embeddable
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String logradouro;
	private int numero;
	private String complemento;	
	private String Cep;
	
	@ManyToOne
	@JoinColumn(name= "codigo_cidade")
	private Cidade cidade;
	
	@Transient
	private Estado estado;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return Cep;
	}

	public void setCep(String cep) {
		Cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String getNomeCidadeSiglaEstado() {
		if (this.cidade != null) {
			return this.cidade.getNome() + "/" + this.cidade.getEstado().getSigla();
		}
		
		return null;
	}
	

}
