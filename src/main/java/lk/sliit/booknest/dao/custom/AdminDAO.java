package lk.sliit.booknest.dao.custom;

import lk.sliit.booknest.dao.CrudDAO;
import lk.sliit.booknest.entity.Admin;

import java.sql.SQLException;

public interface AdminDAO extends CrudDAO<Admin> {
    int updateAdminUsername(String userName, String oldUsername) throws SQLException;

}
