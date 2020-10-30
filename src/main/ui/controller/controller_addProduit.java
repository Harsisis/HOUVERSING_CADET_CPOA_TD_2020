package main.ui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.dao.SQLDAO.SQLCategorieDAO;
import main.dao.SQLDAO.SQLProduitDAO;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.pojo.Categorie;
import main.pojo.Produit;
import main.ui.util.util_isFloat;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class controller_addProduit extends util_isFloat implements Initializable {

    //textfields and input
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputPrice;
    @FXML
    private TextArea inputDesc;
    @FXML
    private ChoiceBox<Categorie> cbxCategorie;

    //output label
    @FXML
    private Label outputProduct;

    //labels for prompt error
    @FXML
    private Label errorPrice;
    @FXML
    private Label errorName;
    @FXML
    private Label errorDesc;

    private EPersistence choix;
    public void setupEnum(EPersistence choix) {
        this.choix = choix;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorName.setVisible(false);
        errorPrice.setVisible(false);
        errorDesc.setVisible(false);
        outputProduct.setText("");

        //populate the categories comboBox
        ArrayList<Categorie> cat = SQLCategorieDAO.getInstance().findAll();
        cbxCategorie.getItems().addAll(cat);

    }

    @FXML
    void onClickCreateProduct(ActionEvent event) {

        boolean isCorrect = true;
        //read textfields to setup variables
        String nom_prod = inputName.getText();
        if (nom_prod == ""){
            errorName.setVisible(true);
            isCorrect = false;
        }
        else {
            errorName.setVisible(false);
        }
        float tarif_prod = 0;
        if (!isFloat(inputPrice.getText()) || inputPrice.getText() == "") {
            errorPrice.setVisible(true);
            isCorrect = false;
        }
        else{
            tarif_prod = Float.parseFloat(inputPrice.getText());
            errorPrice.setVisible(false);
        }
        String desc_prod = inputDesc.getText();
        if (desc_prod == ""){
            errorDesc.setVisible(true);
            isCorrect = false;
        }
        else{
            errorDesc.setVisible(false);
        }

        //if check is ok create product
        if (isCorrect){
            //create object product
            Produit produit = new Produit(1, nom_prod, desc_prod, tarif_prod, "visuel", cbxCategorie.getValue());
            //insert the object in the database
            DAOFactory.getDAOFactory(choix).getProduitDAO().create(produit);
            //display in display label the newest product with toString()
            outputProduct.setText("Le produit : " + produit.toString() + "\n a bien été créé");
            //empty fields
            inputName.setText("");
            inputPrice.setText("");
            inputDesc.setText("");
        }
    }
}
