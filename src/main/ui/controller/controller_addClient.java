package main.ui.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.dao.SQLDAO.SQLClientDAO;
import main.pojo.Client;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class controller_addClient implements Initializable {

    //menu declaration
    @FXML
    private MenuItem mnuQuit;
    @FXML
    private MenuItem mnuAddProd;
    @FXML
    private MenuItem mnuAbout;
    @FXML
    private MenuItem mnuHome;

    //label error
    @FXML
    private Label errorAdr;
    @FXML
    private Label errorMdp;
    @FXML
    private Label errorSurname;
    @FXML
    private Label errorName;
    @FXML
    private Label errorIdent;

    //input
    @FXML
    private TextField inputPassword;
    @FXML
    private TextField inputIdent;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputAdrNumber;
    @FXML
    private TextField inputAdrRue;
    @FXML
    private TextField inputAdrVille;
    @FXML
    private TextField inputAdrCp;
    @FXML
    private TextField inputSurname;

    //ComboBox
    @FXML
    private ComboBox cbxCountry;

    //output label
    @FXML
    private Label outputClient;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //empty the fields
        inputName.setText("");
        inputSurname.setText("");
        inputIdent.setText("");
        inputPassword.setText("");
        inputAdrNumber.setText("");
        inputAdrRue.setText("");
        inputAdrVille.setText("");
        inputAdrCp.setText("");

        // set unvisible error label
        errorSurname.setVisible(false);
        errorAdr.setVisible(false);
        errorIdent.setVisible(false);
        errorMdp.setVisible(false);
        errorName.setVisible(false);

        //populate ComboBox
        ObservableList<String> cities = FXCollections.observableArrayList();

        String[] locales1 = Locale.getISOCountries();
        for (String countrylist : locales1) {
            Locale obj = new Locale("", countrylist);
            String[] city = {obj.getDisplayCountry()};
            for (int x = 0; x < city.length; x++) {
                cities.add(obj.getDisplayCountry());
            }
        }
        cbxCountry.setItems(cities);

    }

    @FXML
    void mnuAddProd_onClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../sample/addProduct.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 374, 400);
            Stage stage = new Stage();
            stage.setTitle("Ajouter un Produit");
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
    void onClickCreateClient(ActionEvent event) {

        boolean isCorrect = true;
        //read textfields to setup variables
        //Surname error
        String nom_client = inputSurname.getText();
        if (nom_client == ""){
            errorSurname.setVisible(true);
            isCorrect = false;
        }
        else {
            errorSurname.setVisible(false);
        }
        //Name error
        String prenom_client = inputName.getText();
        if (inputName.getText() == "") {
            errorName.setVisible(true);
            isCorrect = false;
        }
        else{
            errorName.setVisible(false);
        }
        //identificiation error
        String ident_client = inputIdent.getText();
        if (ident_client == ""){
            errorIdent.setVisible(true);
            isCorrect = false;
        }
        else{
            errorIdent.setVisible(false);
        }
        //password error
        String mdp_client = inputPassword.getText();
        if (mdp_client == ""){
            errorMdp.setVisible(true);
            isCorrect = false;
        }
        else{
            errorMdp.setVisible(false);
        }
        //Address error
        String no_adrCl = inputAdrNumber.getText();
        String rue_adrCl = inputAdrRue.getText();
        String ville_adrCl = inputAdrVille.getText();
        String cp_adrCL = inputAdrCp.getText();
        if (no_adrCl == "" || rue_adrCl == "" || ville_adrCl == "" || cp_adrCL == ""){
            errorAdr.setVisible(true);
            isCorrect = false;
        }
        else {
            errorAdr.setVisible(false);
        }
        //if check is ok create client
        if (isCorrect){
            //create object product
            Client client = new Client(1, nom_client, prenom_client, ident_client, mdp_client, no_adrCl, rue_adrCl, cp_adrCL, ville_adrCl, cbxCountry.getValue().toString());
            //insert the object in the database
            SQLClientDAO.getInstance().create(client);
            //display in display label the newest product with toString()
            outputClient.setText("Le client : " + client.toString() + "\n a bien été créé");
            //empty the fields
            inputName.setText("");
            inputSurname.setText("");
            inputIdent.setText("");
            inputPassword.setText("");
            inputAdrNumber.setText("");
            inputAdrRue.setText("");
            inputAdrVille.setText("");
            inputAdrCp.setText("");
        }
    }

}
