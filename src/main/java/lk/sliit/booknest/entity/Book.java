package lk.sliit.booknest.entity;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String bookID;

    private  String title;

    private  String author;

    private  String genre;

    private boolean available;

    private Branch branch;

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

    public List<BookTransactions> getBookTransactions() {
        return bookTransactions;
    }

    public void setBookTransactions(List<BookTransactions> bookTransactions) {
        this.bookTransactions = bookTransactions;
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
                ", bookTransactions=" + bookTransactions +
                '}';
    }
}
