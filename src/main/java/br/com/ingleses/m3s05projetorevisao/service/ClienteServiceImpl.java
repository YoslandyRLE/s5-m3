package br.com.ingleses.m3s05projetorevisao.service;

import br.com.ingleses.m3s05projetorevisao.model.Cliente;
import br.com.ingleses.m3s05projetorevisao.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente salvar(Cliente cliente) throws Exception {

        if (cliente.getId() != null) {
            buscarPorId(cliente.getId());
        }

        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new Exception("Nome é obrigatório");
        }
        if (cliente.getDocumento() == null || cliente.getDocumento().isEmpty()) {
            throw new Exception("Documento é obrigatório");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new Exception("E-mail é obrigatório");
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            throw new Exception("Telefone é obrigatório");
        }
        if (cliente.getCep() == null || cliente.getCep().isEmpty()) {
            throw new Exception("Cep é obrigatório");
        }
        if (cliente.getLogradouro() == null || cliente.getLogradouro().isEmpty()) {
            throw new Exception("Logradouro é obrigatório");
        }
        if (cliente.getNumero() == null) {
            throw new Exception("Número é obrigatório");
        }
        if (cliente.getBairro() == null || cliente.getBairro().isEmpty()) {
            throw new Exception("Bairro é obrigatório");
        }
        if (cliente.getCidade() == null || cliente.getCidade().isEmpty()) {
            throw new Exception("Cidade é obrigatório");
        }
        if (cliente.getUf() == null || cliente.getUf().isEmpty()) {
            throw new Exception("UF é obrigatório");
        }

        cliente = clienteRepository.save(cliente);
        return cliente;
    }

    @Override
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) throws Exception {
        Optional<Cliente> opt = clienteRepository.findById(id);
        if (opt.isEmpty()) {
            throw new Exception("Cliente não encontrado!");
        }
        return opt.get();
    }

    @Override
    public boolean apagar(Long id) throws Exception {
        Cliente cliente = buscarPorId(id);
        try {
            clienteRepository.delete(cliente);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
