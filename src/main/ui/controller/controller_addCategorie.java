package main.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.pojo.Categorie;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controller_addCategorie implements Initializable {
    @FXML
    private TextField inputTitle;
    @FXML
    private Label outputCategory;
    @FXML
    private Label labelUpload;
    @FXML
    private Label errorTitre;
    @FXML
    private Label errorFichier;

    private EPersistence choix;
    public void setupEnum(EPersistence choix) {
        this.choix = choix;
    }

    private Categorie categorie;
    Boolean update = false;
    public void setupCateg(Categorie categorie) {
        this.categorie = categorie;
        inputTitle.setText(categorie.getTitre());
        update = true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inputTitle.setText("");
        errorTitre.setVisible(false);
        errorFichier.setVisible(false);
    }

    File file;
    @FXML
    void uploadFile(MouseEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG);

            //Show open file dialog
            file = fileChooser.showOpenDialog(null);

            labelUpload.setText(file.getName());
        }
        catch (NullPointerException e) {
            labelUpload.setText("");
        }
    }

    @FXML
    void CreateCategory(MouseEvent event) throws IOException {
        boolean isCorrect = true;
        //read textfields to setup variables
        if (inputTitle.getText() == ""){
            errorTitre.setVisible(true);
            isCorrect = false;
        }
        else{
            errorTitre.setVisible(false);
        }

        if (file == null){
            errorFichier.setVisible(true);
            isCorrect = false;
        }
        else{
            errorFichier.setVisible(false);
        }

        if(isCorrect) {
            String strFin;
            if (update == false) {
                categorie = new Categorie();
            }
            categorie.setTitre(inputTitle.getText());
            categorie.setVisuel(file.getName());
            if (update == true) {
                DAOFactory.getDAOFactory(choix).getCategorieDAO().update(categorie);
                strFin = " a bien été modifié";
                controller_accueil.getInstance().refreshCategorie();
            } else {
                DAOFactory.getDAOFactory(choix).getCategorieDAO().create(categorie);
                strFin = " a bien été créé";
                controller_accueil.getInstance().refreshCategorie();
            }
            outputCategory.setText("La catégorie : " + categorie.toString() + strFin);
        }
        inputTitle.setText("");
        labelUpload.setText("");
    }
}
