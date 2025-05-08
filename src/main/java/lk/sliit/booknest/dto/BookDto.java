package lk.sliit.booknest.dto;

public class BookDto {
    private String bookId;

    private String title;

    private String author;

    private String genre;

    private boolean availability;

    private String branchID;

    public BookDto() {
    }

    public BookDto(String bookId, String title, String author, String genre, String branchID) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.branchID = branchID;
    }

    public BookDto(String bookId, String title, String author, String genre, boolean availability, String branchID) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.branchID = branchID;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }
}
