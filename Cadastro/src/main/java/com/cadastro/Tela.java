/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cadastro;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Tela {

    private Buscador buscador;

    private ArrayList<Cliente> clientes;

    @FXML
    private TextField Nome;
    @FXML
    private TextField Cep;
    @FXML
    private TextField Rua;
    @FXML
    private TextField Numero;
    @FXML
    private TextField Cidade;
    @FXML
    private TextField Estado;
    @FXML
    private TextField Telefone;

    @FXML
    private TableView<Cliente> tabelaClientes;
    @FXML
    private TableColumn<Cliente, Integer> colunaCodigo;
    @FXML
    private TableColumn<Cliente, String> colunaNome;
    @FXML
    private TableColumn<Cliente, String> colunaCidade;
    @FXML
    private TableColumn<Cliente, String> colunaEstado;
    @FXML
    private TableColumn<Cliente, String> colunaTelefone;

    @FXML
    public void initialize() {
        buscador = new Buscador();
        clientes = new ArrayList();
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaCidade.setCellValueFactory(cid -> {
            return new SimpleStringProperty(
                    cid.getValue().getEndereco().getCidade()
            );

        }
        );
        colunaEstado.setCellValueFactory(est -> {
            return new SimpleStringProperty(
                    est.getValue().getEndereco().getEstado()
            );
        }
        );
    }

    @FXML
    public void Buscar() {
        try {
            buscador = new Buscador();
            String cep = Cep.getText();
            Endereco endereco = buscador.buscar(cep);

            Rua.setText(endereco.getRua());
            Cidade.setText(endereco.getCidade());
            Estado.setText(endereco.getEstado());

        } catch (IOException e) {
            System.out.println("Erro ao buscar endereço: " + e.getMessage());
        }
        catch (IllegalArgumentException a){
            System.out.println(a.getMessage());
        }
    }
    
    @FXML    
    public void Gravar() {
        try {
            Cliente cliente = new Cliente(
                    Nome.getText(),
                    new Endereco(
                            Rua.getText(),
                            Numero.getText(),
                            Cidade.getText(),
                            Estado.getText(),
                            Cep.getText()),
                    Telefone.getText());
            clientes.add(cliente);

            tabelaClientes.setItems(FXCollections.observableArrayList(clientes));

            Limpar();

        } catch (Exception e) {
            System.out.println("Erro ao gravar cliente: " + e.getMessage());
        }
    }
    @FXML
    public void Limpar() {
    Nome.clear();
            Cep.clear();
            Rua.clear();
            Numero.clear();
            Cidade.clear();
            Estado.clear();
            Telefone.clear();
    }
}