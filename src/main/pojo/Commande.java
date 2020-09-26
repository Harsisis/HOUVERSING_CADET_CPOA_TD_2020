package main.pojo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public Double getMontantTotal() {
        Double resultat = 0d;
        for (Map.Entry<Produit, Integer> entry : produits.entrySet()) {
            Produit produit = entry.getKey();
            Integer quantite = entry.getValue();
            resultat += (produit.getTarif() * quantite);
        }
        return resultat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commande)) return false;
        Commande commande = (Commande) o;
        return id == commande.id &&
                Objects.equals(client, commande.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client);
    }
}
