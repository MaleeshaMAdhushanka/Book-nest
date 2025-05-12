package lk.sliit.booknest.util;


import lk.sliit.booknest.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {

    private static SessionFactoryConfig factoryConfiguration;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        sessionFactory = new Configuration()
                .mergeProperties(Utility.getProperties())
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(BookTransactions.class)
                .addAnnotatedClass(Branch.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() {
        return (null == factoryConfiguration)
                ? factoryConfiguration = new SessionFactoryConfig()
                : factoryConfiguration;
    }
    public Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}
