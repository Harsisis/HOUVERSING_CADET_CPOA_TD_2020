package main.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.pojo.Client;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class controller_addClient implements Initializable {

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

    private EPersistence choix;

    private Client client;

    private Boolean update = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //empty the fields
        emptyFields();

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

    public void setupEnum(EPersistence choix) {
        this.choix = choix;
    }


    public void setupClient(Client client) {
        this.client = client;
        inputName.setText(client.getPrenom());
        inputSurname.setText(client.getNom());
        inputIdent.setText(client.getIdentifiant());
        inputPassword.setText(client.getMdp());
        inputAdrNumber.setText(client.getAdrNumero());
        inputAdrRue.setText(client.getAdrVoie());
        inputAdrVille.setText(client.getAdrVille());
        inputAdrCp.setText(client.getAdrCP());
        cbxCountry.setValue(client.getAdrPays());

        update = true;
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
        if (isCorrect) {
            String strFin;
            if (update == false) {
                client = new Client();
            }
            client.setNom(inputSurname.getText());
            client.setPrenom(inputName.getText());
            client.setIdentifiant(inputIdent.getText());
            client.setMdp(inputPassword.getText());
            client.setAdrNumero(inputAdrNumber.getText());
            client.setAdrVoie(inputAdrRue.getText());
            client.setAdrVille(inputAdrVille.getText());
            client.setAdrPays(cbxCountry.getValue().toString());
            client.setAdrCP(inputAdrCp.getText());

            if (update == true) {
                DAOFactory.getDAOFactory(choix).getClientDAO().update(client);
                strFin = " a bien été modifié";
                controller_accueil.getInstance().refreshClient();
            } else {
                DAOFactory.getDAOFactory(choix).getClientDAO().create(client);
                strFin = " a bien été créé";
                controller_accueil.getInstance().refreshClient();
            }
            outputClient.setText("Le client : " + client.toString() + strFin);
            //empty the fields
            emptyFields();
        }
    }

    private void emptyFields() {
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
