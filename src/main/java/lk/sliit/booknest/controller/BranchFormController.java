package lk.sliit.booknest.controller;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;


public class BranchFormController {


        @FXML
        private AnchorPane branchPane;

        @FXML
        private TableColumn<?, ?> colBranchAddress;

        @FXML
        private TableColumn<?, ?> colBranchAdmin;

        @FXML
        private TableColumn<?, ?> colBranchID;

        @FXML
        private TableColumn<?, ?> colBranchName;

        @FXML
        private TableColumn<?, ?> colRemove;

        @FXML
        private TableColumn<?, ?> colUpdate;

        @FXML
        private TableView<?> tblBranch;

        @FXML
        private MFXTextField txtSearch;

        @FXML
        void btnAddOnAction(ActionEvent event) {

        }

        @FXML
        void btnSearchOnAction(ActionEvent event) {

        }



}
