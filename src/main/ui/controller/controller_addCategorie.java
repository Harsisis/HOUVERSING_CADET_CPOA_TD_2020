package main.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import main.dao.SQLDAO.SQLCategorieDAO;
import main.pojo.Categorie;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class controller_addCategorie implements Initializable {
    @FXML
    private TextField inputTitle;

    @FXML
    private TextField inputVisuel;

    @FXML
    private Label outputCategory;

    @FXML
    private Label labelUpload;

    private ImageView myImageView = new ImageView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inputTitle.setText("");
        inputVisuel.setText("");
    }

    @FXML
    void uploadFile(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        labelUpload.setText(file.toString());
    }

    @FXML
    void CreateCategory(MouseEvent event) {
        boolean isCorrect = true;
        //read textfields to setup variables
        //Surname error
        if (inputTitle.getText() == ""){
            //errorSurname.setVisible(true);
            isCorrect = false;
        }
        //Name error
        if (inputVisuel.getText() == "") {
            //errorName.setVisible(true);
            isCorrect = false;
        }

        if(isCorrect){
            Categorie categorie = new Categorie(1, inputTitle.getText(), inputVisuel.getText());
            SQLCategorieDAO.getInstance().create(categorie);
            outputCategory.setText("La catégorie : " + categorie.toString() + "\n a bien été créé");
        }
    }
}
