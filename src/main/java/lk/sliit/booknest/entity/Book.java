package lk.sliit.booknest.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_id",length = 30)
    private String bookID;

    @Column(name = "title")
    private  String title;

    @Column(name = "author")
    private  String author;

    @Column(name = "genre")
    private  String genre;

    @Column(name = "available")
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id", nullable = false )
    private Branch branch;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
    private List<BookTransactions> bookTransactions = new ArrayList<>();

    public Book() {
    }

    public Book(String bookID, String title, String author, String genre, boolean available, Branch branch) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
        this.branch = branch;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID='" + bookID + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", available=" + available +
                ", branch=" + branch +
                '}';
    }
}
