package br.com.compass.apimercado.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.compass.apimercado.anotacoes.Cpf;

public class CpfValidador  implements ConstraintValidator<Cpf, String>{

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		
		if ((cpf == null) || (cpf.length() != 14)){
			return false;
		}
        return true;
	}
	
}
