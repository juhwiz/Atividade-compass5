package br.com.compass.apimercado.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.stereotype.Service;

import br.com.compass.apimercado.dto.request.RequestPedidoDto;
import br.com.compass.apimercado.dto.response.ResponsePedidoDto;
import br.com.compass.apimercado.entity.Pedido;
import br.com.compass.apimercado.exception.PedidoNaoEncontradoException;
import br.com.compass.apimercado.repository.PedidoRepository;

@Service
@Validated
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponsePedidoDto postPedido(@Valid RequestPedidoDto request){
        // String json = new Gson().toJson(request);
        // JsonObject data = new Gson().fromJson(json, JsonObject.class);

        // Timestamp stamp = new Timestamp(data.get("dataDeCriacao").getAsLong());
        // Date dataCriacao = new Date(stamp.getTime());

        // Timestamp stamp2 = new Timestamp(data.get("dataDeValidade").getAsLong());
        // Date dataValidade = new Date(stamp2.getTime());
        
        // if(dataCriacao.after(dataValidade) ){
        //     throw new PedidoNaoEncontradoException("Data de criacao da oferta nao pode ser posterior a data de validade");
        // }

        Pedido pedido = modelMapper.map(request, Pedido.class);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return modelMapper.map(pedidoSalvo, ResponsePedidoDto.class);
    }

    public List<ResponsePedidoDto> get(){
        List<Pedido> pedido = pedidoRepository.findAll();
        if(!pedido.isEmpty()){
            List<ResponsePedidoDto> pedidoDto = pedido.stream().map(pedidos -> 
                modelMapper.map(pedidos, ResponsePedidoDto.class))
                .collect(Collectors.toList());
            return pedidoDto;
        }
        else{
            throw new PedidoNaoEncontradoException("Pedido nao encontrado");
        }
    }

    public List<ResponsePedidoDto> getBySort(String sort, Integer page){
        Pageable paging = PageRequest.of(page, 50, Sort.Direction.fromString(sort), "total");
        List<Pedido> pedido = pedidoRepository.findAllPedidoOrderBy(paging);
        if(!pedido.isEmpty()){
            List<ResponsePedidoDto> pedidoDto = pedido.stream().map(pedidos -> 
                modelMapper.map(pedidos, ResponsePedidoDto.class))
                .collect(Collectors.toList());
            return pedidoDto;
        }
        else{
            throw new PedidoNaoEncontradoException("Pedido nao encontrado");
        }
    }

    public ResponsePedidoDto patchPedido(Map<Object, Object> fields, Long id){
        Optional<Pedido> byId = pedidoRepository.findById(id);
        if(byId.isPresent()){
            fields.forEach((k, v) -> {
                Field field = ReflectionUtils.findField(Pedido.class, (String) k);
                field.setAccessible(true);
                ReflectionUtils.setField(field, byId.get(), v);
            });
            Pedido pedido = pedidoRepository.saveAndFlush(byId.get());
            return modelMapper.map(pedido, ResponsePedidoDto.class);
        }else{
            throw new PedidoNaoEncontradoException("Pedido nao encontrado");
        }
    }

    public List<ResponsePedidoDto> getByCpf(String cpf){
        List<Pedido> pedido = pedidoRepository.findAllByCpf(cpf);
        if(!pedido.isEmpty()){
            List<ResponsePedidoDto> pedidoDto = pedido.stream().map(pedidos -> 
                modelMapper.map(pedidos, ResponsePedidoDto.class))
                .collect(Collectors.toList());
            return pedidoDto;
        }
        else{
            throw new PedidoNaoEncontradoException("Pedido nao encontrado");
        }
    }

    public ResponsePedidoDto getByIdPedido(Long id){
        Optional<Pedido> byId = pedidoRepository.findById(id);
        if(byId.isPresent()){
            return modelMapper.map(byId.get(), ResponsePedidoDto.class);
        }
        else{
            throw new PedidoNaoEncontradoException("Pedido nao encontrado");
        }
    }

    public void delete(Long id){
        Optional<Pedido> byId = pedidoRepository.findById(id);
        if(byId.isPresent()){
            pedidoRepository.deleteById(id);
        }
        else{
            throw new PedidoNaoEncontradoException("Pedido nao encontrado");
        }
    }

}
