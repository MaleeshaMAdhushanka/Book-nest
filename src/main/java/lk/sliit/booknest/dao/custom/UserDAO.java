package lk.sliit.booknest.dao.custom;

import lk.sliit.booknest.dao.CrudDAO;
import lk.sliit.booknest.entity.User;

public interface UserDAO extends CrudDAO<User> {
    boolean updateUsername(String newUsername, String oldUsername);

}
