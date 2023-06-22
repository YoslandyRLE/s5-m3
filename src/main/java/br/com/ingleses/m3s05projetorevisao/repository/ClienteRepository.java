package br.com.ingleses.m3s05projetorevisao.repository;

import br.com.ingleses.m3s05projetorevisao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
