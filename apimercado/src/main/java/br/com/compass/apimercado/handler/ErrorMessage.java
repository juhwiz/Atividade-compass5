package br.com.compass.apimercado.handler;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private String message;
    private List<String> validationsErrors;

    public ErrorMessage(List<String> validationsErrors) {
        this.validationsErrors = validationsErrors;
    }

    public ErrorMessage(String message) {
        this.message = message;
    }
}
