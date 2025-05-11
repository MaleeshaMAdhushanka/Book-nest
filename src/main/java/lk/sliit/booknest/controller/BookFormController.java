package lk.sliit.booknest.controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class BookFormController {

        @FXML
        private TableColumn<?, ?> colAuthor;

        @FXML
        private TableColumn<?, ?> colAvailability;

        @FXML
        private TableColumn<?, ?> colGenre;

        @FXML
        private TableColumn<?, ?> colId;

        @FXML
        private TableColumn<?, ?> colRemove;

        @FXML
        private TableColumn<?, ?> colTitle;

        @FXML
        private TableColumn<?, ?> colUpdate;

        @FXML
        private TableView<?> tblBook;

        @FXML
        private MFXTextField txtSearch;

        @FXML
        void btnAddOnAction(ActionEvent event) {

        }

        @FXML
        void btnSearchOnAction(ActionEvent event) {

        }

}
