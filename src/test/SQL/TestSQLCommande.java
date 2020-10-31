package test.SQL;

import main.dao.SQLDAO.SQLClientDAO;
import main.dao.SQLDAO.SQLCommandeDAO;
import main.dao.SQLDAO.SQLProduitDAO;
import main.dao.fabrique.DAOFactory;
import main.modele.Connection;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.CommandeDAO;
import main.pojo.Commande;
import main.pojo.Produit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestSQLCommande {

    private CommandeDAO dao;
    private EPersistence ePersistence = EPersistence.MYSQL;

    @Before
    public void setUp() throws SQLException {
        dao = DAOFactory.getDAOFactory(ePersistence).getCommandeDAO();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(dao.findAll());
    }

    @Test
    public void testCommandeIsASingleton() {
        //GIVEN
        CommandeDAO commandeDAO1 = SQLCommandeDAO.getInstance();
        CommandeDAO commandeDAO2 = SQLCommandeDAO.getInstance();
        //THEN
        assertEquals(commandeDAO1, commandeDAO2);
    }

    @Test
    public void testCreateDeleteCofinalmmande() throws SQLException {
        ArrayList<Commande> commandes = SQLCommandeDAO.getInstance().findAll();
        int size = commandes.size();
        System.out.println(commandes);
        System.out.println(size);
        int id;
        Commande commande = new Commande();
        commande.setClient(SQLClientDAO.getInstance().getById(1));
        commande.setDate(LocalDate.now());
        Produit produit = SQLProduitDAO.getInstance().getById(1);
        commande.addProduit(produit, 1);
        Assert.assertTrue(dao.create(commande));
        Statement statement = Connection.getConnexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Commande";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        id = resultSet.getInt("id_commande");
        dao.delete(dao.getById(id));
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void testupdateCommande() throws SQLException {
        int size = dao.findAll().size();
        int idUp = 0;
        int idDel = 0;
        Commande commandeA = new Commande();
        commandeA.setClient(SQLClientDAO.getInstance().getById(1));
        commandeA.setDate(LocalDate.now());
        //getting generated key
        String request = "INSERT INTO Commande(date_commande, id_client) VALUES(?, ?)";
        PreparedStatement preparedStatement = Connection.getConnexion().prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setDate(1, Date.valueOf(commandeA.getDate()));
        preparedStatement.setInt(2, commandeA.getClient().getId());
        preparedStatement.executeUpdate();
        ResultSet resultSetKey = preparedStatement.getGeneratedKeys();
        if (resultSetKey.next()) {
            idUp = resultSetKey.getInt(1);
        }
        commandeA.setId(idUp);
        Commande commandeB = dao.getById(idUp);
        System.out.println("A : " + commandeA.getId());
        System.out.println("B : " + commandeB.getId());
        Assert.assertEquals(commandeA, commandeB);
        commandeB.setDate(LocalDate.now().plusDays(1));
        Assert.assertTrue(dao.update(commandeB));
        //verify
        Assert.assertEquals(commandeB,commandeA);
        Statement statement = Connection.getConnexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Commande";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        idDel = resultSet.getInt("id_commande");
        dao.delete(dao.getById(idDel));
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void FindAllCommande() throws SQLException {
        int id;
        int size = dao.findAll().size();
        System.out.println(size);
        Commande commande = new Commande();
        commande.setClient(SQLClientDAO.getInstance().getById(1));
        commande.setDate(LocalDate.now());
        Produit produit = SQLProduitDAO.getInstance().getById(1);
        commande.addProduit(produit, 1);
        dao.create(commande);
        Assert.assertEquals(size + 1, dao.findAll().size());
        Statement statement = Connection.getConnexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Commande";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        id = resultSet.getInt("id_commande");
        commande.setId(id);
        Assert.assertTrue(dao.findAll().contains(commande));
        dao.delete(commande);
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void GetById() throws SQLException {
        int size = dao.findAll().size();
        int idDel = 0;
        int id = 0;
        Commande commande = new Commande();
        commande.setClient(SQLClientDAO.getInstance().getById(1));
        commande.setDate(LocalDate.now());
        //getting generated key
        String request = "INSERT INTO Commande(date_commande, id_client) VALUES(?, ?)";
        PreparedStatement preparedStatement = Connection.getConnexion().prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setDate(1, Date.valueOf(commande.getDate()));
        preparedStatement.setInt(2, commande.getClient().getId());
        preparedStatement.executeUpdate();
        ResultSet resultSetKey = preparedStatement.getGeneratedKeys();
        if (resultSetKey.next()) {
            id = resultSetKey.getInt(1);
        }
        commande = dao.getById(id);
        Assert.assertNotNull(commande);
        Statement statement = Connection.getConnexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Commande";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        idDel = resultSet.getInt("id_commande");
        dao.delete(dao.getById(idDel));
        Assert.assertEquals(size, dao.findAll().size());
    }
}
