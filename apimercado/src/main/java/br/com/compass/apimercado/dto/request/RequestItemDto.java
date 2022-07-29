package br.com.compass.apimercado.dto.request;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

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
public class RequestItemDto {
    @NotBlank
    private String nome;
    @NotBlank
    private Date dataDeCriacao;
    @NotBlank
    private Date dataDeValidade;
    @NotBlank
    private Double valor;
    @NotBlank
    private String descricao;
    private List<Oferta> ofertas; 
}
