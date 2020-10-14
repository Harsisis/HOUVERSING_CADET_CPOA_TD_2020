package main.ui.controller;

import com.gluonhq.charm.glisten.control.TextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.pojo.Produit;

import java.awt.event.MouseEvent;

public class controller_addProduit {

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPrice;

    @FXML
    private TextArea inputDesc;

    @FXML
    private Label outputProduct;

    public boolean checkProd(String nom_prod, String desc_prod, float tarif_prod, String categ_prod){
        if (nom_prod!=""){
            System.out.println("nom != null");
            boolean b_nom = true;
        }
        else {
            //set visible error label
        }
        return false;
    }
    @FXML
    void onClickCreateProduct(MouseEvent event) {
        //read textfields to setup variables
        String nom_prod = inputName.getText();
        String desc_prod = null;
        float tarif_prod = 0;
        String categ_prod = null;

        outputProduct.setText(nom_prod);

        //optionnal for now but check the entries
        //checkProd(nom_prod, desc_prod, tarif_prod, categ_prod);

        //if check is ok create product
        Produit produit = new Produit();

        //display in display label the newest product with toString()

    }

}
