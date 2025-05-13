package lk.sliit.booknest.tm;

public class UserBorrowBookTm {

    private String id;

    private String bookId;

    private String borrowDate;

    private String returnDate;

    private String isReturned;

    public UserBorrowBookTm() {
    }

    public UserBorrowBookTm(String id, String bookId, String borrowDate, String returnDate, String isReturned) {
        this.id = id;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(String isReturned) {
        this.isReturned = isReturned;
    }
}
