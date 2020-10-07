package main.dao.fabrique;

import main.dao.SQLDAO.SQLCategorieDAO;
import main.dao.SQLDAO.SQLClientDAO;
import main.dao.SQLDAO.SQLProduitDAO;
import main.dao.metiersDAO.CategoryDAO;
import main.dao.metiersDAO.ClientDAO;
import main.dao.metiersDAO.CommandeDAO;
import main.dao.metiersDAO.ProduitDAO;

public class MySQLDAOFactory extends DAOFactory{

    @Override
    public CategoryDAO getCategoryDAO() {
        return SQLCategorieDAO.getInstance();
    }
    @Override
    public ClientDAO getClientDAO() {
        return SQLClientDAO.getInstance();
    }
    @Override
    public ProduitDAO getProduitDAO() {
        return SQLProduitDAO.getInstance();
    }
    @Override
    public CommandeDAO getCommandeDAO() {
        return null ;
    }
}
