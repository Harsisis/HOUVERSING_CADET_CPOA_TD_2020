package main.ui;
import main.dao.classeDao.CategoryDao;
import main.dao.classeDao.ClientDao;
import main.dao.classeDao.ProductDao;

import java.util.Scanner;
public class applimenu {
    Scanner scan = new Scanner(System.in);
    public applimenu() {
        menu1();
    }

    //menu to manage if the user want a category, a product or a client
    public void menu1(){
        System.out.println("Hello, what do you want to do ?\n\t1. Category\n\t2. Product\n\t3. Client\n\t4. Exit");
        String choice;
        do {
            System.out.println("Select a number between 1 and 4 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3"));

        switch(choice){
            case "1": menuCat();
                break;
            case "2": menuProd();
                break;
            case "3": menuClient();
                break;
            case "4": return;
        }
    }

    //menu to manage Clientomers
    private void menuClient() {
        System.out.println("What do you want to do now ?\n\t1. Add a Client\n\t2. Edit a Client\n\t3. Delete a Client\n\t4. Display all the Clients\n\t5. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 5 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));

        ClientDao clientDao = new ClientDao();

        switch(choice){
            case "1": clientDao.add_client();
                break;
            case "2": clientDao.edit_client();
                break;
            case "3": clientDao.del_client();
                break;
            case "4": clientDao.add_client();
                break;
            case "5": menu1();
                break;
        }
    }

    //menu to manage Products
    private void menuProd() {
            System.out.println("What do you want to do now ?\n\t1. Add a Product\n\t2. Edit a Product\n\t3. Delete a Product\n\t4. Disp all the Products\n\t5. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 5 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));

        ProductDao productDao = new ProductDao();

        switch(choice) {
            case "1": productDao.add_prod();
                break;
            case "2": productDao.edit_prod();
                break;
            case "3": productDao.del_prod();
                break;
            case "4": productDao.all_prod();
                break;
            case "5":
                menu1();
                break;
        }
    }

    //menu to manage Categories
    private void menuCat() {
        System.out.println("What do you want to do now ?\n\t1. Add a Category\n\t2. Edit a Category\n\t3. Delete a Category\n\t4. Disp all the Categories\n\t5. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 5 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));
        CategoryDao categoryDao = new CategoryDao();

        switch(choice){
            case "1": categoryDao.add_cat();
                break;
            case "2": categoryDao.edit_cat();
                break;
            case "3": categoryDao.del_cat();
                break;
            case "4": categoryDao.all_cat();
                break;
            case "5": menu1();
                break;
        }
    }

}
