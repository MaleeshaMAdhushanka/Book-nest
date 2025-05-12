package lk.sliit.booknest.dao.custom.impl;

import lk.sliit.booknest.dao.custom.UserDAO;
import lk.sliit.booknest.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Connection connection;

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;

    }
    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();

        String sql = "SELECRT usernm, "

    }

    @Override
    public boolean save(User entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(User entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(User entity) throws SQLException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException {
        return null;
    }



    @Override
    public boolean updateUsername(String newUsername, String oldUsername) {
        return false;
    }
}
