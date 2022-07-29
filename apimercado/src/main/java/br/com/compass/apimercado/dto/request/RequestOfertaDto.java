package br.com.compass.apimercado.dto.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestOfertaDto {
    @NotBlank
    private String nome;
    @NotBlank
    private Date dataDeCriacao;
    @NotBlank
    private Date dataDeValidade;
    @NotBlank
    private Double desconto;
    @NotBlank
    private String descricao;
}
