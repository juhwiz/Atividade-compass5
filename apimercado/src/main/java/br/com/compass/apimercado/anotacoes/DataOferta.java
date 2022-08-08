package br.com.compass.apimercado.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.compass.apimercado.validacao.DataOfertaValidacao;

@Documented
@Constraint(validatedBy = DataOfertaValidacao.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataOferta {
    String message() default "esta invalido! A data de validade da oferta nao pode ser anterior a data de hoje"; 
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
