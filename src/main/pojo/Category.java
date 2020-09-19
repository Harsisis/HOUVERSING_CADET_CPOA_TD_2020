package main.pojo;

import java.util.List;

public class Category {
    private int idCategorie;
    private String titre;
    private String visuel;
    private List<Produit> produits;

    public Category(int idCategorie, String titre, String visuel) {
        this.idCategorie = idCategorie;
        this.titre = titre;
        this.visuel = visuel;
    }


}
