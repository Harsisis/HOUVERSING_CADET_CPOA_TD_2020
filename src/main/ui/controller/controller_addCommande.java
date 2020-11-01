package main.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.pojo.Categorie;
import main.pojo.Client;
import main.pojo.Commande;
import main.pojo.Produit;

import java.net.URL;
import java.time.LocalDate;
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
    private Label outputCommande;

    @FXML
    private Label lblNom;

    @FXML
    private Button btnValiderCommande;

    @FXML
    private TextArea taProduit;//for the list of product

    private EPersistence choix;
    public void setupEnum(EPersistence choix) {
        this.choix = choix;
        ArrayList<Produit> prod = DAOFactory.getDAOFactory(choix).getProduitDAO().findAll();
        cbxProduits.getItems().addAll(prod);
        ArrayList<Client> client = DAOFactory.getDAOFactory(choix).getClientDAO().findAll();
        cbxClient.getItems().addAll(client);
    }

    Boolean update = false;
    Commande commande = new Commande();
    String strProd;
    public void setupCommande(Commande commande) {
        this.commande = commande;
        cbxClient.setValue(commande.getClient());
        for (Produit prod: commande.getProduits().keySet()){
            String key = prod.toString();
            String value = commande.getProduits().get(prod).toString();
            strProd = (strProd + "\n- " + key + " | " + value);
        }
        taProduit.setText(strProd);
        lblPrix.setText(String.valueOf(commande.getMontantTotal()));
        lblCurrentDate.setText(String.valueOf(commande.getDate()));
        update = true;
    }

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //display the current date
        lblCurrentDate.setText(dtf.format(now));
        if (!update){
            btnValiderCommande.setDisable(true);
        }else
            btnValiderCommande.setDisable(false);
        lblPrix.setText("");
    }

    @FXML
    void cbxClient_onAction(ActionEvent event) {
        if (update){
            btnValiderCommande.setDisable(false);
        }
        lblNom.setText(cbxClient.getSelectionModel().getSelectedItem().getNom() + " " + cbxClient.getSelectionModel().getSelectedItem().getPrenom());
    }

    Boolean produitCh = false;
    @FXML
    void cbxProduits_onAction(ActionEvent event) {
        produitCh = true;
    }

    Boolean produitQte = false;
    @FXML
    void lblQte_onChange(InputMethodEvent event) {
        produitQte = true;
    }

    @FXML
    void btnAjouterProdListe_onClick(ActionEvent event) {
        if (produitCh && produitQte){
            btnValiderCommande.setDisable(false);
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
            strProd = taProduit.getText();
            taProduit.setText(strProd + "\n- " + produit.toString() + " | " + qte);
            this.lblPrix.setText(String.valueOf(commande.getMontantTotal()));
        }
    }

    @FXML
    void btnValiderCommande_onClick(ActionEvent event) {
        boolean isCorrect = true;
        //check if no client is selected
        if (cbxClient.getValue().equals(null)){
            isCorrect = false;
        }

        if (isCorrect){
        String strFin;
            commande.setClient(cbxClient.getValue());
            if(update == true){
                DAOFactory.getDAOFactory(choix).getCommandeDAO().update(commande);
                strFin = " a bien été modifié";
                controller_accueil.getInstance().refreshCommande();
            }else {
                commande.setDate(LocalDate.now());
                DAOFactory.getDAOFactory(choix).getCommandeDAO().create(commande);
                strFin = " a bien été créé";
                controller_accueil.getInstance().refreshCommande();
            }
            outputCommande.setText("La commande : " + commande.toString() + strFin);
        }

}
}
