package main.dao.IDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAO<T> {
    public abstract boolean create(T object);

    public abstract boolean update(T object);

    public abstract boolean delete(T object);

    public abstract T getById(int id);

    public abstract ArrayList<T> findAll() throws SQLException;
}
