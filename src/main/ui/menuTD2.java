package main.ui;

import java.util.ArrayList;
import java.util.Scanner;

import main.dao.classeSQL.CategorySQL;
import main.dao.classeSQL.ClientSQL;
import main.dao.classeSQL.ProduitSQL;
import main.dao.fabrique.EPersistence;
import main.dao.fabrique.DAOFactory;
import main.dao.ListMemoire.*;
import main.pojo.Category;
import main.pojo.Client;
import main.pojo.Produit;

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

        int id = 0;
        Client client = new Client();

        switch(choice){
            case "1":
                System.out.println("Prompt the new client name :\n");
                client.setNom(scan.next());
                System.out.println("Prompt the new client surname :\n");
                client.setPrenom(scan.next());
                ClientSQL.getInstance().create(client);
                break;
            case "2":
                System.out.println("Wich client id would you update ?\n");
                id = scan.nextInt();
                client = ClientSQL.getInstance().getById(id);
                ClientSQL.getInstance().update(client);
                break;
            case "3":
                System.out.println("Wich client id would you delete ?\n");
                id = scan.nextInt();
                client = ClientSQL.getInstance().getById(id);
                ClientSQL.getInstance().delete(client);
                break;
            case "4": ClientSQL.getInstance().findAll();
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

        int id = 0;
        Produit produit = new Produit();

        switch(choice) {
            case "1":
                System.out.println("Prompt the product name :\n");
                produit.setNom(scan.next());
                System.out.println("Prompt the product description :\n");
                produit.setDescription(scan.next());
                System.out.println("Prompt the product price :\n");
                produit.setTarif(Float.parseFloat(scan.next()));
                System.out.println("Prompt the product visual :\n");
                produit.setVisuel(scan.next());
                ProduitSQL.getInstance().create(produit);
                break;
            case "2":
                System.out.println("Wich product id would you update ?\n");
                id = scan.nextInt();
                produit = ProduitSQL.getInstance().getById(id);
                ProduitSQL.getInstance().update(produit);
                break;
            case "3":
                System.out.println("Wich product id would you delete ?\n");
                id = scan.nextInt();
                produit = ProduitSQL.getInstance().getById(id);
                ProduitSQL.getInstance().delete(produit);
                break;
            case "4": ProduitSQL.getInstance().findAll();
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

        int id = 0;
        Category category = new Category();

        switch(choice){
            case "1":
                CategorySQL.getInstance().create(category);
                break;
            case "2":
                System.out.println("Wich category id would you update ?\n");
                id = scan.nextInt();
                category = CategorySQL.getInstance().getById(id);
                CategorySQL.getInstance().update(category);
                break;
            case "3":
                System.out.println("Wich category id would you delete ?\n");
                id = scan.nextInt();
                category = CategorySQL.getInstance().getById(id);
                CategorySQL.getInstance().delete(category);
                break;
            case "4": CategorySQL.getInstance().findAll();
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

        Client client = new Client();
        switch(choice){
            case "1":
                System.out.println("Prompt the client name :\n");
                client.setNom(scan.next());
                System.out.println("Prompt the client surname :\n");
                client.setPrenom(scan.next());
                System.out.println(client);
                ListMemoireClientDAO.getInstance().create(client);
                menu2();
                break;
            case "2":
                System.out.println("Prompt the client id :\n");
                client = ListMemoireClientDAO.getInstance().getById(scan.nextInt());
                System.out.println("Prompt the new client name :\n");
                client.setNom(scan.next());
                System.out.println("Prompt the new client surname :\n");
                client.setPrenom(scan.next());
                ListMemoireClientDAO.getInstance().update(client);
                menu2();
                break;
            case "3":
                System.out.println("Prompt the client id :\n");
                client = ListMemoireClientDAO.getInstance().getById(scan.nextInt());
                ListMemoireClientDAO.getInstance().delete(client);
                menu2();
                break;
            case "4": ListMemoireClientDAO.getInstance().findAll();
                menu2();
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

        Produit produit = new Produit();

        switch(choice) {
            case "1":
                System.out.println("Prompt the product name :\n");
                produit.setNom(scan.next());
                System.out.println("Prompt the product description :\n");
                produit.setDescription(scan.next());
                System.out.println("Prompt the product price :\n");
                produit.setTarif(Float.parseFloat(scan.next()));
                System.out.println("Prompt the product visual :\n");
                produit.setVisuel(scan.next());
                ListMemoireProduitDAO.getInstance().create(produit);
                menu2();
                break;
            case "2":
                System.out.println("Prompt the product id :\n");
                produit = ListMemoireProduitDAO.getInstance().getById(scan.nextInt());
                System.out.println("Prompt the product name :\n");
                produit.setNom(scan.next());
                System.out.println("Prompt the product description :\n");
                produit.setDescription(scan.next());
                System.out.println("Prompt the product price :\n");
                produit.setTarif(Float.parseFloat(scan.next()));
                System.out.println("Prompt the product visual :\n");
                produit.setVisuel(scan.next());
                ListMemoireProduitDAO.getInstance().update(produit);
                menu2();
                break;
            case "3":
                System.out.println("Prompt the product id :\n");
                produit = ListMemoireProduitDAO.getInstance().getById(scan.nextInt());
                ListMemoireProduitDAO.getInstance().delete(produit);
                menu2();
                break;
            case "4": ListMemoireProduitDAO.getInstance().findAll();
                menu2();
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

        Category category = new Category();

        switch(choice){
            case "1":
                System.out.println("Prompt the category title :\n");
                category.setTitre(scan.next());
                System.out.println("Prompt the category visual :\n");
                category.setVisuel(scan.next());
                ListMemoireCategoryDAO.getInstance().create(category);
                menu2();
                break;
            case "2":
                System.out.println("Prompt the category id :\n");
                category = ListMemoireCategoryDAO.getInstance().getById(scan.nextInt());
                System.out.println("Prompt the category title :\n");
                category.setTitre(scan.next());
                System.out.println("Prompt the category visual :\n");
                category.setVisuel(scan.next());
                ListMemoireCategoryDAO.getInstance().update(category);
                menu2();
                break;
            case "3":
                System.out.println("Prompt the category id :\n");
                category = ListMemoireCategoryDAO.getInstance().getById(scan.nextInt());
                ListMemoireCategoryDAO.getInstance().delete(category);
                menu2();
                break;
            case "4": ListMemoireCategoryDAO.getInstance().findAll();
                menu2();
                break;
            case "5": menu2();
                break;
        }
    }
}

    
