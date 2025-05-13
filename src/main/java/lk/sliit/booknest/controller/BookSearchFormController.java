package lk.sliit.booknest.controller;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.sliit.booknest.bo.BoFactory;
import lk.sliit.booknest.bo.custom.BookBO;
import lk.sliit.booknest.tm.BookTm;


public class BookSearchFormController {


        @FXML
        private TableColumn<BookTm, String> colAuthor;

        @FXML
        private TableColumn<BookTm, String> colAvailability;

        @FXML
        private TableColumn<BookTm, String> colGenre;

        @FXML
        private TableColumn<BookTm, String> colId;

        @FXML
        private TableColumn<BookTm, String> colTitle;

        @FXML
        private TableView<BookTm> tblBook;

        @FXML
        private MFXTextField txtSearch;

        BookBO bookBO = (BookBO) BoFactory.getInstance().getBO(BoFactory.BOTypes.BOOK);
        
        public void initialize(){
                setCellValueFactory();
                loadBookDetails();
                
        }

        private void setCellValueFactory() {
                colId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
                colTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
                colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
                colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
                colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        }

        @FXML
        void btnSearchOnAction(ActionEvent event) {
                String keyword = txtSearch.getText().trim().toLowerCase();
                if (keyword.isEmpty()) {
                        loadBookDetails();
                } else {
                        FilteredList<BookTm> filteredData = new FilteredList<>(tblBook.getItems(), bookTm ->
                                bookTm.getBookID().toLowerCase().contains(keyword) ||
                                        bookTm.getBookTitle().toLowerCase().contains(keyword) ||
                                        bookTm.getAuthor().toLowerCase().contains(keyword) ||
                                        bookTm.getGenre().toLowerCase().contains(keyword) ||
                                        bookTm.getAvailability().toLowerCase().contains(keyword)
                        );
                        tblBook.setItems(filteredData);
                }

        }

        private void loadBookDetails() {
                ObservableList<BookTm> bookTms = FXCollections.observableArrayList();
                bookBO.getAllBooks().forEach(bookDto -> {
                        bookTms.add(new BookTm(
                                bookDto.getBookId(),
                                bookDto.getTitle(),
                                bookDto.getAuthor(),
                                bookDto.getGenre(),
                                bookDto.isAvailability()? "Yes" : "No"
                        ));
                });
                tblBook.setItems(bookTms);
        }

}
