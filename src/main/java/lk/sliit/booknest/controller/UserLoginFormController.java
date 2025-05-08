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

import java.io.IOException;

public class UserLoginFormController {


    @FXML
    private Label lblError;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private MFXTextField txtEmail;

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    void btnAdminLogin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminLoginForm.fxml"));
        Pane adminLoginPane = (Pane) fxmlLoader.load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(adminLoginPane);


    }

    @FXML
    void btnLogin(ActionEvent event) {


    }

    @FXML
    void btnRegister(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userImageForm.fxml"));
        Pane registerPane =(Pane) fxmlLoader.load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(registerPane);

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
      btnLogin(event);
    }

}
