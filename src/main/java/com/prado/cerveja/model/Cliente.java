package com.prado.cerveja.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.prado.cerveja.model.validatio.ClienteGroupSequenceProvider;
import com.prado.cerveja.model.validatio.group.CnpGroup;
import com.prado.cerveja.model.validatio.group.CpfGroup;

@Entity
@Table(name="cliente")
@GroupSequenceProvider(ClienteGroupSequenceProvider.class)
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message="Nome é obrigaório")
	private String nome;
	
	@NotNull(message="Tipo Pessoa é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_pessoa")
	private TipoPessoa tipoPessoa;
	
	@NotBlank(message="CPF/CNPJ é obrigatório")
	@CPF(groups= CpfGroup.class)
	@CNPJ(groups= CnpGroup.class)
	@Column(name="cpf_cnpj")
	private String CpfouCnpj;	
	
	private String telefone;
	
	@Email(message="E-mail inválido")
	private String email;
	
	@Embedded
	private Endereco endereco;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpfouCnpj() {
		return CpfouCnpj;
	}

	public void setCpfouCnpj(String cpfouCnpj) {
		CpfouCnpj = cpfouCnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
