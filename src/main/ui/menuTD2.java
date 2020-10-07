package main.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

import main.dao.SQLDAO.SQLCategorieDAO;
import main.dao.SQLDAO.SQLClientDAO;
import main.dao.SQLDAO.SQLProduitDAO;
import main.dao.fabrique.EPersistence;
import main.dao.fabrique.DAOFactory;
import main.dao.ListMemoireDAO.*;
import main.pojo.Categorie;
import main.pojo.Client;
import main.pojo.Commande;
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
                System.out.println("Prompt the new client family name :\n");
                client.setNom(scan.next());
                System.out.println("Prompt the new client name :\n");
                client.setPrenom(scan.next());
                SQLClientDAO.getInstance().create(client);
                break;
            case "2":
                System.out.println("Which client id would you like to update ?\n");
                id = scan.nextInt();
                client = SQLClientDAO.getInstance().getById(id);
                System.out.println("Prompt the new client family name :\n");
                client.setNom(scan.next());
                System.out.println("Prompt the new client name :\n");
                client.setPrenom(scan.next());
                SQLClientDAO.getInstance().update(client);
                break;
            case "3":
                System.out.println("Which client id would you like to delete ?\n");
                id = scan.nextInt();
                client = SQLClientDAO.getInstance().getById(id);
                SQLClientDAO.getInstance().delete(client);
                break;
            case "4": SQLClientDAO.getInstance().findAll();
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
                SQLProduitDAO.getInstance().create(produit);
                break;
            case "2":
                System.out.println("Which product id would you like to update ?\n");
                id = scan.nextInt();
                produit = SQLProduitDAO.getInstance().getById(id);
                System.out.println("Prompt the product name :\n");
                produit.setNom(scan.next());
                System.out.println("Prompt the product description :\n");
                produit.setDescription(scan.next());
                System.out.println("Prompt the product price :\n");
                produit.setTarif(Float.parseFloat(scan.next()));
                System.out.println("Prompt the product visual :\n");
                produit.setVisuel(scan.next());
                System.out.println("Prompt the product category :\n");
                produit.setCategory(new Categorie(scan.nextInt()));
                SQLProduitDAO.getInstance().update(produit);
                break;
            case "3":
                System.out.println("Which product id would you like to delete ?\n");
                id = scan.nextInt();
                produit = SQLProduitDAO.getInstance().getById(id);
                SQLProduitDAO.getInstance().delete(produit);
                break;
            case "4": SQLProduitDAO.getInstance().findAll();
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
        Categorie categorie = new Categorie();

        switch(choice){
            case "1":
                System.out.println("Prompt the category title :\n");
                categorie.setTitre(scan.next());
                System.out.println("Prompt the category visual :\n");
                categorie.setVisuel(scan.next());
                SQLCategorieDAO.getInstance().create(categorie);
                break;
            case "2":
                System.out.println("Which category id would you like to update ?\n");
                id = scan.nextInt();
                categorie = SQLCategorieDAO.getInstance().getById(id);
                System.out.println("Prompt the category title :\n");
                categorie.setTitre(scan.next());
                System.out.println("Prompt the category visual :\n");
                categorie.setVisuel(scan.next());
                SQLCategorieDAO.getInstance().update(categorie);
                break;
            case "3":
                System.out.println("Which category id would you like to delete ?\n");
                id = scan.nextInt();
                categorie = SQLCategorieDAO.getInstance().getById(id);
                SQLCategorieDAO.getInstance().delete(categorie);
                break;
            case "4": SQLCategorieDAO.getInstance().findAll();
                break;
            case "5": menu2();
                break;
        }
    }

    //----------------------------------------------------------------------------
    public void menuML(){
        System.out.println("Select a type\n\t1. Category\n\t2. Product\n\t3. Client\n\t4. Order\n\t5. Exit");
        String choice;
        do {
            System.out.println("Select a number between 1 and 5 please");
            choice = scan.next();
            System.out.println(choice);
        } while (choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));

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
                menuCommandeML();
                break;
            case "5":
                return;
        }
    }

    private void menuCommandeML() {
        System.out.println("Select an action\n\t1. Add an Order\n\t2. Edit an Order\n\t3. Delete an Order\n\t4. Display all the Orders\n\t5. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 5 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));

        Commande commande = new Commande();
        switch(choice){
            case "1":
                System.out.println("Prompt the order date :\n");
                commande.setDate(LocalDate.parse( scan.next(), DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
                System.out.println("All the order :\n");
                ListMemoireClientDAO.getInstance().findAll();
                System.out.println("Prompt the order id :\n");
                commande.setClient(ListMemoireClientDAO.getInstance().getById(scan.nextInt()));

                ListMemoireCommandeDAO.getInstance().create(commande);
                menu2();
                break;
            case "2":
                System.out.println("Prompt the order id :\n");
                commande = ListMemoireCommandeDAO.getInstance().getById(scan.nextInt());
                System.out.println("Prompt the order date :\n");
                commande.setDate(LocalDate.parse( scan.next(), DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
                System.out.println("All the order :\n");
                ListMemoireClientDAO.getInstance().findAll();
                System.out.println("Prompt the order id :\n");
                commande.setClient(ListMemoireClientDAO.getInstance().getById(scan.nextInt()));
                ListMemoireCommandeDAO.getInstance().update(commande);
                menu2();
                break;
            case "3":
                System.out.println("Prompt the order id :\n");
                commande = ListMemoireCommandeDAO.getInstance().getById(scan.nextInt());
                ListMemoireCommandeDAO.getInstance().delete(commande);
                menu2();
                break;
            case "4": ListMemoireCommandeDAO.getInstance().findAll();
                menu2();
                break;
            case "5": menu2();
                break;
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
//                System.out.println("Prompt the client id :\n");
//                client = ListMemoireClientDAO.getInstance().getById(scan.nextInt());
//                System.out.println("Prompt the new client family name :\n");
//                client.setNom(scan.next());
//                System.out.println("Prompt the new client name :\n");
//                client.setPrenom(scan.next());
//                ListMemoireClientDAO.getInstance().update(client);
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

        Categorie categorie = new Categorie();

        switch(choice){
            case "1":
                System.out.println("Prompt the category title :\n");
                categorie.setTitre(scan.next());
                System.out.println("Prompt the category visual :\n");
                categorie.setVisuel(scan.next());
                ListMemoireCategorieDAO.getInstance().create(categorie);
                menu2();
                break;
            case "2":
                System.out.println("Prompt the category id :\n");
                categorie = ListMemoireCategorieDAO.getInstance().getById(scan.nextInt());
                System.out.println("Prompt the category title :\n");
                categorie.setTitre(scan.next());
                System.out.println("Prompt the category visual :\n");
                categorie.setVisuel(scan.next());
                ListMemoireCategorieDAO.getInstance().update(categorie);
                menu2();
                break;
            case "3":
                System.out.println("Prompt the category id :\n");
                categorie = ListMemoireCategorieDAO.getInstance().getById(scan.nextInt());
                ListMemoireCategorieDAO.getInstance().delete(categorie);
                menu2();
                break;
            case "4": ListMemoireCategorieDAO.getInstance().findAll();
                menu2();
                break;
            case "5": menu2();
                break;
        }
    }
}

    
