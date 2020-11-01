package main.pojo;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
 * Réprésentation métier d'une 'commande'.
 * Une commande est réalisée à une date par un client.
 * Et contient plusieurs produits.
 */
public class Commande {

    private int id;
    private LocalDate date; // java date value of
    // Une commande est réalisée par un client :
    private Client client;

    // Ligne de commande : liste des produits.
    // Une Commande contient '1 ou n' produits.
    private HashMap<Produit, Integer> produits;

    public Commande(int id, LocalDate date, Client client, HashMap<Produit, Integer> produits) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.produits = produits;
    }

    public Commande(int id, LocalDate date, Client client) {
        HashMap<Produit, Integer> produits = new HashMap<Produit, Integer>();
        this.id = id;
        this.date = date;
        this.client = client;
        this.produits = produits;
    }

    public Commande() {
    }

    public void setProduits(HashMap<Produit, Integer> produits) {
        this.produits = produits;
    }

    /* @return le montant total du panier (ligne de commande). */
    public Double getMontantTotal() {
        // Parcours de la table des produits ...ligne de commande.
        Double resultat = 0d;
        for (var entry : produits.entrySet()) {
            // Pour chaque ligne de commande je récupère la clé (produit) et la valeur
            // (quantité).
            Produit produit = entry.getKey();
            Integer quantite = entry.getValue();
            resultat += (produit.getTarif() * quantite);
        }
        return resultat;
    }

    /* Méthode qui permet d'ajouter un produit à la ligne de commande. */
    public void addProduit(Produit produit, Integer quantite) {
        if (produits == null) {
            produits = new HashMap<>();
        }
        if (produits.containsKey(produit)){
            this.produits.put(produit, produits.get(produit)+quantite);
        }
        else {
            this.produits.put(produit, quantite);
        }
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

    public Map<Produit, Integer> getProduits() {
        return produits;
    }

    @Override
    public String toString() {
        return id + ", " + date + ", " + client +
                "\nProduits : " + produits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commande)) return false;
        Commande commande = (Commande) o;
        return id == commande.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}