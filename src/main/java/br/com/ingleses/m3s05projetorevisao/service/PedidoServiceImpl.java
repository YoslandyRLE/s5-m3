package br.com.ingleses.m3s05projetorevisao.service;

import br.com.ingleses.m3s05projetorevisao.model.*;
import br.com.ingleses.m3s05projetorevisao.model.Pedido;
import br.com.ingleses.m3s05projetorevisao.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Override
    public Pedido criar(Pedido pedido) throws Exception {
        pedido.setId(null);
        pedido.setDataPedido(new Date());
        pedido.setDataPagamento(null);
        pedido.setStatus(1);

        if (pedido.getCliente() == null || pedido.getCliente().getId() == null) {
            throw new Exception("Cliente é obrigatório");
        }
        Cliente cliente = clienteService.buscarPorId(pedido.getCliente().getId());
        pedido.setCliente(cliente);

        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new Exception("Pedido está vazio! Informe ao menos 1 item!");
        }

        BigDecimal totalItens = BigDecimal.ZERO;
        for (PedidoItem item : pedido.getItens()) {

            item.setPedido(pedido);

            if (item.getProduto() == null || item.getProduto().getId() == null) {
                throw new Exception("Produto é obrigatório");
            }
            Produto produto = produtoService.buscarPorId(item.getProduto().getId());
            item.setProduto(produto);

            if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                throw new Exception("Quantidade é obrigatório");
            }

            item.setValor(produto.getValor());
            item.setTotalItem(
                    produto.getValor().multiply(
                        new BigDecimal(item.getQuantidade())
                    )
            );

            totalItens = totalItens.add(item.getTotalItem());
        }

        pedido.setTotalItens(totalItens);
        pedido.setTotalFrete(BigDecimal.ZERO);

        pedido.setTotalPedido(pedido.getTotalItens().add(pedido.getTotalFrete()));

        pedido = pedidoRepository.save(pedido);

        return pedido;
    }

    @Override
    public Pedido buscarPorId(Long id) throws Exception {
        Optional<Pedido> opt = pedidoRepository.findById(id);
        if (opt.isEmpty()) {
            throw new Exception("Pedido não encontrado!");
        }
        return opt.get();
    }

    @Override
    public List<Pedido> buscarPorStatus(Integer status) throws Exception {
        return pedidoRepository.findByStatusOrderByDataPedidoDesc(status);
    }

    @Override
    public Pedido alterarStatus(Long id, Integer status) throws Exception {
        Pedido pedido = buscarPorId(id);
        if (status == null) {
            throw new Exception("Status deve ser informado");
        }
        switch (status) {
            case 1:
                // Pendente
                throw new Exception("Status ("+status+") inválido!");
            case 2:
                // Pago
                if (pedido.getStatus() != 1) {
                    throw new Exception("Status ("+status+") inválido!");
                }
                pedido.setStatus(status);
                pedido.setDataPagamento(new Date());
                break;
            case 3:
                // Cancelado
                if (pedido.getStatus() != 1 && pedido.getStatus() != 2) {
                    throw new Exception("Status ("+status+") inválido!");
                }
                pedido.setStatus(status);
                break;
            case 4:
                // Entregue
                if (pedido.getStatus() != 2) {
                    throw new Exception("Status ("+status+") inválido!");
                }
                pedido.setStatus(status);
                break;
            default:
                throw new Exception("Status ("+status+") inválido!");
        }
        pedido = pedidoRepository.save(pedido);
        return pedido;
    }

}
