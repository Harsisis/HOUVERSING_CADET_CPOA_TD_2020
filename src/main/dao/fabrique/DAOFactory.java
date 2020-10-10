package main.dao.fabrique;

import main.dao.metiersDAO.CategorieDAO;
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

    public abstract CategorieDAO getCategorieDAO();
    public abstract ClientDAO getClientDAO();
    public abstract ProduitDAO getProduitDAO();
    public abstract CommandeDAO getCommandeDAO();
}
