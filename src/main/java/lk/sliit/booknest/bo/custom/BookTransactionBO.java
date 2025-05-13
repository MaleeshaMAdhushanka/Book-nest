package lk.sliit.booknest.bo.custom;

import lk.sliit.booknest.bo.SuperBo;
import lk.sliit.booknest.dto.BorrowBookDto;

import java.util.List;

public interface BookTransactionBO extends SuperBo {
    List<BorrowBookDto> getAllBorrowedBooks();
    boolean saveBorrowedBook(BorrowBookDto dto);
    boolean updateBorrowedBook(String id);
    boolean deleteBorrowedBook(String id);
    BorrowBookDto searchBorrowedBook(String id);
    List<BorrowBookDto> getAllLateBookDetails();
}

