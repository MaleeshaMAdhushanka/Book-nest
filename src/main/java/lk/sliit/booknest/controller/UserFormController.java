package lk.sliit.booknest.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.sliit.booknest.bo.BoFactory;
import lk.sliit.booknest.bo.custom.UserBO;
import lk.sliit.booknest.tm.UserTm;

import java.io.IOException;

public class UserFormController {


        @FXML
        private TableColumn<UserTm, String> colAddress;

        @FXML
        private TableColumn<UserTm, String> colEmail;

        @FXML
        private TableColumn<UserTm, String> colName;

        @FXML
        private TableColumn<UserTm, String> colRemove;

        @FXML
        private TableColumn<UserTm, String> colUpdate;

        @FXML
        private TableView<UserTm> tblUser;

        @FXML
        private MFXTextField txtSearch;

        @FXML
        private AnchorPane userPane;

        private final UserBO userBO = (UserBO) BoFactory.getInstance().getBO(BoFactory.BOTypes.USER);

        //using JvaFX Tableview
        /*
        This method configures two custom columns in a TableView for managing users:

        Remove Button: A button with a delete icon that deletes a user from the table and shows a confirmation or error alert based on the outcome.

        Update Button: A button with an edit icon that opens an update dialog for the selected user.
         */
        //Table walata data load karaddi using Observable List

        public void initialize(){
                setCellValueFactory();
                loadUserDetails();
        }

        public void loadUserDetails() {
                ObservableList<UserTm> userTms = FXCollections.observableArrayList();
                userBO.getAllUsers().forEach(userDto -> {
                        userTms.add(new UserTm(userDto.getName(),userDto.getEmail(),userDto.getAddress()));
                });
                tblUser.setItems(userTms);
        }

        private void setCellValueFactory() {
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));



                Callback<TableColumn<UserTm, String>, TableCell<UserTm, String>> colRemoveCellFactory
                        = new Callback<TableColumn<UserTm, String>, TableCell<UserTm, String>>()
                {
                        @Override
                        public TableCell<UserTm,String> call(final TableColumn<UserTm, String> param)
                        {
                                final TableCell<UserTm, String> cell = new TableCell<UserTm, String>()
                                {
                                        final MFXButton btn = new MFXButton("");

                                        {
                                                ImageView delete = new ImageView(new Image("/images/remove.png"));
                                                delete.setFitHeight(30);
                                                delete.setPreserveRatio(true);

                                                btn.setGraphic(delete);
                                                btn.setCursor(javafx.scene.Cursor.HAND);
                                                btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white");



                                                btn.setOnAction(event -> {
                                                        UserTm tm = getTableView().getItems().get(getIndex());
                                                        boolean isDeleted = userBO.deleteUser(tm.getEmail());

                                                        //Alert
                                                        if (isDeleted){
                                                                new Alert(Alert.AlertType.CONFIRMATION,"User Deleted").show();
                                                        }
                                                        else {
                                                                new Alert(Alert.AlertType.ERROR, "User Doesnt Deleted").show();
                                                        }


                                                        tblUser.getItems().remove(getIndex());
                                                });

                                                btn.getStyleClass().add("mfx-button-remove");
                                        }

                                        @Override
                                        public void updateItem(String item, boolean empty)
                                        {
                                                super.updateItem(item, empty);
                                                if (empty) {
                                                        setGraphic(null);
                                                        setText(null);
                                                }
                                                else {
                                                        setGraphic(btn);
                                                        setText(null);
                                                }
                                        }
                                };
                                return cell;
                        }
                };

                colRemove.setCellFactory(colRemoveCellFactory);



                Callback<TableColumn<UserTm, String>, TableCell<UserTm, String>> colUpdateCellFactory
                        = new Callback<>() {
                        @Override
                        public TableCell<UserTm, String> call(final TableColumn<UserTm, String> param) {
                                final TableCell<UserTm, String> cell = new TableCell<>() {
                                        final MFXButton btn = new MFXButton("");

                                        {


                                                ImageView update = new ImageView(new Image("/images/edit.png"));
                                                update.setFitHeight(30);
                                                update.setPreserveRatio(true);

                                                btn.setGraphic(update);
                                                btn.setCursor(javafx.scene.Cursor.HAND);
                                                btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white");



                                                btn.setOnAction(event -> {
                                                        UserTm tm = getTableView().getItems().get(getIndex());

                                                        //Catch the exception
                                                        try {
                                                                updateOnAction(tm.getEmail());
                                                        } catch (IOException e) {
                                                                throw new RuntimeException(e);
                                                        }

                                                });

                                                btn.getStyleClass().add("mfx-button-update");
                                        }

                                        @Override
                                        public void updateItem(String item, boolean empty) {
                                                super.updateItem(item, empty);
                                                if (empty) {
                                                        setGraphic(null);
                                                        setText(null);
                                                } else {
                                                        setGraphic(btn);
                                                        setText(null);
                                                }
                                        }
                                };
                                return cell;
                        }
                };
                colUpdate.setCellFactory(colUpdateCellFactory);

        }


        @FXML
        void btnAddOnAction(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/userDataForm.fxml"));
                Parent rootNode = loader.load();

                UserDataFormController userDataFormController = loader.getController();
                userDataFormController.setUserFormController(this);
                //Set Button Name
                userDataFormController.setBtnAndLblName("Add");


                Scene scene = new Scene(rootNode);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Add User");
                stage.show();
        }

        private void updateOnAction(String email) throws IOException {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/userDataForm.fxml"));
                Parent rootNode = loader.load();

                UserDataFormController userDataFormController = loader.getController();
                userDataFormController.setUserFormController(this);
                //Set Button Name
                userDataFormController.setBtnAndLblName("Update");
                userDataFormController.loadUserData(email);

                Scene scene = new Scene(rootNode);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Update User");
                stage.show();
        }


        @FXML
        void btnSearchOnAction(ActionEvent event) {
                String keyword = txtSearch.getText().trim().toLowerCase();
                if (keyword.isEmpty()) {
                        loadUserDetails();
                } else {
                        FilteredList<UserTm> filteredData = new FilteredList<>(tblUser.getItems(), userTm ->
                                userTm.getEmail().toLowerCase().contains(keyword) ||
                                        userTm.getName().toLowerCase().contains(keyword) ||
                                        userTm.getAddress().toLowerCase().contains(keyword)
                        );
                        tblUser.setItems(filteredData);
                }
        }



}
