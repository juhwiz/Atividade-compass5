package br.com.compass.apimercado.service;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import br.com.compass.apimercado.AplicationConfigTest;
import br.com.compass.apimercado.entity.Pedido;
import br.com.compass.apimercado.exception.PedidoNaoEncontradoException;
import br.com.compass.apimercado.repository.PedidoRepository;

@DisplayName("PedidoServiceTest")
public class PedidoServiceTest extends AplicationConfigTest{
    @MockBean
    private PedidoRepository pedidoRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private PedidoService pedidoService;

    @Test
    public void deveFuncionarCorretamente(){
        List<Pedido> todos = pedidoRepository.findAll();
        Assert.assertTrue(todos.isEmpty());
    }

    @Test
    public void deveApagarServico(){
        Long id = 0L;
        Optional<Pedido> byId = pedidoRepository.findById(id);
        pedidoRepository.deleteById(id);

        Assert.assertNotNull(byId);
        Mockito.verify(pedidoRepository).deleteById(id);
    }

    // @Test
    // public void deveRetornarExceptionAoTentarApagarServico(){
        
    //     PedidoNaoEncontradoException thrown = Assertions.assertThrows(PedidoNaoEncontradoException.class, () -> {
            
    //         Long id = null;
    //         Optional<Pedido> byId = pedidoRepository.findById(id);
            

    //     }, "Pedido nao encontrado");

    //     Assert.assertEquals(new PedidoNaoEncontradoException("Pedido nao encontrado") ,thrown.getMessage());
    // }

   

}
