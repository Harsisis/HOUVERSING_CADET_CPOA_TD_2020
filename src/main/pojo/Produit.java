package main.pojo;

import java.util.Objects;

public class Produit {
    private int id;
    private String nom;
    private String description;
    private float tarif;
    private String visuel;
    private Categorie categorie;

    public Produit(int id, String nom, String description, float tarif, String visuel, Categorie categorie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.tarif = tarif;
        this.visuel = visuel;
        this.categorie = categorie;
    }

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    public Categorie getCategory() {
        return categorie;
    }

    public void setCategory(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return nom + ", " + description + ", " + tarif + ", " + visuel +
                "\nCatégorie : " + categorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit)) return false;
        Produit produit = (Produit) o;
        return id == produit.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
