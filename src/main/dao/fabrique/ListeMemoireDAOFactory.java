package main.dao.fabrique;

import main.dao.ListMemoireDAO.ListMemoireCategorieDAO;
import main.dao.ListMemoireDAO.ListMemoireClientDAO;
import main.dao.ListMemoireDAO.ListMemoireCommandeDAO;
import main.dao.ListMemoireDAO.ListMemoireProduitDAO;
import main.dao.metiersDAO.CategorieDAO;
import main.dao.metiersDAO.ClientDAO;
import main.dao.metiersDAO.CommandeDAO;
import main.dao.metiersDAO.ProduitDAO;

public class ListeMemoireDAOFactory extends DAOFactory {

    @Override
    public CategorieDAO getCategorieDAO() {
        return ListMemoireCategorieDAO.getInstance();
    }
    @Override
    public ClientDAO getClientDAO() {
        return ListMemoireClientDAO.getInstance();
    }
    @Override
    public ProduitDAO getProduitDAO() {
        return ListMemoireProduitDAO.getInstance();
    }
    @Override
    public CommandeDAO getCommandeDAO() {
        return ListMemoireCommandeDAO.getInstance();
    }
}
