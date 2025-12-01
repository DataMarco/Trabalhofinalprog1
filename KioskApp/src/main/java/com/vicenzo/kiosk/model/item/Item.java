package com.vicenzo.kiosk.model.item;

public abstract class Item {
    private final String nome;
    private final double preco;

    protected Item(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
}
