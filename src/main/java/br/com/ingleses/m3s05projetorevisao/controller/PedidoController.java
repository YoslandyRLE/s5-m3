package br.com.ingleses.m3s05projetorevisao.controller;

import br.com.ingleses.m3s05projetorevisao.model.Pedido;
import br.com.ingleses.m3s05projetorevisao.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("{id}")
    public ResponseEntity getId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pedidoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity getIdStatus(
            @PathVariable Integer status
    ) {
        try {
            return ResponseEntity.ok(pedidoService.buscarPorStatus(status));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Pedido pedido) {
        try {
            return ResponseEntity.ok(pedidoService.criar(pedido));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("{id}/status/{status}")
    public ResponseEntity put(
            @PathVariable Long id,
            @PathVariable Integer status
    ) {
        try {
            return ResponseEntity.ok(pedidoService.alterarStatus(id, status));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
