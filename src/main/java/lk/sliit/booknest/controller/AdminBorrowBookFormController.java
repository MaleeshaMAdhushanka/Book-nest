package lk.sliit.booknest.controller;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.sliit.booknest.bo.BoFactory;
import lk.sliit.booknest.bo.custom.BookBO;
import lk.sliit.booknest.bo.custom.BookTransactionBO;
import lk.sliit.booknest.bo.custom.UserBO;
import lk.sliit.booknest.dto.BorrowBookDto;
import lk.sliit.booknest.tm.AdminBorrowBookTm;

import java.time.LocalDate;


public class AdminBorrowBookFormController {


        @FXML
        private AnchorPane borrowBookPane;

        @FXML
        private MFXFilterComboBox<String> cmbBookID;

        @FXML
        private MFXFilterComboBox<String> cmbUserID;

        @FXML
        private TableColumn<AdminBorrowBookTm, String> colBookID;

        @FXML
        private TableColumn<AdminBorrowBookTm, String> colBorrowDate;

        @FXML
        private TableColumn<AdminBorrowBookTm, String> colID;

        @FXML
        private TableColumn<AdminBorrowBookTm, String> colIsReturned;

        @FXML
        private TableColumn<AdminBorrowBookTm, String> colRemove;

        @FXML
        private TableColumn<AdminBorrowBookTm, String> colReturnDate;

        @FXML
        private TableColumn<AdminBorrowBookTm, String> colUserID;

        @FXML
        private MFXDatePicker dpReturnDate;

        @FXML
        private TableView<AdminBorrowBookTm> tblBorrowBook;

        UserBO userBO = (UserBO) BoFactory.getInstance().getBO(BoFactory.BOTypes.USER);
        BookBO bookBO = (BookBO) BoFactory.getInstance().getBO(BoFactory.BOTypes.BOOK);

        BookTransactionBO bookTransactionBO = (BookTransactionBO) BoFactory.getInstance().getBO(BoFactory.BOTypes.BOOK_TRANSACTION);

        public void initialize() {
                loadUserIds();
                loadBookIds();
                setCellValueFactory();
                loadBorrowedBooks();
        }

        private void loadBorrowedBooks() {
        }

        private void setCellValueFactory() {
                
        }

        private void loadBookIds() {
                
        }

        private void loadUserIds() {
                
        }


        @FXML
        void btnAddOnAction(ActionEvent event) {
                boolean isValidated = validateFields();
                if (!isValidated) {
                        return;
                }

                String userId = cmbUserID.getText();
                String bookId = cmbBookID.getText();
                LocalDate returnDate = dpReturnDate.getValue();

                BorrowBookDto dto = new BorrowBookDto(userId, bookId, returnDate.toString());
                boolean isSaved =.saveBorrowedBook(dto);
                if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Book Borrowed").show();
                        loadBorrowedBooks();
                        loadBookIds();
                        clearFields();
                } else {
                        new Alert(Alert.AlertType.ERROR, "Book Not Borrowed").show();
                }

        }


}
