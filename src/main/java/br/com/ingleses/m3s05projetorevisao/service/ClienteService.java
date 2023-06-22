package br.com.ingleses.m3s05projetorevisao.service;

import br.com.ingleses.m3s05projetorevisao.model.Cliente;

import java.util.List;

public interface ClienteService {

    // Create/Update - POST/PUT
    Cliente salvar(Cliente cliente) throws Exception;

    // Read - GET
    List<Cliente> buscarTodos();

    // Read - GET
    Cliente buscarPorId(Long id) throws Exception;

    // Delete - DELETE
    boolean apagar(Long id) throws Exception;

}
