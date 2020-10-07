package test.ListeMemoire;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.ClientDAO;
import main.dao.metiersDAO.CommandeDAO;
import main.pojo.Client;
import main.pojo.Commande;
import main.pojo.Commande;
import main.pojo.Produit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestMLCommande {

    private Commande commande;
    private CommandeDAO dao;
    private EPersistence ePersistence = EPersistence.LISTEMEMORY;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getCommandeDAO();
        Client client = new Client();
        this.commande = new Commande();
        client.setId(1);
        client.setNom("JOYEUX");
        client.setPrenom("Stéphane");
        // J'affecte un client à une commande :
        commande.setClient(client);
        Produit produit = new Produit();
        produit.setId(1);
        produit.setNom("souris");
        produit.setTarif(10f);
        // Je commande une souris :
        commande.addProduit(produit, 3);
    }

    @Test
    public void testCommandeIsValid() {
        // La commande appartient bien à un commande ..
        assertNotNull(commande.getClient());
        // Mon panier contient bien un article ...
        assertEquals(1, commande.getProduits().size());
    }

    @Test
    public void testTotalCommande() {
        assertEquals(30d, commande.getMontantTotal());
    }

    @Test
    public void testFindByID() {
        Commande commandeA = new Commande();
        //create a commandeA
        Assert.assertTrue(dao.create(commandeA));
        //create a commandeB
        Commande commandeB = dao.getById(commandeA.getId());
        //test if commandeB is an instance of the class Commande
        Assert.assertTrue(commandeB instanceof Commande);
    }

    @Test
    public void testFindAll() {
        ArrayList commandeArrayList = dao.findAll();
        Commande commande = new Commande();
        dao.create(commande);
        System.out.println(commandeArrayList);
        Assert.assertTrue(commandeArrayList.contains(commande));
    }

    @Test
    public void testCreateCommande() {
        //get the size of the array
        int size = dao.findAll().size();
        Commande commande = new Commande();
        Assert.assertTrue(dao.create(commande));
        //check if the array is incremented
        Assert.assertEquals(size + 1, dao.findAll().size());
    }

    @Test
    public void testUpdateCommande() {
        Commande commandeA = new Commande();
        commandeA.setId(4);
        commandeA.setDate(LocalDate.now());
        //commandeA is created
        Assert.assertTrue(dao.create(commandeA));
        //test if the two products are the same
        Commande commandeB = dao.getById(commandeA.getId());
        Assert.assertEquals(commandeA,commandeB);
        //modifying commandeB
        commandeB.setDate(LocalDate.now().minusDays(1));
        Assert.assertTrue(dao.update(commandeB));
        //verify
        Assert.assertEquals(commandeB,dao.getById(commandeA.getId()));
    }

    @Test
    public void testDeleteCommande() {
        //get the size of the array
        Commande commande = new Commande();
        Assert.assertTrue(dao.create(commande));
        int size = dao.findAll().size();
        Assert.assertTrue(dao.delete(commande));
        //check if the array is incremented
        Assert.assertEquals(size - 1, dao.findAll().size());
    }
}

