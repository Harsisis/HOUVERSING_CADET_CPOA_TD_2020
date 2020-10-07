package main.dao.metiersDAO;

import main.dao.IDAO.IDAO;
import main.pojo.Categorie;

import java.util.ArrayList;

public interface CategoryDAO extends IDAO<Categorie> {

    boolean delete(Categorie objet);

    Categorie getById(int id);

    ArrayList<Categorie> findAll();

    boolean create(Categorie objet);

    boolean update(Categorie objet);
}
