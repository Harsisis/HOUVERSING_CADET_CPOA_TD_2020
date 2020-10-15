package main.pojo;

import java.util.Objects;

public class Categorie {
    private int idCategorie;
    private String titre;
    private String visuel;

    public Categorie(int id, String titre, String visuel) {
        idCategorie = id;
        this.titre = titre;
        this.visuel = visuel;
    }

    public Categorie() {
    }

    public Categorie(int nextInt) {
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

    public void setId(int id) {
        idCategorie = id;
    }

    public int getId() {
        return idCategorie;
    }

    @Override
    public String toString() {
        return titre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categorie)) return false;
        Categorie categorie = (Categorie) o;
        return idCategorie == categorie.idCategorie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategorie);
    }
}
