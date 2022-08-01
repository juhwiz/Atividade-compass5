package br.com.compass.apimercado.dto.response;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import br.com.compass.apimercado.anotacoes.Cpf;
import br.com.compass.apimercado.entity.Item;
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
public class ResponsePedidoDto {
    private Long id;
    @NotNull @NotEmpty @Cpf
    private String cpf;
    @NotNull @Valid
    private List<Item> itens;
    @NotNull
    private Double total;

}
