package com.vicenzo.kiosk.controller;

import com.vicenzo.kiosk.model.atendimento.StatusAtendimento;
import com.vicenzo.kiosk.model.repo.Pedido;
import com.vicenzo.kiosk.model.atendimento.Atendimento;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class AdminController {
    @FXML private ComboBox<StatusAtendimento> cmbNovoStatus;
    @FXML private TableView<Atendimento> tblAtendimentos;
    @FXML private TableColumn<Atendimento, Integer> colNumero;
    @FXML private TableColumn<Atendimento, String> colStatus;
    @FXML private TableColumn<Atendimento, Integer> colQtd;
    @FXML private TableColumn<Atendimento, Double> colTotal;
    @FXML private Button btnVoltar;

    @FXML
    public void initialize() {
        cmbNovoStatus.setItems(FXCollections.observableArrayList(StatusAtendimento.values()));
        cmbNovoStatus.getSelectionModel().select(StatusAtendimento.EM_PREPARO);
        tblAtendimentos.setItems(Pedido.getAtendimentos());
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus().name()));
        colQtd.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getResumo().getQuantidadeItens()).asObject());
        colTotal.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getResumo().getTotal()).asObject());
    }

    @FXML
    private void handleAtualizarStatus() {
        Atendimento selecionado = tblAtendimentos.getSelectionModel().getSelectedItem();
        StatusAtendimento novoStatus = cmbNovoStatus.getValue();
        if (selecionado != null && novoStatus != null) {
            selecionado.setStatus(novoStatus);
            tblAtendimentos.refresh();
        }
    }

    @FXML
    private void handleVoltar() throws Exception {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/StartView.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void handleRecarregar() {
        tblAtendimentos.refresh();
    }
}
