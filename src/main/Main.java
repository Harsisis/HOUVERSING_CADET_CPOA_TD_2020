package main;

//import main.ui.menuTD1;
import main.dao.classeSQL.ProduitSQL;
import main.ui.menuTD2;

public class Main {

    public static void main(String[] args) {
        //create window TD1
        //new menuTD1();

        //create window TD2
        //new menuTD2();

        ProduitSQL.getInstance().findAll();
    }
}