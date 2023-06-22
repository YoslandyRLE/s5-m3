package br.com.ingleses.m3s05projetorevisao.repository;

import br.com.ingleses.m3s05projetorevisao.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
