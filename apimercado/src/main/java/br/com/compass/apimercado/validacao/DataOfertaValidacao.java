package br.com.compass.apimercado.validacao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.compass.apimercado.anotacoes.DataOferta;

public class DataOfertaValidacao implements ConstraintValidator<DataOferta, Date>{
    @Override
	public boolean isValid(Date date, ConstraintValidatorContext context) {
		
        LocalDateTime localDate = LocalDateTime.now();

        Date date1 = Date.from(localDate.atZone( ZoneId.systemDefault() ).toInstant() );

		if(date.before(date1)){
            return false;
        }

        return true;
	} 
}
