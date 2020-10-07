package main.dao.metiersDAO;

import main.dao.IDAO.IDAO;
import main.pojo.Commande;
import java.util.ArrayList;

public interface CommandeDAO extends IDAO<Commande> {

    boolean delete(Commande objet);

    Commande getById(int id);

    ArrayList<Commande> findAll();

    boolean create(Commande objet);

    boolean update(Commande objet);
}
