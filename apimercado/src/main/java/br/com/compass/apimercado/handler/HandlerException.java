package br.com.compass.apimercado.handler;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.compass.apimercado.exception.ItemNaoEncontradoException;
import br.com.compass.apimercado.exception.PedidoNaoEncontradoException;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(value = {PedidoNaoEncontradoException.class})
    public ResponseEntity<Object> handlerPedidoNaoEncontradoExeption(PedidoNaoEncontradoException e){
        // 1. Create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = new ErrorMessage(
            e.getMessage(),
            badRequest, 
            ZonedDateTime.now());

        // 2. Return response entity
        return new ResponseEntity<>(errorMessage, badRequest);
    }

    @ExceptionHandler(value = {ItemNaoEncontradoException.class})
    public ResponseEntity<Object> handlerItemNaoEncontradoException(ItemNaoEncontradoException e){
        // 1. Create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = new ErrorMessage(
            e.getMessage(),
            badRequest, 
            ZonedDateTime.now());

        // 2. Return response entity
        return new ResponseEntity<>(errorMessage, badRequest);
    }
}
