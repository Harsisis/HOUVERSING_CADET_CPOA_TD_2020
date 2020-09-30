package test.classeSQL;

import main.dao.classeSQL.CategorySQL;
import main.pojo.Category;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class TestCategorySQL {

    private int id = 2;
    Category category;

    @Before
    public void setUp() {
        category = CategorySQL.getInstance().getById(id);
    }
    @Test
    public void TestCategorySQLGetById() {
        System.out.println(category);
        assertNotNull(category);
    }
}
