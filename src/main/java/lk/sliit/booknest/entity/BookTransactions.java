package lk.sliit.booknest.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book_transaction")
public class BookTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @CreationTimestamp
    private LocalDate borrowDate;

    private LocalDate returnDate;


     boolean isReturned;

     @ManyToOne
     @JoinColumn(name ="book_id", nullable = false)
     private Book book;

     @ManyToOne
     @JoinColumn(name = "email", nullable = false)
     private User user;

    public BookTransactions() {

    }

    public BookTransactions( Book book,User user ,LocalDate returnDate) {
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
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
