package com.vicenzo.kiosk.model.repo;

import com.vicenzo.kiosk.model.item.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProdutoRepo {
    public static ObservableList<Produto> catalogo() {
        ObservableList<Produto> list = FXCollections.observableArrayList();
        list.addAll(
                new Produto("Xis Salada", 18.0, "Lanche"),
                new Produto("Cachorro-Quente", 12.0, "Lanche"),
                new Produto("Batata Frita", 10.0, "Acompanhamento"),
                new Produto("Refrigerante Lata", 6.0, "Bebida"),
                new Produto("Suco Natural", 8.0, "Bebida")
        );
        return list;
    }
}

