package br.com.compass.apimercado.anotacoes;



import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.compass.apimercado.validacao.CpfValidador;

@Documented
@Constraint(validatedBy = CpfValidador.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cpf{
    String message() default "esta invalido! Ele deve seguir esse formato xxx.xxx.xxx-xx"; 
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}