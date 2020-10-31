package main.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    public void setupCommande(Commande commande) {
        this.commande = commande;
        update = true;
    }

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //display the current date
        lblCurrentDate.setText(dtf.format(now));
        btnValiderCommande.setDisable(true);
        lblPrix.setText("");
    }

    @FXML
    void btnAjouterProdListe_onClick(ActionEvent event) {
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
        String strProd = taProduit.getText();
        taProduit.setText(strProd + "\n- " + produit.toString() + " | " + qte);

        lblPrix.setText(String.valueOf(commande.getMontantTotal()));
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
            commande.setDate(LocalDate.now());
            if(update == true){
                DAOFactory.getDAOFactory(choix).getCommandeDAO().update(commande);
                strFin = " a bien été modifié";
            }else {
                DAOFactory.getDAOFactory(choix).getCommandeDAO().create(commande);
                strFin = " a bien été créé";
            }
            outputCommande.setText("La commande : " + commande.toString() + strFin);
        }

}
}
