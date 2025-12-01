package com.vicenzo.kiosk.model.repo;

import com.vicenzo.kiosk.model.atendimento.Atendimento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Pedido {
    private static final ObservableList<Atendimento> ATENDIMENTOS = FXCollections.observableArrayList();

    public static ObservableList<Atendimento> getAtendimentos() { return ATENDIMENTOS; }

    public static void adicionar(Atendimento a) { ATENDIMENTOS.add(a); }
}
