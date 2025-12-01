package com.vicenzo.kiosk.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Parent;

public class StartController {

    @FXML
    private Button btnCliente;

    @FXML
    private void handleCliente() throws Exception {
        Stage stage = (Stage) btnCliente.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void handleAdmin() throws Exception {
        Stage stage = (Stage) btnCliente.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminView.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }
}
