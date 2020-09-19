package main.pojo;

import java.time.LocalDate;
import java.util.HashMap;

public class Commande {
    private int id;
    private LocalDate date;
    private Client client;

    private HashMap<Produit, Integer> produits;

    /* Méthode qui permet d'ajouter un produit à la ligne de commande. */
    public void addProduit(Produit produit, Integer quantite) {
        if (produits == null) {
            produits = new HashMap<>();
        }
        produits.put(produit, quantite);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public HashMap<Produit, Integer> getProduits() {
        return produits;
    }

    public void setProduits(HashMap<Produit, Integer> produits) {
        this.produits = produits;
    }
}
