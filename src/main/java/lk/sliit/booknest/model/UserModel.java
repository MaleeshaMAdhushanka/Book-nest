package lk.sliit.booknest.model;

import javafx.scene.shape.Circle;
import lk.sliit.booknest.db.DbConnection;
import lk.sliit.booknest.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {

    //Get All Users

    public List<UserDto> getAll (){
        List<UserDto> users = new ArrayList<>();
        try{
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "SELECT * FROM users";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                users.add(new UserDto(
                        resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("password"),
                        resultSet.getString("imgUrl")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
         }
        return users;

    }

    //Save a new User

    public boolean save (UserDto userDto){
        try{
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "INSERT INTO user(email, name, address, password, imgUrl) VALUES(?, ?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, userDto.getEmail());
            pstm.setString(2, userDto.getName());
            pstm.setString(3, userDto.getAddress());
            pstm.setString(4, userDto.getPassword());
            pstm.setString(5, userDto.getImgUrl());

            return pstm.executeUpdate() > 0;


        }catch (SQLException e){
            e.printStackTrace();
            return false;

        }
    }

    //updating existing user
    public boolean update (UserDto userDto){
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "UPDATE user SET name = ?, address = ?, password = ?, imgUrl = ? WHERE email = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, userDto.getName());
            pstm.setString(2, userDto.getAddress());
            pstm.setString(3, userDto.getPassword());
            pstm.setString(4, userDto.getImgUrl());
            pstm.setString(5, userDto.getEmail());

            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
         }
    }

    //Delete User
    public boolean delete (String email){

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "DELETE FROM user WHERE email = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);

            return pstm.executeUpdate() > 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Search for user by email

    public UserDto search(String email){

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "SELECT * FROM user WHERE email = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);

             ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return  new UserDto(
                        resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("password"),
                        resultSet.getString("imgUrl")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    // Get users with overdue books
    public List<UserDto>getUsersWithOverdueBooks(){
        List<UserDto> users = new ArrayList<>();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "SELECT DISTINCT u.* FROM user u " +
                    "JOIN book_transactions bt ON u.email = bt.user_email " +
                    "WHERE bt.return_date < CURDATE() AND bt.is_returned = false";

            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                users.add(new UserDto(
                        resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("password"),
                        resultSet.getString("imgUrl")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;


    }

    //update user email
    public boolean updateUserEmail(String email, String oldEmail){

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "UPDATE user SET email = ? WHERE email = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setString(2, oldEmail);
            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Verify user credentials for login
    public boolean isUserExist(UserDto userDto){
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, userDto.getEmail());
            pstm.setString(2, userDto.getPassword());

            ResultSet resultSet = pstm.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

}
