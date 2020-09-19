package main.dao.ListMemoire;

import main.pojo.Category;
import java.util.ArrayList;

public interface CategoryDAO {
    boolean delete(Category objet);

    Category getById(int id);

    ArrayList<Category> findAll();

    boolean create(Category objet);
}
