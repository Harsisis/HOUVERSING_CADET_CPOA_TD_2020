package main.ui.controller;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.dao.SQLDAO.SQLCategorieDAO;
import main.dao.SQLDAO.SQLProduitDAO;
import main.pojo.Categorie;
import main.pojo.Produit;
import main.ui.util.util_isFloat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class controller_addProduit extends util_isFloat implements Initializable {

    //menu declaration
    @FXML
    private MenuItem mnuQuit;
    @FXML
    private MenuItem mnuAbout;
    @FXML
    private MenuItem mnuAddClient;
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
        alert.setContentText("Application développée par Irma Houver Sing et Gauthier Cadet.\nEn cas de problème veuillez nous contacter");

        alert.showAndWait();
    }

    @FXML
    void mnuAddClient_onClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../sample/addClient.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 374, 500);
            Stage stage = new Stage();
            stage.setTitle("Ajouter un Client");
            Image icon = new Image(getClass().getResourceAsStream("../images/iconTest.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
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
            SQLProduitDAO.getInstance().create(produit);
            //display in display label the newest product with toString()
            outputProduct.setText("Le produit : " + produit.toString() + "\n a bien été créé");
            //empty fields
            inputName.setText("");
            inputPrice.setText("");
            inputDesc.setText("");
        }
    }
}
