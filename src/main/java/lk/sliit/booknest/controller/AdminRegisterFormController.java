package lk.sliit.booknest.controller;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AdminRegisterFormController {


        @FXML
        private Label lblError;

        @FXML
        private AnchorPane registerPane;

        @FXML
        private MFXPasswordField txtConfirmPassword;

        @FXML
        private MFXPasswordField txtPassword;

        @FXML
        private MFXTextField txtUsername;

        @FXML
        void btnLogin(MouseEvent event) {

        }

        @FXML
        void btnRegister(ActionEvent event) {

        }

}
