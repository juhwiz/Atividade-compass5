package br.com.compass.apimercado.dto.response;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import br.com.compass.apimercado.entity.Oferta;
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
@Validated
public class ResponseItemDto {
    private Long id;
    @NotNull @NotEmpty
    private String nome;
    @NotNull
    private Date dataDeCriacao;
    @NotNull
    private Date dataDeValidade;
    @NotNull
    private Double valor;
    @NotNull @NotEmpty
    private String descricao;
    @NotNull @Valid
    private List<Oferta> ofertas; 
}
