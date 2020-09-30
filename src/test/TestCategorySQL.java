package test;

import main.dao.classeSQL.CategorySQL;
import main.pojo.Category;
import org.junit.Before;
import org.junit.Test;

public class TestCategorySQL {

    private int id = 2;
    Category category;

    @Before
    public void setUp() {
        category = CategorySQL.getInstance().getById(id);
    }
    @Test
    public void TestCategorySQL() {
        System.out.println(category);
    }
}
