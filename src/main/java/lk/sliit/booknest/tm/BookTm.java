package lk.sliit.booknest.tm;

public class BookTm {

    private String bookID;

    private String bookTitle;

    private String author;

    private String genre;

    private String availability;

    public BookTm() {
    }

    public BookTm(String bookID, String bookTitle, String author, String genre, String availability) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
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

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
