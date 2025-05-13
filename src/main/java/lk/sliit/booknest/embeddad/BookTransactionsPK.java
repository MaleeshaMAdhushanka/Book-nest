package lk.sliit.booknest.embeddad;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class BookTransactionsPK implements Serializable {
    @Column(name = "book_id",length = 30)
    private String bookId;
    @Column(name = "user_id",length = 30)
    private String userId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

