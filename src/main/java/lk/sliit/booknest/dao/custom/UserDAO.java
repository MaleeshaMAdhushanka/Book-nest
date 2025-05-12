package lk.sliit.booknest.dao.custom;

import lk.sliit.booknest.dao.CrudDAO;
import lk.sliit.booknest.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {

    List<User> getUsersWithOverdueBooks();

    int updateUserEmail(String email, String oldEmail);

}
