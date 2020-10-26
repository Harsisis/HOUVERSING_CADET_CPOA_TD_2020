package main.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class controller_addCommande implements Initializable {

    //client part
    @FXML
    private ComboBox<?> cbxClient;

    @FXML
    private Label lblCurrentDate;

    //product part
    @FXML
    private ComboBox<?> cbxProduits;

    @FXML
    private TextField lblQte;

    @FXML
    private Button btnAjouterProdListe;

    //display and validation part
    @FXML
    private Label lblPrix;

    @FXML
    private Button btnValiderCommande;

    @FXML
    private ScrollPane liProd;//scrollpane for the list of product

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnAjouterProdListe_onClick(ActionEvent event) {

    }

    @FXML
    void btnValiderCommande_onClick(ActionEvent event) {

    }
}
