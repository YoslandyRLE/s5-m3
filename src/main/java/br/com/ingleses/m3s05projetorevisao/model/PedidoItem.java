package br.com.ingleses.m3s05projetorevisao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "pedido_item")
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal totalItem;

}
