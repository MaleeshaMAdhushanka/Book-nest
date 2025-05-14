package lk.sliit.booknest.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import lk.sliit.booknest.bo.BoFactory;
import lk.sliit.booknest.bo.custom.BookBO;
import lk.sliit.booknest.bo.custom.BookTransactionBO;
import lk.sliit.booknest.bo.custom.UserBO;
import lk.sliit.booknest.dto.BorrowBookDto;
import lk.sliit.booknest.tm.AdminBorrowBookTm;

import java.time.LocalDate;
import java.util.List;


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
                List<BorrowBookDto> allBorrowedBooks = bookTransactionBO.getAllBorrowedBooks();


                ObservableList<AdminBorrowBookTm> observableList = FXCollections.observableArrayList();

                for (BorrowBookDto dto : allBorrowedBooks) {
                        observableList.add(new AdminBorrowBookTm(
                                dto.getId(),
                                dto.getUserId(),
                                dto.getBookId(),
                                dto.getBorrowDate(),
                                dto.getReturnDate()
                        ));
                }

                for (int i = 0; i < observableList.size(); i++) {
                        final  int index = i;
                        if (allBorrowedBooks.get(i).isReturned()){
                                observableList.get(i).getIsReturned().setSelected(true);
                        }
                        observableList.get(i).getIsReturned().setOnAction(actionEvent -> {
                                boolean isUpdated = bookTransactionBO.updateBorrowedBook(observableList.get(index).getId());
                                if (isUpdated){
                                        new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
                                        loadBookIds();
                                }
                                else {
                                        new Alert(Alert.AlertType.ERROR,"Not Updated").show();
                                }
                        });
                }

                tblBorrowBook.setItems(observableList);
        }

        private void setCellValueFactory() {
                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
                colBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
                colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
                colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
                colIsReturned.setCellValueFactory(new PropertyValueFactory<>("isReturned"));


                Callback<TableColumn<AdminBorrowBookTm, String>, TableCell<AdminBorrowBookTm, String>> colRemoveCellFactory
                        = new Callback<TableColumn<AdminBorrowBookTm, String>, TableCell<AdminBorrowBookTm, String>>()
                {
                        @Override
                        public TableCell<AdminBorrowBookTm,String> call(final TableColumn<AdminBorrowBookTm, String> param)
                        {
                                final TableCell<AdminBorrowBookTm, String> cell = new TableCell<AdminBorrowBookTm, String>()
                                {
                                        final MFXButton btn = new MFXButton("");

                                        {
                                                ImageView delete = new ImageView(new Image("/images/remove.png"));
                                                delete.setFitHeight(30);
                                                delete.setPreserveRatio(true);

                                                btn.setGraphic(delete);
                                                btn.setCursor(javafx.scene.Cursor.HAND);
                                                btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white");

                                                btn.setOnAction(event -> {
                                                        AdminBorrowBookTm tm = getTableView().getItems().get(getIndex());
                                                        boolean isDeleted = bookTransactionBO.deleteBorrowedBook(tm.getId());
                                                        //Alert
                                                        if (isDeleted){
                                                                new Alert(Alert.AlertType.CONFIRMATION,"User Deleted").show();
                                                                loadBookIds();
                                                        }
                                                        else {
                                                                new Alert(Alert.AlertType.ERROR, "User Doesnt Deleted").show();
                                                        }

                                                        tblBorrowBook.getItems().remove(getIndex());
                                                });
                                                btn.getStyleClass().add("mfx-button-remove");
                                        }

                                        @Override
                                        public void updateItem(String item, boolean empty)
                                        {
                                                super.updateItem(item, empty);
                                                if (empty) {
                                                        setGraphic(null);
                                                        setText(null);
                                                }
                                                else {
                                                        setGraphic(btn);
                                                        setText(null);
                                                }
                                        }
                                };
                                return cell;
                        }
                };

                colRemove.setCellFactory(colRemoveCellFactory);
        }
                


        private void loadBookIds() {
                //Clear Items
                cmbBookID.getItems().clear();

                bookBO.getAllBooks().forEach(dto -> {
                        boolean availability = dto.isAvailability();
                        if (availability) {
                                cmbBookID.getItems().add(dto.getBookId());
                        }
                });
                
        }

        private void loadUserIds() {
                userBO.getAllUsers().forEach(dto -> {
                        cmbUserID.getItems().add(dto.getEmail());
                });
                
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
                boolean isSaved =bookTransactionBO.saveBorrowedBook(dto);
                if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Book Borrowed").show();
                        loadBorrowedBooks();
                        loadBookIds();
                        clearFields();
                } else {
                        new Alert(Alert.AlertType.ERROR, "Book Not Borrowed").show();
                }

        }
        private void clearFields() {
                cmbUserID.clear();
                cmbBookID.clear();
                dpReturnDate.clear();
        }
        private boolean validateFields() {

                boolean isUserIdEmpty = cmbUserID.getText().isEmpty();

                if (isUserIdEmpty) {
                        cmbUserID.requestFocus();
                        cmbUserID.getStyleClass().add("mfx-combo-box-error");
                        return false;
                }

                cmbUserID.getStyleClass().remove("mfx-combo-box-error");


                boolean isBookIdEmpty = cmbBookID.getText().isEmpty();
                if (isBookIdEmpty) {
                        cmbBookID.requestFocus();
                        cmbBookID.getStyleClass().add("mfx-combo-box-error");
                        return false;
                }
                cmbBookID.getStyleClass().remove("mfx-combo-box-error");


                boolean isReturnDateEmpty = dpReturnDate.getText().isEmpty();

                if (isReturnDateEmpty){
                        dpReturnDate.requestFocus();
                        dpReturnDate.getStyleClass().add("mfx-dp-picker-error");
                        return false;
                }

                dpReturnDate.getStyleClass().remove("mfx-dp-picker-error");


                return true;

        }





}
