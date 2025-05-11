package lk.sliit.booknest.controller;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class UserBorrowBookFormController {


        @FXML
        private MFXFilterComboBox<?> cmbBookID;

        @FXML
        private TableColumn<?, ?> colBookID;

        @FXML
        private TableColumn<?, ?> colBorrowDate;

        @FXML
        private TableColumn<?, ?> colID;

        @FXML
        private TableColumn<?, ?> colRetuened;

        @FXML
        private TableColumn<?, ?> colReturnDate;

        @FXML
        private MFXDatePicker dpReturnDate;

        @FXML
        private TableView<?> tblBorrowBook;

        @FXML
        private MFXTextField txtSearch;

        @FXML
        void btnBorrowOnAction(ActionEvent event) {

        }

        @FXML
        void txtSearchOnAction(ActionEvent event) {

        }

}

