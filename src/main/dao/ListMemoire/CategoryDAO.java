package main.dao.ListMemoire;

import main.dao.IDAO.IDAO;
import main.pojo.Category;
import java.util.ArrayList;

public interface CategoryDAO extends IDAO<Category> {
    boolean delete(Category objet);

    Category getById(int id);

    ArrayList<Category> findAll();

    boolean create(Category objet);

    boolean update(Category objet);
}
