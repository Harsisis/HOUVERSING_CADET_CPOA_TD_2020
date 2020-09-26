package main.dao.metiersDAO;

import main.dao.IDAO.IDAO;
import main.pojo.Produit;
import java.util.ArrayList;

public interface ProduitDAO extends IDAO<Produit> {
    boolean delete(Produit objet);

    Produit getById(int id);

    ArrayList<Produit> findAll();

    boolean create(Produit objet);

    boolean update(Produit objet);
}
