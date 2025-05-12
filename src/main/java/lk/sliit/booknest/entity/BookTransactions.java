package lk.sliit.booknest.entity;

import java.time.LocalDate;

public class BookTransactions {
    private int transactionId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;

    private Book book;

    private User user;

    public BookTransactions() {

    }

    public BookTransactions(Book book, User user, LocalDate returnDate) {
        this.book = book;
        this.user = user;
        this.returnDate = returnDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
