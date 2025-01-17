package main.dao.ListMemoireDAO;

import main.dao.metiersDAO.CommandeDAO;
import main.pojo.Client;
import main.pojo.Commande;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListMemoireCommandeDAO implements CommandeDAO {

    private static CommandeDAO instance;
    private List<Commande> donnees = new ArrayList<Commande>();

    public static CommandeDAO getInstance() {
        if (instance == null) {
            instance = new ListMemoireCommandeDAO();
        }
        return instance;
    }

    @Override
    public boolean delete(Commande objet) {
        Commande supprime;
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
        } else {
            supprime = this.donnees.remove(idx);
        }
        return objet.equals(supprime);
    }

    @Override
    public Commande getById(int id) {
        Client client = new Client();
        LocalDate date = LocalDate.now();
        int idx = this.donnees.indexOf(new Commande(id, date, client));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucune commande ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public ArrayList<Commande> findAll() {
        return (ArrayList<Commande>) this.donnees;
    }

    @Override
    public boolean create(Commande objet) {
        objet.setId(1);
        while (this.donnees.contains(objet)) {
            objet.setId(objet.getId() + 1);
        }
        boolean ok = this.donnees.add(objet);
        return true;
    }

    @Override
    public boolean update(Commande objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'une commande inexistante");
        } else {
            this.donnees.set(idx, objet);
        }
        return true;
    }
}
