package com.vicenzo.kiosk.controller;
import com.vicenzo.kiosk.model.repo.ProdutoRepo;
import com.vicenzo.kiosk.model.repo.Pedido;

import com.vicenzo.kiosk.model.atendimento.*;
import com.vicenzo.kiosk.model.item.Produto;
import com.vicenzo.kiosk.model.pedido.ItemPedido;
import com.vicenzo.kiosk.model.pedido.ResumoPedido;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainController {

    @FXML private Label lblNumeroAtendimento, lblTotal;
    @FXML private TableView<Produto> tblProdutos;
    @FXML private TableColumn<Produto, String> colProdNome;
    @FXML private TableColumn<Produto, Number> colProdPreco;
    @FXML private TableColumn<Produto, String> colProdCategoria;

    @FXML private TableView<ItemPedido> tblItensPedido;
    @FXML private TableColumn<ItemPedido, String> colItemNome;
    @FXML private TableColumn<ItemPedido, Number> colItemQtd;
    @FXML private TableColumn<ItemPedido, Number> colItemPreco;
    @FXML private TableColumn<ItemPedido, Number> colItemSubtotal;


    @FXML private TableView<Atendimento> tblAtendimentos;
    @FXML private TableColumn<Atendimento, Number> colAtNum;
    @FXML private TableColumn<Atendimento, Number> colAtQtd;
    @FXML private TableColumn<Atendimento, Number> colAtTotal;
    @FXML private TableColumn<Atendimento, String> colAtStatus;

    private final ObservableList<ItemPedido> itensPedido = FXCollections.observableArrayList();
    private PedidoRestaurante pedidoAtual;

    @FXML private Button btnVoltarCliente;

    @FXML
    private void handleVoltarCliente() throws Exception {
        Stage stage = (Stage) btnVoltarCliente.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/StartView.fxml"));
        stage.setScene(new Scene(root));
    }


    @FXML
    public void initialize() {
        // Produtos
        tblProdutos.setItems(ProdutoRepo.catalogo());
        colProdNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        colProdPreco.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPreco()));
        colProdCategoria.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategoria()));

        // Itens do pedido
        tblItensPedido.setItems(itensPedido);
        colItemNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getItem().getNome()));
        colItemQtd.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getQuantidade()));
        colItemPreco.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getItem().getPreco()));
        colItemSubtotal.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getSubtotal()));

        // Atendimentos
        tblAtendimentos.setItems(Pedido.getAtendimentos());
        colAtNum.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getNumero()));
        colAtQtd.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getResumo().getQuantidadeItens()));
        colAtTotal.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getResumo().getTotal()));
        colAtStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus().name()));

        handleNovoAtendimento();
    }


    @FXML
    public void handleNovoAtendimento() {
        pedidoAtual = new PedidoRestaurante();
        itensPedido.clear();
        lblNumeroAtendimento.setText(String.valueOf(NumeroAtendimentoGenerator.proximo()));
        atualizarTotal();
    }

    @FXML
    public void handleConfirmarPedido() {
        ResumoPedido resumo = pedidoAtual.resumo();
        int numero = Integer.parseInt(lblNumeroAtendimento.getText());
        Atendimento atendimento = new Atendimento(numero, StatusAtendimento.EM_PREPARO, resumo);
        Pedido.adicionar(atendimento);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Pedido confirmado! Nº " + numero, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
        handleNovoAtendimento();
    }


    @FXML
    public void handleAdicionarProduto() {
        Produto selecionado = tblProdutos.getSelectionModel().getSelectedItem();
        if (selecionado == null) return;
        ItemPedido existente = itensPedido.stream()
                .filter(ip -> ip.getItem().getNome().equals(selecionado.getNome()))
                .findFirst().orElse(null);
        if (existente != null) {
            existente.aumentar();
            tblItensPedido.refresh();
        } else {
            ItemPedido ip = new ItemPedido(selecionado);
            itensPedido.add(ip);
            pedidoAtual.adicionarItem(ip);
        }
        atualizarTotal();
    }

    @FXML
    public void handleRemoverProduto() {
        Produto selecionado = tblProdutos.getSelectionModel().getSelectedItem();
        if (selecionado == null) return;
        ItemPedido ip = itensPedido.stream()
                .filter(i -> i.getItem().getNome().equals(selecionado.getNome()))
                .findFirst().orElse(null);
        if (ip != null) {
            itensPedido.remove(ip);
            pedidoAtual.removerItem(ip);
            atualizarTotal();
        }
    }

    @FXML
    public void handleAumentarQuantidade() {
        ItemPedido ip = tblItensPedido.getSelectionModel().getSelectedItem();
        if (ip == null) return;
        ip.aumentar();
        tblItensPedido.refresh();
        atualizarTotal();
    }

    @FXML
    public void handleDiminuirQuantidade() {
        ItemPedido ip = tblItensPedido.getSelectionModel().getSelectedItem();
        if (ip == null) return;
        ip.diminuir();
        tblItensPedido.refresh();
        atualizarTotal();
    }

    @FXML
    public void handleRemoverItemPedido() {
        ItemPedido ip = tblItensPedido.getSelectionModel().getSelectedItem();
        if (ip == null) return;
        itensPedido.remove(ip);
        pedidoAtual.removerItem(ip);
        atualizarTotal();
    }

    private void atualizarTotal() {
        double total = itensPedido.stream().mapToDouble(ItemPedido::getSubtotal).sum();
        lblTotal.setText(String.format("R$ %.2f", total));
    }
}
