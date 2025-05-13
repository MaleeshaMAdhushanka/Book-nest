package lk.sliit.booknest.controller;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lk.sliit.booknest.bo.custom.impl.AdminBOImpl;

import java.io.IOException;


public class AdminDashboardMainFormController {


        @FXML
        private MFXButton btnBook;

        @FXML
        private MFXButton btnBookBorrow;

        @FXML
        private MFXButton btnBranch;

        @FXML
        private MFXButton btnDashboard;

        @FXML
        private MFXButton btnSetting;

        @FXML
        private MFXButton btnUser;

        @FXML
        private Circle cirAdminImage;

        @FXML
        private AnchorPane holderPane;

        @FXML
        private Label idAdminName;

        @FXML
        private Pane imgAndNameHolderPane;


        public void initialize() throws IOException {
                setAdminNameAndImage(true);
                loadAdminDashBoardForm();
        }

        private void loadAdminDashBoardForm() throws IOException {
                setButtonColors(Pages.DASHBOARD);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminDashBoardForm.fxml"));
                Pane dashboardPane = (Pane) fxmlLoader.load();
                holderPane.getChildren().clear();
                holderPane.getChildren().add(dashboardPane);
        }

        private void setAdminNameAndImage(boolean flag) {
                imgAndNameHolderPane.setVisible(flag);
                Platform.runLater(()-> {
                        idAdminName.setText(AdminBOImpl.loggedAdmin.getUsername());
                        Image image = new Image(AdminBOImpl.loggedAdmin.getImgUrl());
                        cirAdminImage.setFill(new ImagePattern(image));
                });
        }


        @FXML
        void btnBookOnAction(ActionEvent event) throws IOException {
                setButtonColors(Pages.BOOK);
                setAdminNameAndImage(true);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookForm.fxml"));
                Pane bookPane = (Pane) fxmlLoader.load();
                holderPane.getChildren().clear();
                holderPane.getChildren().add(bookPane);

        }

        @FXML
        void btnBorrowBookOnAction(ActionEvent event) throws IOException {
                setButtonColors(Pages.BORROW_BOOK);
                setAdminNameAndImage(true);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminBorrowBookForm.fxml"));
                Pane borrowBookPane = (Pane) fxmlLoader.load();
                holderPane.getChildren().clear();
                holderPane.getChildren().add(borrowBookPane);

        }

        @FXML
        void btnBranchOnAction(ActionEvent event) throws IOException {
                setButtonColors(Pages.BRANCH);
                setAdminNameAndImage(true);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/branchForm.fxml"));
                Pane borrowBookPane = (Pane) fxmlLoader.load();
                holderPane.getChildren().clear();
                holderPane.getChildren().add(borrowBookPane);

        }

        @FXML
        void btnDashboardOnAction(ActionEvent event) throws IOException {
                setButtonColors(Pages.DASHBOARD);
                setAdminNameAndImage(true);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminDashBoardForm.fxml"));
                Pane dashboardPane = (Pane) fxmlLoader.load();
                holderPane.getChildren().clear();
                holderPane.getChildren().add(dashboardPane);


        }

        @FXML
        void btnLogoutOnAction(ActionEvent event) throws IOException {
                Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/mainForm.fxml"));
                Scene scene = new Scene(rootNode);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Login");
                stage.show();

                //Close the Current Window
                Stage dashboard= (Stage) holderPane.getScene().getWindow();
                dashboard.close();

        }

        @FXML
        void btnSettingsOnAction(ActionEvent event) throws IOException {
                setButtonColors(Pages.SETTINGS);
                setAdminNameAndImage(false);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminSettingsForm.fxml"));
                Pane settingPane = (Pane) fxmlLoader.load();
                holderPane.getChildren().clear();
                holderPane.getChildren().add(settingPane);

        }

        @FXML
        void btnUserOnAction(ActionEvent event) throws IOException {
                setButtonColors(Pages.USER);
                setAdminNameAndImage(true);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userForm.fxml"));
                Pane userPane = (Pane) fxmlLoader.load();
                holderPane.getChildren().clear();
                holderPane.getChildren().add(userPane);

        }

        public enum Pages{
                DASHBOARD,BOOK, BORROW_BOOK, BRANCH, SETTINGS, USER
        }
        private void setButtonColors(Pages page) {
                btnDashboard.getStyleClass().remove("mfx-button-Dashboard-active");
                btnBook.getStyleClass().remove("mfx-button-Book-active");
                btnBookBorrow.getStyleClass().remove("mfx-button-BorrowBooks-active");
                btnBranch.getStyleClass().remove("mfx-button-Branch-active");
                btnSetting.getStyleClass().remove("mfx-button-Settings-active");
                btnUser.getStyleClass().remove("mfx-button-User-active");

                switch (page) {
                        case DASHBOARD:
                                btnDashboard.getStyleClass().add("mfx-button-Dashboard-active");
                                break;
                        case BOOK:
                                btnBook.getStyleClass().add("mfx-button-Book-active");
                                break;
                        case BORROW_BOOK:
                                btnBookBorrow.getStyleClass().add("mfx-button-BorrowBooks-active");
                                break;
                        case BRANCH:
                                btnBranch.getStyleClass().add("mfx-button-Branch-active");
                                break;
                        case SETTINGS:
                                btnSetting.getStyleClass().add("mfx-button-Settings-active");
                                break;
                        case USER:
                                btnUser.getStyleClass().add("mfx-button-User-active");
                                break;
                }
        }


}
