package main.pojo;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private Map<Produit, Integer> produits;

    public Commande(int id, LocalDate date, Client client, Map<Produit, Integer> produits) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.produits = produits;
    }

    public Commande(int id, LocalDate date, Client client) {
        Map<Produit, Integer> produits = new HashMap<Produit, Integer>();
        this.id = id;
        this.date = date;
        this.client = client;
        this.produits = produits;
    }

    /* @return le montant total du panier (ligne de commande). */
    public Double getMontantTotal() {
        // Parcours de la table des produits ...ligne de commande.
        Double resultat = 0d;
        for (Map.Entry<Produit, Integer> entry : produits.entrySet()) {
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

    public Map<Produit, Integer> getProduits() {
        return produits;
    }
}