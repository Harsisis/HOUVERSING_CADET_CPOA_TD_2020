package main.dao.ListMemoire;

import main.pojo.Category;
import main.pojo.Produit;

import java.util.ArrayList;
import java.util.List;

public class ListMemoireProduitsDAO implements ProduitDAO {

    private static ListMemoireProduitsDAO instance;
    private List<Produit> donnees;

    @Override
    public Produit getById(int id) {
        return null;
    }

    @Override
    public ArrayList<Produit> findAll() {
        return null;
    }

    @Override
    public boolean create(Produit objet) {
        return false;
    }

    @Override
    public boolean update(Produit objet) {
        return false;
    }
}
