package main.pojo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commande {
    private int id;
    private LocalDate date;
    private int idClient;

    private Map<Produit, Integer> produits;

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

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Map<Produit, Integer> getProduits() {
        return produits;
    }

    public void setProduits(Map<Produit, Integer> produits) {
        this.produits = produits;
    }


    public Double getMontantTotal(){
        double resultat = 0;
        for (Map.Entry<Produit, Integer> entry : produits.entrySet()){
            Produit produit = entry.getKey();
            Integer quantite = entry.getValue();
            resultat += (produit.getTarif() * quantite);
        }
        return resultat;
    }
}
