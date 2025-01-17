package main.dao.ListMemoireDAO;

import main.dao.metiersDAO.ProduitDAO;
import main.pojo.Categorie;
import main.pojo.Produit;

import java.util.ArrayList;
import java.util.List;

public class ListMemoireProduitDAO implements ProduitDAO {

    private static ListMemoireProduitDAO instance;
    private List<Produit> donnees = new ArrayList<Produit>();

    public static ProduitDAO getInstance() {
        if (instance == null) {
            instance = new ListMemoireProduitDAO();
        }
        return instance;
    }

    @Override
    public boolean delete(Produit objet) {
        Produit supprime;
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un produit inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }

    @Override
    public Produit getById(int id) {
        int idx = this.donnees.indexOf(new Produit(id, "test", "test", (float) 0.0, "test.png", new Categorie(id, "test", "test.png")));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun produit ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public ArrayList<Produit> findAll() {
        return (ArrayList<Produit>) this.donnees;
    }

    @Override
    public boolean create(Produit objet) {
        objet.setId(1);
        while (this.donnees.contains(objet)) {
            objet.setId(objet.getId() + 1);
        }
        boolean ok = this.donnees.add(objet);
        return true;
    }

    @Override
    public boolean update(Produit objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un produit inexistant");
        } else {

            this.donnees.set(idx, objet);
        }
        return true;
    }
}
