package br.com.compass.apimercado.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

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
public class RequestPedidoDto {
    @NotBlank
    private String cpf;
    @NotBlank
    private List<Item> itens;
    @NotBlank
    private Double total;
}
