package lk.sliit.booknest.bo.custom;

import lk.sliit.booknest.bo.SuperBo;
import lk.sliit.booknest.dto.BookDto;

import java.util.List;

public interface BookBO extends SuperBo {
    List<BookDto> getAllBooks();
    boolean saveBook(BookDto dto);
    boolean updateBook(BookDto dto);
    boolean deleteBook(String id);
    BookDto searchBook(String id);
}
