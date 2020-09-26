package main.dao.fabrique;

import main.dao.ListMemoire.ListMemoireCategoryDAO;
import main.dao.ListMemoire.ListMemoireProduitDAO;
import main.dao.metiersDAO.CategoryDAO;
import main.dao.metiersDAO.ProduitDAO;

public class ListeMemoireDAOFactory extends DAOFactory {


    @Override
    public CategoryDAO getCategoryDAO() {
        return ListMemoireCategoryDAO.getInstance();
    }

    @Override
    public ProduitDAO getProduitDAO() {
        return ListMemoireProduitDAO.getInstance();
    }
}
