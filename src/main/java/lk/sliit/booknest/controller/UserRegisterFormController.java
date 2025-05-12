package lk.sliit.booknest.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.sliit.booknest.model.UserModel;

import java.io.IOException;

public class UserRegisterFormController {


        @FXML
        private Label lblError;

        @FXML
        private AnchorPane registerPane;

        @FXML
        private MFXTextField txtAddress;

        @FXML
        private MFXPasswordField txtConfirmPassword;

        @FXML
        private MFXTextField txtEmail;

        @FXML
        private MFXTextField txtName;

        @FXML
        private MFXPasswordField txtPassword;
        
        private  final UserModel userModel = new UserModel();
        

        @FXML
        void btnLogin(MouseEvent event) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userLoginForm.fxml"));
                Pane loginPane =(Pane) fxmlLoader.load();
                registerPane.getChildren().clear();
                registerPane.getChildren().add(loginPane);

        }

        @FXML
        void btnRegister(ActionEvent event) {
                boolean isLoginValidated = validateRegister();

                if (!isLoginValidated) {
                     return;
                }

        }

        private boolean validateRegister() {
            boolean isNameValid  = txtName.getText().matches("^[\\p{L} '-]+$");

                if (!isNameValid) {
                        txtName.requestFocus();
                        txtName.getStyleClass().add("mfx-text-field-error");
                        return false;
                }
                txtName.getStyleClass().remove("mfx-text-field-error");

                boolean isAddressValid = txtAddress.getText().matches("^[a-zA-Z0-9,._#()/:;]+$");
                if (!isAddressValid) {
                        txtAddress.requestFocus();
                        txtAddress.getStyleClass().add("mfx-text-field-error");
                        return false;
                }
                txtAddress.getStyleClass().remove("mfx-text-field-error");

                boolean isEmailValid = txtEmail.getText().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
                if (!isEmailValid) {
                        txtEmail.requestFocus();
                        txtEmail.getStyleClass().add("mfx-text-field-error");
                        return false;
                }
                txtEmail.getStyleClass().remove("mfx-text-field-error");

                boolean isPasswordValid = txtPassword.getText().matches("^[a-zA-Z0-9@#]{3,}$");
                if (!isPasswordValid) {
                        txtPassword.requestFocus();
                        txtPassword.getStyleClass().add("mfx-text-field-error");
                        return false;
                }
                txtPassword.getStyleClass().remove("mfx-text-field-error");

                boolean isConfirmPasswordValid = txtConfirmPassword.getText().matches("^[a-zA-Z0-9]{3,}$");
                if (!isConfirmPasswordValid) {
                        txtConfirmPassword.requestFocus();
                        txtConfirmPassword.getStyleClass().add("mfx-text-field-error");
                        return false;
                }
                txtConfirmPassword.getStyleClass().remove("mfx-text-field-error");

                if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
                        lblError.setVisible(true);
                        txtPassword.getStyleClass().add("mfx-text-field-error");
                        txtConfirmPassword.getStyleClass().add("mfx-text-field-error");
                        return false;
                }
                
                lblError.setVisible(false);
                txtPassword.getStyleClass().remove("mfx-text-field-error");
                txtConfirmPassword.getStyleClass().remove(("mfx-text-field-error"));
                
                return true;
        }

}

