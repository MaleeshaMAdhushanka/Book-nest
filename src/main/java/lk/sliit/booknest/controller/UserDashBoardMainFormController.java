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
import lk.sliit.booknest.bo.custom.impl.UserBOImpl;

import java.io.IOException;

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

        public void initialize() throws IOException {
                setUserNameAndImage(true);
                loadBookSearchForm();
        }


        private void setUserNameAndImage(boolean flag) {
                imgAndNameHolderPane.setVisible(flag);
                Platform.runLater(()-> {
                        idUserName.setText(UserBOImpl.loggedUser.getName());
                        Image image = new Image(UserBOImpl.loggedUser.getImgUrl());
                        cirUserImage.setFill(new ImagePattern(image));

                });
        }


        private void loadBookSearchForm() throws IOException {
                setButtonColors(Pages.BOOK_SEARCH);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookSearchForm.fxml"));
                Pane bookSearchPane = (Pane) fxmlLoader.load();
                holderPane.getChildren().clear();
                holderPane.getChildren().add(bookSearchPane);
        }

        @FXML
        void btnBookSearchOnAction(ActionEvent event) throws IOException {
                setButtonColors(Pages.BOOK_SEARCH);
                setUserNameAndImage(true);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookSearchForm.fxml"));
                Pane bookSearchPane = (Pane) fxmlLoader.load();
                holderPane.getChildren().clear();
                holderPane.getChildren().add(bookSearchPane);


        }

        @FXML
        void btnBorrowBookOnAction(ActionEvent event) throws IOException {
                setButtonColors(Pages.BORROW_BOOK);
                setUserNameAndImage(true);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userBorrowBookForm.fxml"));
                Pane historyPane = (Pane) fxmlLoader.load();
                holderPane.getChildren().clear();
                holderPane.getChildren().add(historyPane);


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
                setUserNameAndImage(false);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userSettingsForm.fxml"));
                Pane userSettingsPane = (Pane) fxmlLoader.load();
                holderPane.getChildren().clear();
                holderPane.getChildren().add(userSettingsPane);

        }
        public enum Pages{
                BOOK_SEARCH,BORROW_BOOK,SETTINGS
        }

        private void setButtonColors(Pages pages){

                btnBookSearch.getStyleClass().remove("mfx-button-BookSearch-active");
                btnBorrowBook.getStyleClass().remove("mfx-button-BookHistory-active");
                btnSetting.getStyleClass().remove("mfx-button-Settings-active");

                switch (pages){
                        case BOOK_SEARCH:
                                btnBookSearch.getStyleClass().add("mfx-button-BookSearch-active");
                                break;
                        case BORROW_BOOK:
                                btnBorrowBook.getStyleClass().add("mfx-button-BookHistory-active");
                                break;
                        case SETTINGS:
                                btnSetting.getStyleClass().add("mfx-button-Settings-active");
                                break;
                }
        }
    }

