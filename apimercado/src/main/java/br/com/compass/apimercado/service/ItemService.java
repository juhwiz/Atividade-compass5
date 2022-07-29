package br.com.compass.apimercado.service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.compass.apimercado.dto.response.ResponseItemDto;
import br.com.compass.apimercado.entity.Item;
import br.com.compass.apimercado.exception.ItemNaoEncontradoException;
import br.com.compass.apimercado.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseItemDto patchItem(Map<Object, Object> fields, Long id){
        Optional<Item> byId = itemRepository.findById(id);
        if(byId.isPresent()){
            fields.forEach((k, v) -> {
                if(k == "dataDeCriacao" || k == "dataDeValidade"){
                    throw new ItemNaoEncontradoException("Nao e possivel alterar a data de criacao e a data de validade.");        
                }
                Field field = ReflectionUtils.findField(Item.class, (String) k);
                field.setAccessible(true);
                ReflectionUtils.setField(field, byId.get(), v);
            });
            Item item = itemRepository.saveAndFlush(byId.get());
            return modelMapper.map(item, ResponseItemDto.class);
        }else{
            throw new ItemNaoEncontradoException("Item nao encontrado");
        }
    }
    
}
