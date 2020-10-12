package main.ui.controller;

import main.pojo.Produit;

public class controller_addProduit {

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

    public void createProduct(){
        //read textfields to setup variables
        String nom_prod = null;
        String desc_prod = null;
        float tarif_prod = 0;
        String categ_prod = null;

        //optionnal for now but check the entries
        //checkProd(nom_prod, desc_prod, tarif_prod, categ_prod);

        //if check is ok create product
        Produit produit = new Produit();

        //display in display label the newest product with toString()


    }

}
