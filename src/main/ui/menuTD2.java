package main.ui;

import java.util.Scanner;
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
                break;
            case "2": choix = EPersistence.LISTEMEMORY;
                break;
            case "3":
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }

        DAOFactory daoFactory = DAOFactory.getDAOFactory(choix);

    }
}
