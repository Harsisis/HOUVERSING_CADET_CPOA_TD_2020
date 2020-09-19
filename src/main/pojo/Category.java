package main.pojo;

import java.util.List;

public class Category {
    private int idCategorie;
    private String titre;
    private String visuel;
    private List<Produit> produits;

    public Category(int id, String titre, String visuel) {
        idCategorie = id;
        this.titre = titre;
        this.visuel = visuel;

    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public void setId(int id) {
        idCategorie = id;
    }

    public int getId() {
        return idCategorie;
    }
}
