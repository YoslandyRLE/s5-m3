package br.com.ingleses.m3s05projetorevisao.service;

import br.com.ingleses.m3s05projetorevisao.model.Produto;

import java.util.List;

public interface ProdutoService {

    // Create/Update - POST/PUT
    Produto salvar(Produto produto) throws Exception;

    // Read - GET
    List<Produto> buscarTodos();

    // Read - GET
    Produto buscarPorId(Long id) throws Exception;

    // Delete - DELETE
    boolean apagar(Long id) throws Exception;

}
