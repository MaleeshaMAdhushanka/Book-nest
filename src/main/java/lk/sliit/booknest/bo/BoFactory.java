package lk.sliit.booknest.bo;

import lk.sliit.booknest.bo.custom.impl.*;

public class BoFactory {

    //Using Singleton Design Pattern

    private static BoFactory boFactory;

    //Constructor eka private karanwa
    private BoFactory(){}

    public  static BoFactory getInstance(){
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes{
        USER, ADMIN, BRANCH, BOOK, BOOK_TRANSACTION
    }
    //acces karanna objcet ekaka hadagann on nithrama new key word eka dala object hadanne nh
    public SuperBo getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case ADMIN:
                return  new AdminBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            case BOOK:
                return new BookBOImpl();
            case BOOK_TRANSACTION:
                return new BookTransactionBOImpl();
            default:
                return null;
        }
    }


}
