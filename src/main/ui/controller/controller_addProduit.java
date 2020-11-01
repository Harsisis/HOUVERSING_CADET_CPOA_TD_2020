package main.ui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.pojo.Categorie;
import main.pojo.Produit;
import main.ui.util.util_isFloat;

import java.io.File;
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
    @FXML
    private Label labelUpload;

    //labels for prompt error
    @FXML
    private Label errorPrice;
    @FXML
    private Label errorName;
    @FXML
    private Label errorDesc;
    @FXML
    private Label errorFichier;

    private EPersistence choix;
    public void setupEnum(EPersistence choix) {
        this.choix = choix;
        ArrayList<Categorie> cat = DAOFactory.getDAOFactory(choix).getCategorieDAO().findAll();
        cbxCategorie.getItems().addAll(cat);
    }

    Boolean update = false;
    private Produit produit;
    public void setupProduit(Produit produit) {
        this.produit = produit;
        labelUpload.setText(produit.getVisuel());
        inputPrice.setText(String.valueOf(produit.getTarif()));
        inputDesc.setText(produit.getDescription());
        inputName.setText(produit.getNom());
        update = true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorName.setVisible(false);
        errorPrice.setVisible(false);
        errorDesc.setVisible(false);
        outputProduct.setText("");

        //populate the categories comboBox
    }

    File file;
    @FXML
    void uploadFile(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG);

        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        labelUpload.setText(file.getName());
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

        if (file == null){
            errorFichier.setVisible(true);
            isCorrect = false;
        }
        else{
            errorFichier.setVisible(false);
        }

        //if check is ok create product
        if (isCorrect){
            String strFin;
            if (update == false){
                produit = new Produit();
            }
            produit.setTarif(Float.parseFloat(inputPrice.getText()));
            produit.setDescription(inputDesc.getText());
            produit.setNom(inputName.getText());
            produit.setVisuel(file.getName());
            produit.setCategory(cbxCategorie.getValue());
            if(update == true){
                DAOFactory.getDAOFactory(choix).getProduitDAO().update(produit);
                strFin = " a bien été modifié";
                controller_accueil.getInstance().refreshProduit();
            }else {
                DAOFactory.getDAOFactory(choix).getProduitDAO().create(produit);
                strFin = " a bien été créé";
                controller_accueil.getInstance().refreshProduit();
            }
            outputProduct.setText("Le produit : " + produit.toString() + strFin);
            //empty fields
            inputName.setText("");
            inputPrice.setText("");
            inputDesc.setText("");
        }
    }
}
