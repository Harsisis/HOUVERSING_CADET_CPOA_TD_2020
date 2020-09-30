package main.dao.fabrique;

import main.dao.classeSQL.CategorySQL;
import main.dao.classeSQL.ClientSQL;
import main.dao.classeSQL.ProduitSQL;
import main.dao.metiersDAO.CategoryDAO;
import main.dao.metiersDAO.ClientDAO;
import main.dao.metiersDAO.ProduitDAO;

public class MySQLDAOFactory extends DAOFactory{

    @Override
    public CategoryDAO getCategoryDAO() {
        return (CategoryDAO) CategorySQL.getInstance();
    }
    @Override
    public ClientDAO getClientDAO() {
        return (ClientDAO) ClientSQL.getInstance();
    }
    @Override
    public ProduitDAO getProduitDAO() {
        return ProduitSQL.getInstance();
    }
}
