package main.pojo;

import java.time.LocalDate;
import java.util.List;

public class Commande {
    private int id;
    private LocalDate date;
    private int idClient;
    private List<Produit> produits;
}
