package main.dao.fabrique;

import main.dao.metiersDAO.CategoryDAO;
import main.dao.metiersDAO.ClientDAO;
import main.dao.metiersDAO.CommandeDAO;
import main.dao.metiersDAO.ProduitDAO;

public abstract class DAOFactory {
    public static DAOFactory getDAOFactory(EPersistence cible) {
        DAOFactory daoF = null;
        switch (cible) {
            case MYSQL:
                daoF = new MySQLDAOFactory();
                break;
            case LISTEMEMORY:
                daoF = new ListeMemoireDAOFactory();
                break;
        }
        return daoF;
    }

    public abstract CategoryDAO getCategoryDAO();
    public abstract ClientDAO getClientDAO();
    public abstract ProduitDAO getProduitDAO();
    public abstract CommandeDAO getCommandeDAO();
}
