package lk.sliit.booknest.dao.custom.impl;

import lk.sliit.booknest.dao.custom.BookDAO;
import lk.sliit.booknest.entity.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private Session session;


    @Override
    public void setSession(Session session) {
        this.session = session;
    }


    @Override
    public List<Book> getAll() {
        String hql = "FROM Book ";
        Query query = session.createQuery(hql);
        List<Book> bookList = query.list();
        return bookList;
    }

    @Override
    public void save(Book entity) {
        session.save(entity);
    }

    @Override
    public void update(Book entity) {
        session.update(entity);
    }

    @Override
    public void delete(Book entity) {
        session.delete(entity);
    }

    @Override
    public Book search(String id) {
        return session.get(Book.class, id);
    }

}
