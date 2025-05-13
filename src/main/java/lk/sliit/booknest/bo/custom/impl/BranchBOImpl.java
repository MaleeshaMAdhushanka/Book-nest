package lk.sliit.booknest.bo.custom.impl;


import lk.sliit.booknest.bo.custom.BranchBO;
import lk.sliit.booknest.dao.DAOFactory;
import lk.sliit.booknest.dao.custom.AdminDAO;
import lk.sliit.booknest.dao.custom.BranchDAO;
import lk.sliit.booknest.dto.BranchDto;
import lk.sliit.booknest.entity.Admin;
import lk.sliit.booknest.entity.Branch;
import lk.sliit.booknest.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {

    BranchDAO branchDAO = (BranchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BRANCH);

    AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ADMIN);

    private Session session;

    @Override
    public List<BranchDto> getAllBranch() {
        session = SessionFactoryConfig.getInstance().getSession();
        branchDAO.setSession(session);
        List<BranchDto> branchDtoList = new ArrayList<>();
        try {
            for (Branch branch : branchDAO.getAll()) {
                branchDtoList.add(new BranchDto(branch.getBranchID(),
                        branch.getBranchName(),
                        branch.getBranchAddress(),
                        branch.getAdmin().getUsername()));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return branchDtoList;
    }

    @Override
    public boolean saveBranch(BranchDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            branchDAO.setSession(session);
            adminDAO.setSession(session);
            Admin admin = adminDAO.search(dto.getAdminID());
            branchDAO.save(new Branch(dto.getBranchID(), dto.getBranchName(), dto.getAddress(), admin));
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateBranch(BranchDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        adminDAO.setSession(session);
        Admin admin = adminDAO.search(dto.getAdminID());
        session.clear();

        Transaction transaction = session.beginTransaction();
        branchDAO.setSession(session);
        try {
            branchDAO.update(new Branch(dto.getBranchID(), dto.getBranchName(), dto.getAddress(), admin));
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteBranch(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            branchDAO.setSession(session);
            Branch branch = branchDAO.search(id);
            branchDAO.delete(branch);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public BranchDto searchBranch(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        branchDAO.setSession(session);
        try {
            Branch branch = branchDAO.search(id);
            if (branch != null) {
                return new BranchDto(branch.getBranchID(),
                        branch.getBranchName(),
                        branch.getBranchAddress(),
                        branch.getAdmin().getUsername());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }
}

