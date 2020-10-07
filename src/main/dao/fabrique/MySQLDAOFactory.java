package main.dao.fabrique;

import main.dao.SQLDAO.CategorySQLDAO;
import main.dao.SQLDAO.ClientSQLDAO;
import main.dao.SQLDAO.ProduitSQLDAO;
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
