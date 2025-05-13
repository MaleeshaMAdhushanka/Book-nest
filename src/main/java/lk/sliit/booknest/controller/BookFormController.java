package lk.sliit.booknest.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.sliit.booknest.bo.BoFactory;
import lk.sliit.booknest.bo.custom.BookBO;
import lk.sliit.booknest.tm.BookTm;

import java.io.IOException;


public class BookFormController {

        @FXML
        private TableColumn<BookTm, String> colAuthor;

        @FXML
        private TableColumn<BookTm, String> colAvailability;

        @FXML
        private TableColumn<BookTm, String> colGenre;

        @FXML
        private TableColumn<BookTm, String> colId;

        @FXML
        private TableColumn<BookTm, String> colRemove;

        @FXML
        private TableColumn<BookTm, String> colTitle;

        @FXML
        private TableColumn<BookTm, String> colUpdate;

        @FXML
        private TableView<BookTm> tblBook;

        @FXML
        private MFXTextField txtSearch;
        
        BookBO bookBO = (BookBO) BoFactory.getInstance().getBO(BoFactory.BOTypes.BOOK);
        
        public  void initialize() {
            setCellValueFactory();
            loadAllBooks();
        }

        private void setCellValueFactory() {
                colId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
                colTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
                colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
                colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
                colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

                Callback<TableColumn<BookTm, String>, TableCell<BookTm, String>> colRemoveCellFactory
                        = new Callback<TableColumn<BookTm, String>, TableCell<BookTm, String>>()
                {
                        @Override
                        public TableCell<BookTm,String> call(final TableColumn<BookTm, String> param)
                        {
                                final TableCell<BookTm, String> cell = new TableCell<BookTm, String>()
                                {
                                        final MFXButton btn = new MFXButton("");

                                        {

                                                ImageView delete = new ImageView(new Image("/assets/images/remove.png"));
                                                delete.setFitHeight(30);
                                                delete.setPreserveRatio(true);

                                                btn.setGraphic(delete);
                                                btn.setCursor(javafx.scene.Cursor.HAND);
                                                btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white");


                                                btn.setOnAction(event -> {
                                                        BookTm tm = getTableView().getItems().get(getIndex());

                                                        boolean isDeleted = bookBO.deleteBook(tm.getBookID());

                                                        //Alert
                                                        if (isDeleted){
                                                                new Alert(Alert.AlertType.CONFIRMATION,"User Deleted").show();
                                                        }
                                                        else {
                                                                new Alert(Alert.AlertType.ERROR, "User Doesnt Deleted").show();
                                                        }

                                                        tblBook.getItems().remove(getIndex());
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



                Callback<TableColumn<BookTm, String>, TableCell<BookTm, String>> colUpdateCellFactory
                        = new Callback<>() {
                        @Override
                        public TableCell<BookTm, String> call(final TableColumn<BookTm, String> param) {
                                final TableCell<BookTm, String> cell = new TableCell<>() {
                                        final MFXButton btn = new MFXButton("");

                                        {

                                                ImageView update = new ImageView(new Image("/assets/images/edit.png"));
                                                update.setFitHeight(30);
                                                update.setPreserveRatio(true);

                                                btn.setGraphic(update);
                                                btn.setCursor(javafx.scene.Cursor.HAND);
                                                btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white");




                                                btn.setOnAction(event -> {
                                                        BookTm tm = getTableView().getItems().get(getIndex());
                                                        //Catch the exception
                                                        try {
                                                                updateOnAction(tm.getBookID());
                                                        } catch (IOException e) {
                                                                throw new RuntimeException(e);
                                                        }

                                                });

                                                btn.getStyleClass().add("mfx-button-update");
                                        }

                                        @Override
                                        public void updateItem(String item, boolean empty) {
                                                super.updateItem(item, empty);
                                                if (empty) {
                                                        setGraphic(null);
                                                        setText(null);
                                                } else {
                                                        setGraphic(btn);
                                                        setText(null);
                                                }
                                        }
                                };
                                return cell;
                        }
                };
                colUpdate.setCellFactory(colUpdateCellFactory);
        }

        private void updateOnAction(String bookId) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/bookDataForm.fxml"));
                Parent rootNode = fxmlLoader.load();

                BookDataFormController bookDataFormController = fxmlLoader.getController();
                bookDataFormController.setBookFormController(this);

                //Set Button Name
                bookDataFormController.setBtnAndLblName("Update");
                bookDataFormController.loadBookData(bookId);

                Scene scene = new Scene(rootNode);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Update Book");
                stage.show();
        }

        @FXML
        void btnAddOnAction(ActionEvent event) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/bookDataForm.fxml"));
                Parent rootNode = fxmlLoader.load();

                BookDataFormController bookDataFormController = fxmlLoader.getController();
                bookDataFormController.setBookFormController(this);

                bookDataFormController.setBtnAndLblName("Add");


                Scene scene = new Scene(rootNode);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Add Book");
                stage.show();

        }

        @FXML
        void btnSearchOnAction(ActionEvent event) {
                String keyword = txtSearch.getText().trim().toLowerCase();
                if (keyword.isEmpty()) {
                        loadAllBooks();
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
        public void loadAllBooks(){
                ObservableList<BookTm> books = FXCollections.observableArrayList();
                bookBO.getAllBooks().forEach(bookDto -> {
                        books.add(new BookTm(
                                bookDto.getBookId(), 
                                bookDto.getTitle(),
                                bookDto.getAuthor(),
                                bookDto.getGenre(),
                                bookDto.isAvailability()? "Yes" : "NO"
                                
                        ));
                });
                
                tblBook.setItems(books);
                
        }
        

}
