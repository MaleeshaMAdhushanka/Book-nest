package lk.sliit.booknest.dao.custom.impl;

import lk.sliit.booknest.dao.custom.BranchDAO;
import lk.sliit.booknest.entity.Branch;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BranchDAOImpl implements BranchDAO {

    private Session session;
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Branch> getAll() {
        String hql = "FROM Branch ";
        Query query = session.createQuery(hql);
        List<Branch> branchList = query.list();
        session.close();
        return branchList;
    }

    @Override
    public void save(Branch entity) {
        session.save(entity);
    }

    @Override
    public void update(Branch entity) {
        session.update(entity);
    }

    @Override
    public void delete(Branch entity) {
        session.delete(entity);
    }

    @Override
    public Branch search(String id) {
        return session.get(Branch.class, id);
    }
}
