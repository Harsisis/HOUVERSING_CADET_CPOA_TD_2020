package main.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.pojo.Client;
import main.pojo.Commande;
import main.pojo.Produit;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class controller_addCommande implements Initializable {

    //client part
    @FXML
    private ComboBox<Client> cbxClient;

    @FXML
    private Label lblCurrentDate;

    //product part
    @FXML
    private ComboBox<Produit> cbxProduits;

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
    private TextArea taProduit;//for the list of product

    private EPersistence choix;
    public void setupEnum(EPersistence choix) {
        this.choix = choix;
    }

    Commande commande;
    public void setupCommande(Commande commande) { this.commande = commande;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //fill the comboBox
//        ArrayList<Client> liClient = DAOFactory.getDAOFactory(EPersistence.MYSQL).getClientDAO().findAll();
//        ArrayList<String> liClientStr = new ArrayList<>();
//        liClient.forEach( client ->  liClientStr.add(client.toString()));
//        cbxClient.setItems(FXCollections.observableArrayList(liClientStr));
//
//        ArrayList<Produit> liProduit = DAOFactory.getDAOFactory(EPersistence.MYSQL).getProduitDAO().findAll();
//        ArrayList<String> liProdStr = new ArrayList<>();
//        liProduit.forEach( produit ->  liProdStr.add(produit.toString()));
//        cbxProduits.setItems(FXCollections.observableArrayList(liProdStr));


        //display the current date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        lblCurrentDate.setText(dtf.format(now));

    }

    @FXML
    void btnAjouterProdListe_onClick(ActionEvent event) {
        //get the product from the combox and the quantity from the textField --> add a row on the list
        Produit produit = cbxProduits.getValue();
        boolean correct = true;
        int qte = 0;
        try{
            qte = Integer.parseInt(lblQte.getText());
        }catch (NumberFormatException e){
            correct = false;
        }
        if (correct){
            commande.addProduit(produit, qte);
        }
        String strProd = taProduit.getText();
        taProduit.setText(strProd + "\n" + produit.toString() + " | " + qte);


    }

    @FXML
    void btnValiderCommande_onClick(ActionEvent event) {
        //check if no client is selcted and if no product has been added

    }
}
