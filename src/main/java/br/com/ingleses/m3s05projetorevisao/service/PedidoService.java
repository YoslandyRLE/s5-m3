package br.com.ingleses.m3s05projetorevisao.service;

import br.com.ingleses.m3s05projetorevisao.model.Pedido;

import java.util.List;

public interface PedidoService {

    // Create - POST
    Pedido criar(Pedido pedido) throws Exception;

    // Read - GET
    Pedido buscarPorId(Long id) throws Exception;

    // Read - GET
    List<Pedido> buscarPorStatus(Integer status) throws Exception;

    // Update - PUT
    Pedido alterarStatus(Long id, Integer status) throws Exception;

}
