package main.dao.ListMemoireDAO;

import main.dao.metiersDAO.CategorieDAO;
import main.pojo.Categorie;

import java.util.ArrayList;
import java.util.List;

public class ListMemoireCategorieDAO implements CategorieDAO {

    private static ListMemoireCategorieDAO instance;
    private List<Categorie> donnees = new ArrayList<Categorie>();

    public static CategorieDAO getInstance() {
        if (instance == null) {
            instance = new ListMemoireCategorieDAO();
        }
        return instance;
    }

    @Override
    public boolean delete(Categorie objet) {
        Categorie supprime;
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'une categorie inexistante");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }

    @Override
    public Categorie getById(int id) {
        int idx = this.donnees.indexOf(new Categorie(id, "test", "test.png"));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucune categorie ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public ArrayList<Categorie> findAll() {
        return (ArrayList<Categorie>) this.donnees;
    }

    @Override
    public boolean create(Categorie objet) {
        objet.setId(1);
        while (this.donnees.contains(objet)) {
            objet.setId(objet.getId() + 1);
        }
        boolean ok = this.donnees.add(objet);
        return true;
    }

    @Override
    public boolean update(Categorie objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'une categorie inexistante");
        } else {

            this.donnees.set(idx, objet);
        }
        return true;
    }
}
