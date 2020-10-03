package main.dao.fabrique;

import main.dao.classeSQL.CategorySQLDAO;
import main.dao.classeSQL.ClientSQLDAO;
import main.dao.classeSQL.ProduitSQLDAO;
import main.dao.metiersDAO.CategoryDAO;
import main.dao.metiersDAO.ClientDAO;
import main.dao.metiersDAO.ProduitDAO;

public class MySQLDAOFactory extends DAOFactory{

    @Override
    public CategoryDAO getCategoryDAO() {
        return (CategoryDAO) CategorySQLDAO.getInstance();
    }
    @Override
    public ClientDAO getClientDAO() {
        return (ClientDAO) ClientSQLDAO.getInstance();
    }
    @Override
    public ProduitDAO getProduitDAO() {
        return ProduitSQLDAO.getInstance();
    }
}
