package main.dao.ListMemoireDAO;

import main.dao.metiersDAO.CategoryDAO;
import main.pojo.Category;

import java.util.ArrayList;
import java.util.List;

public class ListMemoireCategoryDAO implements CategoryDAO {

    private static ListMemoireCategoryDAO instance;
    private List<Category> donnees = new ArrayList<Category>();

    public static CategoryDAO getInstance() {
        if (instance == null) {
            instance = new ListMemoireCategoryDAO();
        }
        return instance;
    }

    @Override
    public boolean delete(Category objet) {
        Category supprime;
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'une categorie inexistante");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }

    @Override
    public Category getById(int id) {
        int idx = this.donnees.indexOf(new Category(id, "test", "test.png"));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucune categorie ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public ArrayList<Category> findAll() {
        System.out.println(this.donnees);
        return (ArrayList<Category>) this.donnees;
    }

    @Override
    public boolean create(Category objet) {
        objet.setId(1);
        while (this.donnees.contains(objet)) {
            objet.setId(objet.getId() + 1);
        }
        boolean ok = this.donnees.add(objet);
        return true;
    }

    @Override
    public boolean update(Category objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'une categorie inexistante");
        } else {

            this.donnees.set(idx, objet);
        }
        return true;
    }
}
