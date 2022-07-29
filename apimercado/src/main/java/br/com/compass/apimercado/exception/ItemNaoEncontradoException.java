package br.com.compass.apimercado.exception;

public class ItemNaoEncontradoException extends RuntimeException{

    public ItemNaoEncontradoException(String message)  {
        super(message);
    }
    
}
