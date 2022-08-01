package br.com.compass.apimercado.dto.request;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compass.apimercado.anotacoes.DataOferta;
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
    @NotNull @NotEmpty
    private String nome;
    @NotNull
    private Date dataDeCriacao;
    @NotNull @DataOferta
    private Date dataDeValidade;
    @NotNull
    private Double desconto;
    @NotNull @NotEmpty
    private String descricao;
}
