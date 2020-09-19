package main.pojo;

import java.time.LocalDate;
import java.util.HashMap;

public class Commande {
    private int id;
    private LocalDate date;

    //une commande est réalisé par un client :
    private Client idClient;

    //Ligne de commande : liste des produtis
    //Une commande contient '1' ou 'n' produits
    private HashMap<Produit, Integer> produitIntegerHashMap;

}
