package com.prado.cerveja.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prado.cerveja.model.Cliente;
import com.prado.cerveja.repository.Clientes;
import com.prado.cerveja.service.exception.CpfCnpjClienteJaCadastrado;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void salvar(Cliente cliente){
		Optional<Cliente> clienteExitente = clientes.findByCpfouCnpj(cliente.getCpfCnpjSemFormatacao());
		if(clienteExitente.isPresent()){
			throw new CpfCnpjClienteJaCadastrado("CPF/CNPJ já cadastrado!");
		}
		clientes.save(cliente);
	}
}
