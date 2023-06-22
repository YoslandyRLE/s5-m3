package br.com.ingleses.m3s05projetorevisao.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 18, nullable = false)
    private String documento;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 16, nullable = false)
    private String telefone;

    @Column(length = 10, nullable = false)
    private String cep;

    @Column(length = 120, nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private Integer numero;

    @Column(length = 20)
    private String complemento;

    @Column(length = 30, nullable = false)
    private String bairro;

    @Column(length = 50, nullable = false)
    private String cidade;

    @Column(columnDefinition = "bpchar", length = 2, nullable = false)
    private String uf;

}
