package com.prado.cerveja.model;

import com.prado.cerveja.model.validatio.group.CnpGroup;
import com.prado.cerveja.model.validatio.group.CpfGroup;

public enum TipoPessoa {
	FISICA("Fisica", "CPF", "000.000.000-00", CpfGroup.class) {
		@Override
		public String formatar(String cpfouCnpj) {
			return cpfouCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	},
	JURIDICA("Juridica", "CNPJ", "00.000.000/0000-00", CnpGroup.class) {
		@Override
		public String formatar(String cpfouCnpj) {
			return cpfouCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}
	};
	
	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> grupo;
	
	 TipoPessoa(String descricao, String documento, String mascara, Class<?> grupo) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.grupo = grupo;
	}
	 
	 public abstract String formatar(String cpfouCnpj);

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}

	public Class<?> getGrupo() {
		return grupo;
	}
	
	public static String removerFormatacao(String  cpfOucnpj){
		return cpfOucnpj.replaceAll("\\.|-|/", "");
	}
	 
	 
}
