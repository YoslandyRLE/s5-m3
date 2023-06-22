package br.com.ingleses.m3s05projetorevisao.controller;

import br.com.ingleses.m3s05projetorevisao.model.Produto;
import br.com.ingleses.m3s05projetorevisao.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> get() {
        return produtoService.buscarTodos();
    }

    @GetMapping("{id}")
    public ResponseEntity getId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produtoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Produto produto) {
        try {
            produto.setId(null);
            return ResponseEntity.ok(produtoService.salvar(produto));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            produto.setId(id);
            return ResponseEntity.ok(produtoService.salvar(produto));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produtoService.apagar(id));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
