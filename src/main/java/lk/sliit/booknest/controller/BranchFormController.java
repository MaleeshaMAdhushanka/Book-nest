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
import lk.sliit.booknest.bo.custom.BranchBO;
import lk.sliit.booknest.tm.BranchTm;

import java.io.IOException;


public class BranchFormController {


        @FXML
        private AnchorPane branchPane;

        @FXML
        private TableColumn<BranchTm, String> colBranchAddress;

        @FXML
        private TableColumn<BranchTm, String> colBranchAdmin;

        @FXML
        private TableColumn<BranchTm, String> colBranchID;

        @FXML
        private TableColumn<BranchTm, String> colBranchName;

        @FXML
        private TableColumn<BranchTm, String> colRemove;

        @FXML
        private TableColumn<BranchTm, String> colUpdate;

        @FXML
        private TableView<BranchTm> tblBranch;

        @FXML
        private MFXTextField txtSearch;


        BranchBO branchBO = (BranchBO) BoFactory.getInstance().getBO(BoFactory.BOTypes.BRANCH);


        public void initialize(){
                setCellValueFactory();
                loadAllBranches();
        }

        public void loadAllBranches() {
                ObservableList<BranchTm> branchTms = FXCollections.observableArrayList();

                branchBO.getAllBranch().forEach(branchDto -> {
                        branchTms.add(new BranchTm(
                                branchDto.getBranchID(),
                                branchDto.getBranchName(),
                                branchDto.getAddress(),
                                branchDto.getAdminID()
                        ));
                });
                tblBranch.setItems(branchTms);
        }

        private void setCellValueFactory() {
                colBranchID.setCellValueFactory(new PropertyValueFactory<>("branchId"));
                colBranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
                colBranchAddress.setCellValueFactory(new PropertyValueFactory<>("branchAddress"));
                colBranchAdmin.setCellValueFactory(new PropertyValueFactory<>("adminName"));

                Callback<TableColumn<BranchTm, String>, TableCell<BranchTm, String>> colRemoveCellFactory
                        = new Callback<TableColumn<BranchTm, String>, TableCell<BranchTm, String>>()
                {
                        @Override
                        public TableCell<BranchTm,String> call(final TableColumn<BranchTm, String> param)
                        {
                                final TableCell<BranchTm, String> cell = new TableCell<BranchTm, String>()
                                {
                                        final MFXButton btn = new MFXButton("");

                                        {
                                                ImageView delete = new ImageView(new Image("/assets/images/remove.png"));
                                                delete.setFitHeight(30);
                                                delete.setPreserveRatio(true);

                                                btn.setGraphic(delete);
                                                btn.setCursor(javafx.scene.Cursor.HAND);
                                                btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white");


                                                btn.setOnAction(event -> {
                                                        BranchTm tm = getTableView().getItems().get(getIndex());

                                                        boolean isDeleted = branchBO.deleteBranch(tm.getBranchId());

                                                        //Alert
                                                        if (isDeleted){
                                                                new Alert(Alert.AlertType.CONFIRMATION,"User Deleted").show();
                                                        }
                                                        else {
                                                                new Alert(Alert.AlertType.ERROR, "User Doesnt Deleted").show();
                                                        }

                                                        tblBranch.getItems().remove(getIndex());
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

                Callback<TableColumn<BranchTm, String>, TableCell<BranchTm, String>> colUpdateCellFactory
                        = new Callback<>() {
                        @Override
                        public TableCell<BranchTm, String> call(final TableColumn<BranchTm, String> param) {
                                final TableCell<BranchTm, String> cell = new TableCell<>() {
                                        final MFXButton btn = new MFXButton("");

                                        {

                                                ImageView update = new ImageView(new Image("/assets/images/edit.png"));
                                                update.setFitHeight(30);
                                                update.setPreserveRatio(true);

                                                btn.setGraphic(update);
                                                btn.setCursor(javafx.scene.Cursor.HAND);
                                                btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white");


                                                btn.setOnAction(event -> {
                                                        BranchTm tm = getTableView().getItems().get(getIndex());
                                                        //Catch the exception
                                                        try {
                                                                updateOnAction(tm.getBranchId());
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

        private void updateOnAction(String branchId) throws IOException {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/branchDataForm.fxml"));
                Parent rootNode = loader.load();
                BranchDataFormController branchDataFormController = loader.getController();
                branchDataFormController.setBranchFormController(this);

                //Set Button Name
                branchDataFormController.setBtnAndLblName("Update");
                branchDataFormController.loadBranchData(branchId);

                Scene scene = new Scene(rootNode);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Update Branch");
                stage.show();
        }

        @FXML
        void btnAddOnAction(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/branchDataForm.fxml"));
                Parent rootNode = loader.load();

                BranchDataFormController branchDataFormController = loader.getController();
                branchDataFormController.setBranchFormController(this);

                //Set Button Name
                branchDataFormController.setBtnAndLblName("Add");

                Scene scene = new Scene(rootNode);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Add Branch");
                stage.show();


        }

        @FXML
        void btnSearchOnAction(ActionEvent event) {
                String keyword = txtSearch.getText().trim().toLowerCase();
                if (keyword.isEmpty()) {
                        loadAllBranches();
                } else {
                        FilteredList<BranchTm> filteredData = new FilteredList<>(tblBranch.getItems(), branchTm ->
                                branchTm.getBranchId().toLowerCase().contains(keyword) ||
                                        branchTm.getBranchName().toLowerCase().contains(keyword) ||
                                        branchTm.getBranchAddress().toLowerCase().contains(keyword) ||
                                        branchTm.getAdminName().toLowerCase().contains(keyword)
                        );
                        tblBranch.setItems(filteredData);
                }

        }


}
