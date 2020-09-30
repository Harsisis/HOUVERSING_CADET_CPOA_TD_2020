package main.ui;

import java.util.Scanner;

import main.dao.classeSQL.CategorySQL;
import main.dao.classeSQL.ClientSQL;
import main.dao.classeSQL.ProduitSQL;
import main.dao.fabrique.EPersistence;
import main.dao.fabrique.DAOFactory;

public class menuTD2 {
    Scanner scan = new Scanner(System.in);

    public menuTD2() {
        menu2();
    }

    //menu to manage if the user want to access database or memory list
    public void menu2(){
        System.out.println("Hello, what do you want to do ?\n\t1. Database\n\t2. Memory List\n\t3. Exit");
        String choice;
        do {
            System.out.println("Select a number between 1 and 3 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3"));

        EPersistence choix;

        switch(choice){
            case "1": choix = EPersistence.MYSQL;
                menuSQL();
                break;
            case "2": choix = EPersistence.LISTEMEMORY;
                menuML();
                break;
            case "3":
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }

        DAOFactory daoFactory = DAOFactory.getDAOFactory(choix);

    }
    //----------------------------------------------------------------------------
    public void menuSQL() {
        System.out.println("Select a type\n\t1. Category\n\t2. Product\n\t3. Client\n\t4. Exit");
        String choice;
        do {
            System.out.println("Select a number between 1 and 4 please");
            choice = scan.next();
            System.out.println(choice);
        } while (choice.equals("1") && choice.equals("2") && choice.equals("3"));

        switch (choice) {
            case "1":
                menuCatSQL();
                break;
            case "2":
                menuProdSQL();
                break;
            case "3":
                menuClientSQL();
                break;
            case "4":
                return;

        }
    }

    private void menuClientSQL() {
        System.out.println("Select an action\n\t1. Add a Client\n\t2. Edit a Client\n\t3. Delete a Client\n\t4. Display all the Clients\n\t5. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 5 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));

        switch(choice){
            case "1": ClientSQL.getInstance().add_client();
                break;
            case "2": ClientSQL.getInstance().edit_client();
                break;
            case "3": ClientSQL.getInstance().del_client();
                break;
            case "4": ClientSQL.getInstance().add_client();
                break;
            case "5": menu2();
                break;
        }
    }

    private void menuProdSQL() {
        System.out.println("Select an action\n\t1. Add a Product\n\t2. Edit a Product\n\t3. Delete a Product\n\t4. Disp all the Products\n\t5. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 5 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));

        switch(choice) {
            case "1":
                ProduitSQL.getInstance().add_prod();
                break;
            case "2": ProduitSQL.getInstance().edit_prod();
                break;
            case "3": ProduitSQL.getInstance().del_prod();
                break;
            case "4": ProduitSQL.getInstance().all_prod();
                break;
            case "5":
                menu2();
                break;
        }
    }

    private void menuCatSQL() {
        System.out.println("Select an action\n\t1. Add a Category\n\t2. Edit a Category\n\t3. Delete a Category\n\t4. Disp all the Categories\n\t5. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 5 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));

        switch(choice){
            case "1":
                CategorySQL.getInstance().add_cat();
                break;
            case "2": CategorySQL.getInstance().edit_cat();
                break;
            case "3": CategorySQL.getInstance().del_cat();
                break;
            case "4": CategorySQL.getInstance().all_cat();
                break;
            case "5": menu2();
                break;
        }
    }

    //----------------------------------------------------------------------------
    public void menuML(){
        System.out.println("Select a type\n\t1. Category\n\t2. Product\n\t3. Client\n\t4. Exit");
        String choice;
        do {
            System.out.println("Select a number between 1 and 4 please");
            choice = scan.next();
            System.out.println(choice);
        } while (choice.equals("1") && choice.equals("2") && choice.equals("3"));

        switch (choice) {
            case "1":
                menuCatML();
                break;
            case "2":
                menuProdML();
                break;
            case "3":
                menuClientML();
                break;
            case "4":
                return;

        }
    }

    private void menuClientML() {
        System.out.println("Select an action\n\t1. Add a Client\n\t2. Edit a Client\n\t3. Delete a Client\n\t4. Display all the Clients\n\t5. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 5 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));

        switch(choice){
            case "1": ClientSQL.getInstance().add_client();
                break;
            case "2": ClientSQL.getInstance().edit_client();
                break;
            case "3": ClientSQL.getInstance().del_client();
                break;
            case "4": ClientSQL.getInstance().add_client();
                break;
            case "5": menu2();
                break;
        }
    }

    private void menuProdML() {
        System.out.println("Select an action\n\t1. Add a Product\n\t2. Edit a Product\n\t3. Delete a Product\n\t4. Disp all the Products\n\t5. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 5 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));

        switch(choice) {
            case "1":
                ProduitSQL.getInstance().add_prod();
                break;
            case "2": ProduitSQL.getInstance().edit_prod();
                break;
            case "3": ProduitSQL.getInstance().del_prod();
                break;
            case "4": ProduitSQL.getInstance().all_prod();
                break;
            case "5":
                menu2();
                break;
        }
    }

    private void menuCatML() {
        System.out.println("Select an action\n\t1. Add a Category\n\t2. Edit a Category\n\t3. Delete a Category\n\t4. Disp all the Categories\n\t5. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 5 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));

        switch(choice){
            case "1":
                CategorySQL.getInstance().add_cat();
                break;
            case "2": CategorySQL.getInstance().edit_cat();
                break;
            case "3": CategorySQL.getInstance().del_cat();
                break;
            case "4": CategorySQL.getInstance().all_cat();
                break;
            case "5": menu2();
                break;
        }
    }
}

    
