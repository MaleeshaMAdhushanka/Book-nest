package lk.sliit.booknest.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends  SuperDAO{

    List<T> getAll () throws SQLException;
    boolean save(T entity) throws SQLException;

    boolean update(T entity) throws SQLException;
    boolean delete(T entity) throws SQLException;
    T search(String id) throws SQLException;
}
