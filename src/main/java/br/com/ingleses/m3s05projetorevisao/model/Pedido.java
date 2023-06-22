package br.com.ingleses.m3s05projetorevisao.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pedido", nullable = false)
    private Date dataPedido = new Date();

    private Date dataPagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private Integer status = 1;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal totalItens;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal totalFrete;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal totalPedido;

    @OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PedidoItem> itens = new ArrayList<>();

}
