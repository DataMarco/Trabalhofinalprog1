package com.vicenzo.kiosk.model.item;

public class Produto extends Item {
    private final String categoria;

    public Produto(String nome, double preco, String categoria) {
        super(nome, preco);
        this.categoria = categoria;
    }

    public String getCategoria() { return categoria; }
}
