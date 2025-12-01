package com.vicenzo.kiosk.model.atendimento;

import com.vicenzo.kiosk.model.pedido.ItemPedido;
import com.vicenzo.kiosk.model.pedido.ResumoPedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoRestaurante {
    private final List<ItemPedido> itens = new ArrayList<>();

    public List<ItemPedido> getItens() { return itens; }

    public void adicionarItem(ItemPedido ip) { itens.add(ip); }

    public void removerItem(ItemPedido ip) { itens.remove(ip); }

    public ResumoPedido resumo() { return new ResumoPedido(itens); }
}
