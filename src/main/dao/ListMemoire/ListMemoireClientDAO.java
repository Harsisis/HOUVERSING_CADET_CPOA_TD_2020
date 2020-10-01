package main.dao.ListMemoire;

import main.dao.metiersDAO.ClientDAO;
import main.dao.metiersDAO.ProduitDAO;
import main.pojo.Client;
import main.pojo.Commande;

import java.util.ArrayList;
import java.util.List;

public class ListMemoireClientDAO implements ClientDAO {

    private static ListMemoireClientDAO instance;
    private List<Client> donnees;

    public static ClientDAO getInstance() {
        if (instance == null) {
            instance = new ListMemoireClientDAO();
        }
        return instance;
    }

    @Override
    public boolean delete(Client objet) {
        Client supprime;
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un client inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }

    @Override
    public Client getById(int id) {
        int idx = this.donnees.indexOf(new Client(id, "test", "test", new ArrayList<Commande>()));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun client ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public ArrayList<Client> findAll() {
        System.out.println((ArrayList<Client>) this.donnees);
        return (ArrayList<Client>) this.donnees;
    }

    @Override
    public boolean create(Client objet) {
        objet.setId(1);
        while (this.donnees.contains(objet)) {

            objet.setId(objet.getId() + 1);
        }
        boolean ok = this.donnees.add(objet);

        return true;
    }

    @Override
    public boolean update(Client objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un client inexistant");
        } else {

            this.donnees.set(idx, objet);
        }
        return true;
    }
}
