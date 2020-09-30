package main.pojo;

import java.util.List;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Category{" +
                "idCategorie=" + idCategorie +
                ", titre='" + titre + '\'' +
                ", visuel='" + visuel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return idCategorie == category.idCategorie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategorie);
    }
}
