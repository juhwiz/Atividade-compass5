package br.com.compass.apimercado.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.apimercado.dto.response.ResponseItemDto;
import br.com.compass.apimercado.service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseItemDto> patch(@RequestBody Map<Object, Object> fields, @PathVariable Long id){
        ResponseItemDto response = service.patchItem(fields, id);
        return ResponseEntity.ok(response);

    }
}
