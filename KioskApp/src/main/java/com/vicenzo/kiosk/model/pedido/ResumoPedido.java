package com.vicenzo.kiosk.model.pedido;

import java.util.List;

public class ResumoPedido {
    private final int quantidadeItens;
    private final double total;

    public ResumoPedido(List<ItemPedido> itens) {
        this.quantidadeItens = itens.stream().mapToInt(ItemPedido::getQuantidade).sum();
        this.total = itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }

    public int getQuantidadeItens() { return quantidadeItens; }
    public double getTotal() { return total; }
}
