package lk.sliit.booknest.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BranchDataFormController {

        @FXML
        private MFXButton btnAction;

        @FXML
        private MFXComboBox<?> cmbAdmin;

        @FXML
        private Label lblAction;

        @FXML
        private MFXTextField txtBranchAddress;

        @FXML
        private MFXTextField txtBranchID;

        @FXML
        private MFXTextField txtBranchName;

        @FXML
        void btnActionOnAction(ActionEvent event) {

        }

        @FXML
        void btnCancel(ActionEvent event) {

        }

}
