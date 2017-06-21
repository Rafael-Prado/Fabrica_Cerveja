package com.prado.cerveja.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.prado.cerveja.validation.AtributoConfirmacao;

public class AtributoConfirmacaoValidator implements ConstraintValidator<AtributoConfirmacao, Object>{

	private String atributoSenha;
	private String atributoConfirmacao;
	
	@Override
	public void initialize(AtributoConfirmacao constraintAnnotation) {
		
		this.atributoSenha = constraintAnnotation.atributoSenha();
		this.atributoConfirmacao = constraintAnnotation.atributoConfirmacao();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		boolean valido = false;
		
		try{
		Object valorAtributo = BeanUtils.getProperty(object, this.atributoSenha);
		Object valorAtributoConfirmacao = BeanUtils.getProperty(object, this.atributoConfirmacao);
		
		valido = anbosSaoNull(valorAtributo, valorAtributoConfirmacao) || ambosSaoIguais(valorAtributo, valorAtributoConfirmacao);
		}catch (Exception e) {
			throw new RuntimeException("Erro recuperando valor atributos", e);
		}
		if(!valido){
			context.disableDefaultConstraintViolation();
			String mensage = context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(mensage);
			violationBuilder.addPropertyNode(atributoConfirmacao).addConstraintViolation();
		}
		
		return valido;
	}

	private boolean ambosSaoIguais(Object valorAtributo, Object valorAtributoConfirmacao) {
		
		return valorAtributo != null && valorAtributo.equals(valorAtributoConfirmacao);
	}

	private boolean anbosSaoNull(Object valorAtributo, Object valorAtributoConfirmacao) {
		
		return valorAtributo == null && valorAtributoConfirmacao == null;
	}

}
