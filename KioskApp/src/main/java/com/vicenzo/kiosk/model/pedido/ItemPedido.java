package com.vicenzo.kiosk.model.pedido;

import com.vicenzo.kiosk.model.item.Item;

public class ItemPedido {
    private final Item item;
    private int quantidade;

    public ItemPedido(Item item) {
        this.item = item;
        this.quantidade = 1;
    }

    public Item getItem() { return item; }
    public int getQuantidade() { return quantidade; }

    public void aumentar() { quantidade++; }
    public void diminuir() { if (quantidade > 1) quantidade--; }

    public double getSubtotal() { return item.getPreco() * quantidade; }
}
