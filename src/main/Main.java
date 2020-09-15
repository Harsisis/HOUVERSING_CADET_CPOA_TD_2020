package main;

import main.db.Category;
//import main.ui.applimenu;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        //create window
        //new applimenu();
        Category category = new Category();
        category.add_cat();
    }
}