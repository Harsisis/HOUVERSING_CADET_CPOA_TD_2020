package main.dao.metiersDAO;

import main.dao.IDAO.IDAO;
import main.pojo.Client;
import main.pojo.Commande;

import java.util.ArrayList;

public interface ClientDAO extends IDAO<Client> {
    boolean delete(Client objet);

    Client getById(int id);

    ArrayList<Client> findAll();

    boolean create(Client objet);

    boolean update(Client objet);

}
