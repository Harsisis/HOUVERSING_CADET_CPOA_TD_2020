//package main.ui;
//import main.dao.classeSQL.CategorySQL;
//import main.dao.classeSQL.ClientSQL;
//import main.dao.classeSQL.ProduitSQL;
//
//import java.util.Scanner;
//public class menuTD1 {
//    Scanner scan = new Scanner(System.in);
//    public menuTD1() {
//        menu1();
//    }
//
//    //menu to manage if the user want a category, a product or a client
//    public void menu1(){
//        System.out.println("Hello, what do you want to do ?\n\t1. Category\n\t2. Product\n\t3. Client\n\t4. Exit");
//        String choice;
//        do {
//            System.out.println("Select a number between 1 and 4 please");
//            choice = scan.next();
//            System.out.println(choice);
//        } while(choice.equals("1") && choice.equals("2") && choice.equals("3"));
//
//        switch(choice){
//            case "1": menuCat();
//                break;
//            case "2": menuProd();
//                break;
//            case "3": menuClient();
//                break;
//            case "4": return;
//        }
//    }
//
//    //menu to manage Clientomers
//    private void menuClient() {
//        System.out.println("What do you want to do now ?\n\t1. Add a Client\n\t2. Edit a Client\n\t3. Delete a Client\n\t4. Display all the Clients\n\t5. Back");
//        String choice;
//        do {
//            System.out.println("Select a number between 1 and 5 please");
//            choice = scan.next();
//            System.out.println(choice);
//        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));
//
//        ClientSQL clientSQL = new ClientSQL();
//
//        switch(choice){
//            case "1": clientSQL.add_client();
//                break;
//            case "2": clientSQL.edit_client();
//                break;
//            case "3": clientSQL.del_client();
//                break;
//            case "4": clientSQL.add_client();
//                break;
//            case "5": menu1();
//                break;
//        }
//    }
//
//    //menu to manage Products
//    private void menuProd() {
//            System.out.println("What do you want to do now ?\n\t1. Add a Product\n\t2. Edit a Product\n\t3. Delete a Product\n\t4. Disp all the Products\n\t5. Back");
//        String choice;
//        do {
//            System.out.println("Select a number between 1 and 5 please");
//            choice = scan.next();
//            System.out.println(choice);
//        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));
//
//        ProduitSQL produitSQL = new ProduitSQL();
//
//        switch(choice) {
//            case "1": produitSQL.add_prod();
//                break;
//            case "2": produitSQL.edit_prod();
//                break;
//            case "3": produitSQL.del_prod();
//                break;
//            case "4": produitSQL.all_prod();
//                break;
//            case "5":
//                menu1();
//                break;
//        }
//    }
//
//    //menu to manage Categories
//    private void menuCat() {
//        System.out.println("What do you want to do now ?\n\t1. Add a Category\n\t2. Edit a Category\n\t3. Delete a Category\n\t4. Disp all the Categories\n\t5. Back");
//        String choice;
//        do {
//            System.out.println("Select a number between 1 and 5 please");
//            choice = scan.next();
//            System.out.println(choice);
//        } while(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") && choice.equals("5"));
//        CategorySQL categorySQL = CategorySQL.getInstance();
//
//        switch(choice){
//            case "1": categorySQL.add_cat();
//                break;
//            case "2": categorySQL.edit_cat();
//                break;
//            case "3": categorySQL.del_cat();
//                break;
//            case "4": categorySQL.all_cat();
//                break;
//            case "5": menu1();
//                break;
//        }
//    }
//
//}