package br.com.ingleses.m3s05projetorevisao.repository;

import br.com.ingleses.m3s05projetorevisao.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatusOrderByDataPedidoDesc(Integer status);

}
