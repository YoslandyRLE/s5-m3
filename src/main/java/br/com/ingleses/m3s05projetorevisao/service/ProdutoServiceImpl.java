package br.com.ingleses.m3s05projetorevisao.service;

import br.com.ingleses.m3s05projetorevisao.model.Produto;
import br.com.ingleses.m3s05projetorevisao.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(Produto produto) throws Exception {

        if (produto.getId() != null) {
            buscarPorId(produto.getId());
        }

        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new Exception("Nome é obrigatório");
        }
        if (produto.getCategoria() == null || produto.getCategoria().isEmpty()) {
            throw new Exception("Categoria é obrigatório");
        }
        if (produto.getValor() == null || produto.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Valor é obrigatório e deve ser maior que ZERO");
        }
        if (produto.getMarca() == null || produto.getMarca().isEmpty()) {
            throw new Exception("Marca é obrigatório");
        }
        if (produto.getCor() == null || produto.getCor().isEmpty()) {
            throw new Exception("Cor é obrigatório");
        }
        if (produto.getTamanho() == null || produto.getTamanho().isEmpty()) {
            throw new Exception("Tamanho é obrigatório");
        }

        produto = produtoRepository.save(produto);
        return produto;
    }

    @Override
    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto buscarPorId(Long id) throws Exception {
        Optional<Produto> opt = produtoRepository.findById(id);
        if (opt.isEmpty()) {
            throw new Exception("Produto não encontrado!");
        }
        return opt.get();
    }

    @Override
    public boolean apagar(Long id) throws Exception {
        Produto produto = buscarPorId(id);
        try {
            produtoRepository.delete(produto);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
