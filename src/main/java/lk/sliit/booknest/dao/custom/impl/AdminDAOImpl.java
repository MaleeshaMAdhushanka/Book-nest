package lk.sliit.booknest.dao.custom.impl;

import lk.sliit.booknest.dao.custom.AdminDAO;
import lk.sliit.booknest.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    private Connection connection;

    @Override
    public void setConnection(Connection connection) {
       this.connection = connection;
    }



    @Override
    public List<Admin> getAll() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT username, password, imgUrl FROM admin";
        try(PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String imgUrl = resultSet.getString("imgUrl");

                Admin admin = new Admin(username, password, imgUrl);
                admins.add(admin);
            }

        }
        return admins;
    }

    @Override
    public boolean save(Admin entity) throws SQLException {
        String sql = "INSERT INTO admin(username, password, imgUrl) VALUES(?,?,?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getImgUrl());

            return statement.executeUpdate() > 0;

        }

    }

    @Override
    public boolean update(Admin entity) throws SQLException {
        String sql = "UPDATE admin SET password = ?, imgUrl = ? WHERE username = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getPassword());
            statement.setString(2, entity.getImgUrl());
            statement.setString(3, entity.getUsername());

            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Admin entity) throws SQLException {
        String sql = "DELETE FROM admin WHERE username = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getUsername());
            return statement.executeUpdate() > 0;
        }

    }

    @Override
    public Admin search(String id) throws SQLException {
        String sql = "SELECT username, password, imgUrl FROM admin WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String imgUrl = resultSet.getString("imgUrl");

                    return new Admin(username, password, imgUrl);
                }
            }
        }
        return null;

    }
    @Override
    public int updateAdminUsername(String userName, String oldUsername) throws SQLException {
        String sql = "UPDATE admin SET username = ? WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setString(2, oldUsername);

            return statement.executeUpdate();
        }

    }


}
