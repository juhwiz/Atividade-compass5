package br.com.compass.apimercado.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.apimercado.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findAllByCpf(String cpf);
    List<Pedido> findAllPedidoOrderBy(Pageable pageable);
}
