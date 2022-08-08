package br.com.compass.apimercado.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.apimercado.dto.request.RequestPedidoDto;
import br.com.compass.apimercado.dto.response.ResponsePedidoDto;
import br.com.compass.apimercado.service.PedidoService;

@Validated
@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<ResponsePedidoDto> post(@Valid @RequestBody RequestPedidoDto request){
        ResponsePedidoDto response = service.postPedido(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePedidoDto> getById(@PathVariable Long id){
        ResponsePedidoDto response = service.getByIdPedido(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponsePedidoDto>> get(@RequestParam(required = false) String sort,
                                                    @RequestParam(required = false) Integer page, 
                                                    @RequestParam(required = false) String cpf){
        if(sort == null && cpf == null){
            List<ResponsePedidoDto> response = service.get();
            return ResponseEntity.ok(response);
        }
        else if(sort == null){
            List<ResponsePedidoDto> responseCpf = service.getByCpf(cpf);
            return ResponseEntity.ok(responseCpf);
        }
        else{
            List<ResponsePedidoDto> responseSort = service.getBySort(sort, page);
            return ResponseEntity.ok(responseSort);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponsePedidoDto> patch(@RequestBody Map<Object, Object> fields, @PathVariable Long id){
        ResponsePedidoDto response = service.patchPedido(fields, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    

}
