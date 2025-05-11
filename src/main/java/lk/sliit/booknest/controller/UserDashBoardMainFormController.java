package lk.sliit.booknest.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class UserDashBoardMainFormController {


        @FXML
        private MFXButton btnBookSearch;

        @FXML
        private MFXButton btnBorrowBook;

        @FXML
        private MFXButton btnSetting;

        @FXML
        private Circle cirUserImage;

        @FXML
        private AnchorPane holderPane;

        @FXML
        private Label idUserName;

        @FXML
        private Pane imgAndNameHolderPane;

        @FXML
        void btnBookSearchOnAction(ActionEvent event) {

        }

        @FXML
        void btnBorrowBookOnAction(ActionEvent event) {

        }

        @FXML
        void btnLogoutOnAction(ActionEvent event) {

        }

        @FXML
        void btnSettingsOnAction(ActionEvent event) {

        }
    }

