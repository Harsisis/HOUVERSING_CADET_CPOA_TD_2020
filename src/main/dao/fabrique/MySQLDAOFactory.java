package main.dao.fabrique;

import main.dao.metiersDAO.CategoryDAO;
import main.dao.metiersDAO.ClientDAO;
import main.dao.metiersDAO.ProduitDAO;

public class MySQLDAOFactory extends DAOFactory{

    @Override
    public CategoryDAO getCategoryDAO() {
        return null;
    }
    @Override
    public ClientDAO getClientDAO() {
        return null;
    }
    @Override
    public ProduitDAO getProduitDAO() {
        return null;
    }
}
