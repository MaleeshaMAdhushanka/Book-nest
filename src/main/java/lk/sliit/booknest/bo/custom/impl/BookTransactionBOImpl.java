package lk.sliit.booknest.bo.custom.impl;


import lk.sliit.booknest.bo.custom.BookTransactionBO;
import lk.sliit.booknest.dao.DAOFactory;
import lk.sliit.booknest.dao.custom.BookDAO;
import lk.sliit.booknest.dao.custom.BookTransactionDAO;
import lk.sliit.booknest.dao.custom.UserDAO;
import lk.sliit.booknest.dto.BorrowBookDto;
import lk.sliit.booknest.entity.Book;
import lk.sliit.booknest.entity.BookTransactions;
import lk.sliit.booknest.entity.User;
import lk.sliit.booknest.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookTransactionBOImpl implements BookTransactionBO {

    BookTransactionDAO bookTransactionDAO = (BookTransactionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOK_TRANSACTION);

    BookDAO bookDAO = (BookDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOK);

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    private Session session;

    @Override
    public List<BorrowBookDto> getAllBorrowedBooks() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookTransactionDAO.setSession(session);
        List<BorrowBookDto> borrowBookDtoList = new ArrayList<>();
        try {
            for (BookTransactions bookTransactions : bookTransactionDAO.getAll()) {
                borrowBookDtoList.add(new BorrowBookDto(
                        String.valueOf(bookTransactions.getTransactionId()),
                        bookTransactions.getUser().getEmail(),
                        bookTransactions.getBook().getBookID(),
                        String.valueOf(bookTransactions.getBorrowDate()),
                        String.valueOf(bookTransactions.getReturnDate()),
                        bookTransactions.isReturned()
                ));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return borrowBookDtoList;
    }

    @Override
    public boolean saveBorrowedBook(BorrowBookDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            bookTransactionDAO.setSession(session);
            bookDAO.setSession(session);
            userDAO.setSession(session);

            //Search Book And User
            User user = userDAO.search(dto.getUserId());
            Book book = bookDAO.search(dto.getBookId());
            book.setAvailable(false);

            bookTransactionDAO.save(new BookTransactions(book, user, LocalDate.parse(dto.getReturnDate())));
            bookDAO.update(book);


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
    public boolean updateBorrowedBook(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            bookTransactionDAO.setSession(session);
            bookDAO.setSession(session);

            BookTransactions bookTransaction = bookTransactionDAO.search(id);

            //Get Book And set available
            Book book = bookTransaction.getBook();
            boolean available = !(book.isAvailable());
            book.setAvailable(available);

            //Set Book Entity and update the book transaction record
            bookTransaction.setBook(book);
            bookTransaction.setReturned(!bookTransaction.isReturned());

            bookTransactionDAO.update(bookTransaction);
            bookDAO.update(book);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteBorrowedBook(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            bookTransactionDAO.setSession(session);
            bookDAO.setSession(session);

            //Get The transaction record
            BookTransactions bookTransaction = bookTransactionDAO.search(id);

            //Get Book and set available
            Book book = bookTransaction.getBook();
            book.setAvailable(true);


            bookDAO.update(book);
            bookTransactionDAO.delete(bookTransaction);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public BorrowBookDto searchBorrowedBook(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        bookTransactionDAO.setSession(session);
        try {
            BookTransactions transactions = bookTransactionDAO.search(id);
            if (transactions != null) {
                return new BorrowBookDto(
                        String.valueOf(transactions.getTransactionId()),
                        transactions.getUser().getEmail(),
                        transactions.getBook().getBookID(),
                        String.valueOf(transactions.getBorrowDate()),
                        String.valueOf(transactions.getReturnDate()),
                        transactions.isReturned()
                );
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<BorrowBookDto> getAllLateBookDetails() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookTransactionDAO.setSession(session);
        List<BorrowBookDto> borrowBookDtoList = new ArrayList<>();
        try {
            for (BookTransactions bookTransactions : bookTransactionDAO.getAllLateBookDetails()) {
                borrowBookDtoList.add(new BorrowBookDto(
                        String.valueOf(bookTransactions.getTransactionId()),
                        bookTransactions.getUser().getEmail(),
                        bookTransactions.getBook().getBookID(),
                        String.valueOf(bookTransactions.getBorrowDate()),
                        String.valueOf(bookTransactions.getReturnDate()),
                        bookTransactions.isReturned()
                ));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return borrowBookDtoList;
    }
}

