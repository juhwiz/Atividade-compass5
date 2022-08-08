package br.com.compass.apimercado.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compass.apimercado.anotacoes.DataOferta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "oferta")
@Validated
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotEmpty
    private String nome;
    @NotNull 
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataDeCriacao;
    @NotNull @DataOferta
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataDeValidade;
    @NotNull
    private Double desconto;
    @NotNull @NotEmpty
    private String descricao;
}
