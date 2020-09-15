package main.ui;
import main.db.Customer;
import main.db.Product;

import java.util.Scanner;
public class applimenu {
    Scanner scan = new Scanner(System.in);
    public applimenu() {
        menu1();
    }

    //menu to manage if the user want a category, a product or a customer
    public void menu1(){
        System.out.println("Hello, what do you want to do ?\n\t1. Category\n\t2. Product\n\t3. Customer");
        String choice;
        do {
            System.out.println("Select a number between 1 and 3 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3"));

        switch(choice){
            case "1": menuCat();
                break;
            case "2": menuProd();
                break;
            case "3": menuCust();
                break;
        }
    }

    //menu to manage Customers
    private void menuCust() {
        System.out.println("What do you want to do now ?\n\t1. Add a Customer\n\t2. Edit a Customer\n\t3. Disp all the Customers\n\t4. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 4 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4"));

        Customer customer = new Customer();

        switch(choice){
            case "1": customer.add_cust();
                break;
            case "2": customer.edit_cust();
                break;
            case "3": customer.all_cust();
                break;
            case "4": menu1();
                break;
        }
    }

    //menu to manage Products
    private void menuProd() {
            System.out.println("What do you want to do now ?\n\t1. Add a Product\n\t2. Edit a Product\n\t3. Disp all the Products\n\t4. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 4 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4"));

        Product product = new Product();

        switch(choice) {
            case "1": product.add_prod();
                break;
            case "2": product.edit_prod();
                break;
            case "3":
                break;
            case "4":
                menu1();
                break;
        }
    }

    //menu to manage Categories
    private void menuCat() {
        System.out.println("What do you want to do now ?\n\t1. Add a Category\n\t2. Edit a Category\n\t3. Disp all the Categories\n\t4. Back");
        String choice;
        do {
            System.out.println("Select a number between 1 and 4 please");
            choice = scan.next();
            System.out.println(choice);
        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4"));

        switch(choice){
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4": menu1();
                break;
        }
    }

}
