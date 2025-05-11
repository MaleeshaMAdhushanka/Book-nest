package lk.sliit.booknest.tm;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.scene.Cursor;

public class AdminBorrowBookTm {

    private String id;

    private String userId;

    private String bookId;

    private String borrowDate;

    private String returnDate;

    private MFXCheckbox isReturned;
    {
        isReturned = new MFXCheckbox();
        isReturned.setCursor(Cursor.HAND);
        isReturned.getStyleClass().add(".mfx-checkbox-update");
    }

    public AdminBorrowBookTm(String id, String userId, String bookId, String borrowDate, String returnDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public MFXCheckbox getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(MFXCheckbox isReturned) {
        this.isReturned = isReturned;
    }
}
