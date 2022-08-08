package br.com.compass.apimercado.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.compass.apimercado.exception.ItemNaoEncontradoException;
import br.com.compass.apimercado.exception.PedidoNaoEncontradoException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = {PedidoNaoEncontradoException.class})
    public ResponseEntity<ErrorMessage> handlerPedidoNaoEncontradoExeption(PedidoNaoEncontradoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(value = {ItemNaoEncontradoException.class})
    public ResponseEntity<ErrorMessage> handlerItemNaoEncontradoException(ItemNaoEncontradoException e){
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
    }

    // @ExceptionHandler(value = {Exception.class})
    // public ResponseEntity<ErrorMessage> handlerException(Exception e){
    //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    // }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> validationsList = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> 
            "Campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage()
            ).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(validationsList));
    }
}
