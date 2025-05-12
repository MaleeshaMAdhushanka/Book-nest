package lk.sliit.booknest.dao;

import org.hibernate.Session;

import java.sql.Connection;

public interface SuperDAO {
    void setSession(Session session);
}
