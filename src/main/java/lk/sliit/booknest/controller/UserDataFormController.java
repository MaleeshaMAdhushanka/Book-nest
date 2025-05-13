package lk.sliit.booknest.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.sliit.booknest.bo.BoFactory;
import lk.sliit.booknest.bo.custom.UserBO;
import lk.sliit.booknest.dto.UserDto;

public class UserDataFormController {


        @FXML
        private MFXButton btnAction;

        @FXML
        private Label lblAction;

        @FXML
        private MFXTextField txtAddress;

        @FXML
        private MFXTextField txtEmail;

        @FXML
        private MFXTextField txtName;

        @FXML
        private MFXPasswordField txtPassword;

        private UserFormController userFormController;

        private final UserBO userBO = (UserBO) BoFactory.getInstance().getBO(BoFactory.BOTypes.USER);

        @FXML
        void btnActionOnAction(ActionEvent event) {
              boolean isValidated = validateFields();

                if (!isValidated) {
                     return;
                }
                if (btnAction.getText().equals("Add")) {
                        UserDto userDto = new UserDto(txtEmail.getText(), txtName.getText(), txtAddress.getText(), txtPassword.getText(), "images/addUserImage.png");
                      boolean isSaved = userBO.saveUser(userDto);

                        if (isSaved) {
                                new Alert(Alert.AlertType.CONFIRMATION, "User Saved");
                                clearFields();
                                closeTheWindow();

                                //Load User Details
                                userFormController.loadUserDetails();
                        } else {
                                new Alert(Alert.AlertType.ERROR, "User Doesnt Saved").show();
                        }
                } else {
                        UserDto userDto = new UserDto(txtEmail.getText(), txtName.getText(), txtAddress.getText(), txtPassword.getText());
                        boolean isUpdated = userBO.updateUser(userDto);

                        if (isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "User Updated").show();

                                //Load User Details
                                userFormController.loadUserDetails();


                        } else {
                                new Alert(Alert.AlertType.ERROR, "User Doesnt Updated").show();
                        }
                }

        }

        private void clearFields(){
                txtEmail.clear();
                txtName.clear();
                txtAddress.clear();
                txtPassword.clear();
        }

        private boolean validateFields() {
                boolean isEmailValid = txtEmail.getText().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
                if (!isEmailValid) {
                        txtEmail.requestFocus();
                        txtEmail.getStyleClass().add("mfx-text-field-error");
                        return false;
                }

                txtEmail.getStyleClass().remove("mfx-text-field-error");

                boolean isNameValid = txtName.getText().matches("^[\\p{L} '-]+$");
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


                boolean isPasswordValid = txtPassword.getText().matches("^[a-zA-Z0-9@#]{3,}$");
                if (!isPasswordValid) {
                        txtPassword.requestFocus();
                        txtPassword.getStyleClass().add("mfx-text-field-error");
                        return false;
                }

                txtPassword.getStyleClass().remove("mfx-text-field-error");
                return true;
        }

        @FXML
        void btnCancel(ActionEvent event) {
            closeTheWindow();
        }

        private void closeTheWindow(){
                Stage userDataStage = (Stage) txtName.getScene().getWindow();
                userDataStage.close();
        }

        public void setUserFormController(UserFormController userFormController){
                this.userFormController = userFormController;
        }

        public void loadUserData(String email){
                UserDto userDto = userBO.searchUser(email);
                setFields(userDto);

        }

       public void setBtnAndLblName(String action){
                btnAction.setText(action);
                lblAction.setText(action + "User");
       }

        private void setFields(UserDto userDto) {
                txtEmail.setText(userDto.getEmail());
                txtName.setText(userDto.getName());
                txtAddress.setText(userDto.getAddress());
                txtPassword.setText(userDto.getPassword());

                //Disable Email Field
                txtEmail.setEditable(false);


        }


}
