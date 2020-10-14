package main.ui.controller;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.dao.SQLDAO.SQLCategorieDAO;
import main.dao.SQLDAO.SQLProduitDAO;
import main.pojo.Categorie;
import main.pojo.Produit;
import main.ui.util.util_isFloat;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class controller_addProduit extends util_isFloat implements Initializable {

    //menu declaration
    @FXML
    private MenuItem mnuQuit;
    @FXML
    private MenuItem mnuAbout;
    @FXML
    private MenuItem mnuHome;

    //textfields and input
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputPrice;
    @FXML
    private TextArea inputDesc;
    @FXML
    private ChoiceBox<?> cbxCategorie;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorName.setVisible(false);
        errorPrice.setVisible(false);
        errorDesc.setVisible(false);
        outputProduct.setText("");

        //populate the categories comboBox
        //ArrayList<Categorie> cat = SQLCategorieDAO.getInstance().findAll();
        //String [] stockArr = (String[]) cat.toArray();
        //cbxCategorie.setItems(FXCollections.observableArrayList(stockArr));

    }

    @FXML
    void mnuHome_onClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("message informatif");
        alert.setHeaderText(null);
        alert.setContentText("Cette partie n'existe pas encore");

        alert.showAndWait();
    }

    @FXML
    void mnuQuit_onClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void mnuAbout_onClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("message informatif");
        alert.setHeaderText(null);
        alert.setContentText("Cette partie n'existe pas encore");

        alert.showAndWait();
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
            inputName.setText("");
            errorName.setVisible(false);
        }
        float tarif_prod = 0;
        if (!isFloat(inputPrice.getText()) || inputPrice.getText() == "") {
            errorPrice.setVisible(true);
            isCorrect = false;
        }
        else{
            tarif_prod = Float.parseFloat(inputPrice.getText());
            inputPrice.setText("");
            errorPrice.setVisible(false);
        }
        String desc_prod = inputDesc.getText();
        if (desc_prod == ""){
            errorDesc.setVisible(true);
            isCorrect = false;
        }
        else{
            inputDesc.setText("");
            errorDesc.setVisible(false);

        }

        cbxCategorie.getValue();

        String categ_prod = "";

        //if check is ok create product
        if (isCorrect){
            //create object product
            Produit produit = new Produit(1, nom_prod, desc_prod, tarif_prod, "visuel", new Categorie());
            //insert the object in the database
            SQLProduitDAO.getInstance().create(produit);
            //display in display label the newest product with toString()
            outputProduct.setText(produit.toString());
        }
    }
}
