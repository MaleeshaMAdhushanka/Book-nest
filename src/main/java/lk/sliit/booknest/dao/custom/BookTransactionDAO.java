package lk.sliit.booknest.dao.custom;

import lk.sliit.booknest.dao.CrudDAO;
import lk.sliit.booknest.entity.BookTransactions;

import java.util.List;

public interface BookTransactionDAO extends CrudDAO<BookTransactions> {

    List<BookTransactions>  getAllLateBookDetails();
}
