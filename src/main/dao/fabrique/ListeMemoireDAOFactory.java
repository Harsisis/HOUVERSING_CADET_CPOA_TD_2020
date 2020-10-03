package main.dao.fabrique;

import main.dao.ListMemoireDAO.ListMemoireCategoryDAO;
import main.dao.ListMemoireDAO.ListMemoireClientDAO;
import main.dao.ListMemoireDAO.ListMemoireProduitDAO;
import main.dao.metiersDAO.CategoryDAO;
import main.dao.metiersDAO.ClientDAO;
import main.dao.metiersDAO.ProduitDAO;

public class ListeMemoireDAOFactory extends DAOFactory {

    @Override
    public CategoryDAO getCategoryDAO() {
        return ListMemoireCategoryDAO.getInstance();
    }
    @Override
    public ClientDAO getClientDAO() {
        return ListMemoireClientDAO.getInstance();
    }
    @Override
    public ProduitDAO getProduitDAO() {
        return ListMemoireProduitDAO.getInstance();
    }
}
