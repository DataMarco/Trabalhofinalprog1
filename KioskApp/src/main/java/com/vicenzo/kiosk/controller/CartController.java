package com.vicenzo.kiosk.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;

public class CartController {

    @FXML
    private Button btnIrParaPedidos;

    @FXML
    private void handleIrParaPedidos() throws Exception {
        Stage stage = (Stage) btnIrParaPedidos.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void handleConfirmarCarrinho() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Pedido confirmado com sucesso!");
        alert.showAndWait();
    }
}
