package test.ListeMemoire;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import main.pojo.Client;
import main.pojo.Commande;
import main.pojo.Produit;
import org.junit.Before;
import org.junit.Test;

public class TestMLCommande {

    private Commande commande;

    @Before
    public void setUp() {
        createCommande();
    }

    private void createCommande() {
        Client client = new Client();
        this.commande = new Commande(client.getId(), client);
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
        // La commande appartient bien à un client ..
        assertNotNull(commande.getClient());
        // Mon panier contient bien un article ...
        assertEquals(1, commande.getProduits().size());
    }

    @Test
    public void testTotalCommande() {
        assertEquals(30d, commande.getMontantTotal());
    }
}

