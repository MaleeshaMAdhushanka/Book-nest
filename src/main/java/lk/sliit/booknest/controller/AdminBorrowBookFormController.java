package lk.sliit.booknest.controller;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;


public class AdminBorrowBookFormController {


        @FXML
        private AnchorPane borrowBookPane;

        @FXML
        private MFXFilterComboBox<?> cmbBookID;

        @FXML
        private MFXFilterComboBox<?> cmbUserID;

        @FXML
        private TableColumn<?, ?> colBookID;

        @FXML
        private TableColumn<?, ?> colBorrowDate;

        @FXML
        private TableColumn<?, ?> colID;

        @FXML
        private TableColumn<?, ?> colIsReturned;

        @FXML
        private TableColumn<?, ?> colRemove;

        @FXML
        private TableColumn<?, ?> colReturnDate;

        @FXML
        private TableColumn<?, ?> colUserID;

        @FXML
        private MFXDatePicker dpReturnDate;

        @FXML
        private TableView<?> tblBorrowBook;

        @FXML
        void btnAddOnAction(ActionEvent event) {

        }


}
