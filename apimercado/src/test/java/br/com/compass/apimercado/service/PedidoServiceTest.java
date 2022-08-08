package br.com.compass.apimercado.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.compass.apimercado.AplicationConfigTest;
import br.com.compass.apimercado.dto.request.RequestPedidoDto;
import br.com.compass.apimercado.dto.response.ResponsePedidoDto;
import br.com.compass.apimercado.entity.Item;
import br.com.compass.apimercado.entity.Oferta;
import br.com.compass.apimercado.entity.Pedido;
import br.com.compass.apimercado.exception.PedidoNaoEncontradoException;
import br.com.compass.apimercado.repository.PedidoRepository;

@DisplayName("PedidoServiceTest")
@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest extends AplicationConfigTest{
    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void deveApagarServico(){
        //given
        Long id = 0L;
        Pedido pedido = pedido();
        Mockito.when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));

        //when
        pedidoService.delete(id);

        //then
        Assert.assertNotNull(pedido);
        Mockito.verify(pedidoRepository).deleteById(id);
        Assert.assertNotEquals(PedidoNaoEncontradoException.class, null);
    }

    @Test
    void deveRetornarExceptionAoTentarApagarServico(){
        Long id = null;
        try{
            pedidoService.getByIdPedido(id);
        } catch(PedidoNaoEncontradoException e){
            Assert.assertSame(e.getClass(), PedidoNaoEncontradoException.class);
            Assert.assertNotNull(e.getClass());
        }
    }

    @Test
    void deveRetornarUmPedidoPeloId(){
        //given - precondition or setup
        Long id = 1L;
        ResponsePedidoDto response = response();
        Pedido pedido = pedido();
        Mockito.when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        Mockito.when(modelMapper.map(pedido, ResponsePedidoDto.class)).thenReturn(response);

        //when - action or the behavior that we are going test
        ResponsePedidoDto pedidoDto = pedidoService.getByIdPedido(id);

        //then - verify the output
        Assert.assertEquals(pedidoDto, response);
        Assert.assertNotNull(pedidoDto);
        Assert.assertNotEquals(PedidoNaoEncontradoException.class, null);
    }

    @Test
    void deveTentarRetornarPedidoPorIdMasRetornaUmErro(){
        Long id = null;
        try {
            pedidoService.getByIdPedido(id);
        } catch (PedidoNaoEncontradoException e) {
            
            Assert.assertSame(e.getClass(), PedidoNaoEncontradoException.class);
            Assert.assertNotNull(e.getClass());
        }
    }

    @Test
    void deveCadastrarUmPedido(){

        //given - precondition or setup
        RequestPedidoDto request = request();
        Pedido pedido = pedido();
        ResponsePedidoDto response = response();
        Mockito.when(modelMapper.map(request, Pedido.class)).thenReturn(pedido);
        Mockito.when(pedidoRepository.save(pedido)).thenReturn(pedido);
        Mockito.when(modelMapper.map(pedido, ResponsePedidoDto.class)).thenReturn(response);

        //When
        ResponsePedidoDto postPedido = pedidoService.postPedido(request);

        //Then
        Mockito.verify(pedidoRepository).save(pedido);
        Assert.assertEquals(postPedido, response);
        Assert.assertNotNull(postPedido);
    }

    @Test
    void deveTentarCadastrarPedidoMasRetornarUmErro(){
        //given
        RequestPedidoDto request = request();
        request.setCpf("");

        //When 
        try{
            pedidoService.postPedido(request);

        } catch(PedidoNaoEncontradoException e){
            Assert.assertSame(e.getClass(), PedidoNaoEncontradoException.class);
            Assert.assertNotNull(e.getClass());
        }
        
        //then
    }

    @Test
    void deveRetornarUmaListaDePedido(){
        //Given
        ResponsePedidoDto response = response();
        List<ResponsePedidoDto> listaResponse = new ArrayList<>();
        listaResponse.add(response);
        Pedido pedido = pedido();
        List<Pedido> listaPedido = new ArrayList<>();
        listaPedido.add(pedido);

        Mockito.when(pedidoRepository.findAll()).thenReturn(listaPedido);
        Mockito.when(modelMapper.map(pedido, ResponsePedidoDto.class)).thenReturn(response);
        
        //When
        List<ResponsePedidoDto> dtos = pedidoService.get();

        //Then
        Assert.assertEquals(dtos, listaResponse);
        Assert.assertNotNull(dtos);
        Assert.assertNotEquals(PedidoNaoEncontradoException.class, null);
    }
    
    @Test
    void deveRetornarUmErroAoTentarRetornarUmaLista(){
        Mockito.when(pedidoRepository.findAll()).thenReturn(null);
        try {
            pedidoService.get();
        } catch (PedidoNaoEncontradoException e) {
            Assert.assertSame(e.getClass(), PedidoNaoEncontradoException.class);
            Assert.assertNotNull(e.getClass());
        }
    }

    @Test
    void deveRetornarUmaListaDePedidoComFiltroTotal(){
        //Given
        ResponsePedidoDto response = response();
        List<ResponsePedidoDto> listaResponse = new ArrayList<>();
        listaResponse.add(response);
        Pedido pedido = pedido();
        List<Pedido> listaPedido = new ArrayList<>();
        listaPedido.add(pedido);

        Pageable paging = PageRequest.of(0, 50, Sort.Direction.fromString("desc"), "total");
        Mockito.when(pedidoRepository.findAllPedidoOrderBy(paging)).thenReturn(listaPedido);
        Mockito.when(modelMapper.map(pedido, ResponsePedidoDto.class)).thenReturn(response);

        //When
        List<ResponsePedidoDto> dtos = pedidoService.getBySort("desc", 0);
        
        //Then
        Assert.assertEquals(dtos, listaResponse);
        Assert.assertNotNull(dtos);
        Assert.assertNotEquals(PedidoNaoEncontradoException.class, null);
    }

    @Test
    void deveriaRetornarUmaListaDePedidoComFiltroMasRetornaUmErro(){
        Pageable paging = PageRequest.of(0, 50, Sort.Direction.fromString("desc"), "total");
        Mockito.when(pedidoRepository.findAllPedidoOrderBy(paging)).thenReturn(null);
        try {
            pedidoService.getBySort("desc", 0);
        } catch (PedidoNaoEncontradoException e) {
            Assert.assertSame(e.getClass(), PedidoNaoEncontradoException.class);
            Assert.assertNotNull(e.getClass());
        }
    }

    @Test
    void deveRetornarUmaListaDePedidoPorCpf(){
        //Given
        ResponsePedidoDto response = response();
        List<ResponsePedidoDto> listaResponse = new ArrayList<>();
        listaResponse.add(response);
        Pedido pedido = pedido();
        List<Pedido> listaPedido = new ArrayList<>();
        listaPedido.add(pedido);
        String cpf = "111.111.111-11";

        Mockito.when(pedidoRepository.findAllByCpf(cpf)).thenReturn(listaPedido);
        Mockito.when(modelMapper.map(pedido, ResponsePedidoDto.class)).thenReturn(response);

        //When
        List<ResponsePedidoDto> dtos = pedidoService.getByCpf("111.111.111-11");
        
        //Then
        Assert.assertEquals(dtos, listaResponse);
        Assert.assertNotNull(dtos);
        Assert.assertNotEquals(PedidoNaoEncontradoException.class, null);
    }

    @Test
    void deveriaRetornarUmaListaDePedidoComFiltroPorCpfMasRetornaUmErro(){
        String cpf = "111.111.111-11";
        Mockito.when(pedidoRepository.findAllByCpf(cpf)).thenReturn(null);
        try {
            pedidoService.getByCpf(cpf);
        } catch (PedidoNaoEncontradoException e) {
            Assert.assertSame(e.getClass(), PedidoNaoEncontradoException.class);
            Assert.assertNotNull(e.getClass());
        }
    }

    // @Test
    // void deveriaAtualizarUmPedido(){
    //     //Given
    //     Long id = 1L;
    //     ResponsePedidoDto response = response();
    //     Pedido pedido = pedido();
    //     Mockito.when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
    //     Map<Object,Object> fields = new HashMap<Object,Object>();
    //     fields.put("nome", "canela");

    //     //When
    //     pedidoService.patchPedido(fields, id);

    //     //Then

    // }

    // @Test
    // void deveRetornarUmErroAoTentarAtualizarUmPedido(){
    //     Long id = 1L;
    //     Map<Object,Object> fields = new HashMap<Object,Object>();
    //     fields.put("nome", "canela");
    //     Pedido pedido = pedido();
    //     Mockito.when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
    //     Mockito.when(pedidoRepository.saveAndFlush(pedido)).thenReturn(null);
    //     try {
    //         pedidoService.patchPedido(fields, id);
    //     } catch (PedidoNaoEncontradoException e) {
    //         Assert.assertSame(e.getClass(), PedidoNaoEncontradoException.class);
    //         Assert.assertNotNull(e.getClass());
    //     }
    // }

    //#region BEFORE

    @Before
    public RequestPedidoDto request(){
        LocalDateTime localDateTime = LocalDateTime.of(2022, 8, 01, 11, 50, 00);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        
        LocalDateTime localDateTime2 = LocalDateTime.of(2022, 8, 04, 11, 50, 00);
        Date date2 = Date.from(localDateTime2.atZone(ZoneId.systemDefault()).toInstant());
        

        List<Oferta> listOferta = new ArrayList<>();
        Oferta oferta = new Oferta(1L, "nome oferta", date, date2, 2.05, "Descricao Oferta");
        listOferta.add(oferta);

        List<Item> listItem = new ArrayList<>();
        Item item = new Item(1L, "nomeItem", date, date2, 16.99, "descricaoItem", listOferta);
        listItem.add(item);
        
        RequestPedidoDto pedidoDto = new RequestPedidoDto("111.111.111-11", listItem, 24.59);
        
        return pedidoDto;
        // ResponsePedidoDto responsePedido = new ResponsePedidoDto(1L, "111.111.111-11", listItem, 24.59);
   
    }

    @Before
    public Pedido pedido(){
        LocalDateTime localDateTime = LocalDateTime.of(2022, 8, 01, 11, 50, 00);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        
        LocalDateTime localDateTime2 = LocalDateTime.of(2022, 8, 04, 11, 50, 00);
        Date date2 = Date.from(localDateTime2.atZone(ZoneId.systemDefault()).toInstant());
        

        List<Oferta> listOferta = new ArrayList<>();
        Oferta oferta = new Oferta(1L, "nome oferta", date, date2, 2.05, "Descricao Oferta");
        listOferta.add(oferta);

        List<Item> listItem = new ArrayList<>();
        Item item = new Item(1L, "nomeItem", date, date2, 16.99, "descricaoItem", listOferta);
        listItem.add(item);

        Pedido pedido = new Pedido("111.111.111-11", listItem, 24.59);
        
        return pedido;

    }

    @Before
    public ResponsePedidoDto response(){
        LocalDateTime localDateTime = LocalDateTime.of(2022, 8, 01, 11, 50, 00);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        
        LocalDateTime localDateTime2 = LocalDateTime.of(2022, 8, 04, 11, 50, 00);
        Date date2 = Date.from(localDateTime2.atZone(ZoneId.systemDefault()).toInstant());
        

        List<Oferta> listOferta = new ArrayList<>();
        Oferta oferta = new Oferta(1L, "nome oferta", date, date2, 2.05, "Descricao Oferta");
        listOferta.add(oferta);

        List<Item> listItem = new ArrayList<>();
        Item item = new Item(1L, "nomeItem", date, date2, 16.99, "descricaoItem", listOferta);
        listItem.add(item);
        
        ResponsePedidoDto responsePedido = new ResponsePedidoDto(1L, "111.111.111-11", listItem, 24.59);
        
        return responsePedido;
   
    }
//#endregion   

}
