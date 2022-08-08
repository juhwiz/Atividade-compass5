package br.com.compass.apimercado.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
    public PedidoNaoEncontradoException(String message){
        super(message);
    }
    
}
