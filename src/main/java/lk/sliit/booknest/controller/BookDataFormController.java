package lk.sliit.booknest.controller;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BookDataFormController {


    @FXML
    private MFXButton btnAction;

    @FXML
    private MFXComboBox<?> colBranch;

    @FXML
    private Label lblAction;

    @FXML
    private MFXTextField txtAuthor;

    @FXML
    private MFXTextField txtBookId;

    @FXML
    private MFXTextField txtGenre;

    @FXML
    private MFXTextField txtTitle;

    @FXML
    void btnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

}

