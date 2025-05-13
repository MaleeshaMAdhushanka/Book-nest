package lk.sliit.booknest.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.sliit.booknest.bo.BoFactory;
import lk.sliit.booknest.bo.custom.BookBO;
import lk.sliit.booknest.bo.custom.BookTransactionBO;
import lk.sliit.booknest.bo.custom.UserBO;
import lk.sliit.booknest.dto.BorrowBookDto;
import lk.sliit.booknest.dto.UserDto;
import lk.sliit.booknest.tm.LateBookDetailTm;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDashBoardFormController {


        @FXML
        private TableColumn<?, ?> colBookID;

        @FXML
        private TableColumn<LateBookDetailTm, String> colBorrowDate;

        @FXML
        private TableColumn<LateBookDetailTm, String> colID;

        @FXML
        private TableColumn<LateBookDetailTm, String> colLateCount;

        @FXML
        private TableColumn<LateBookDetailTm, String> colReturnDate;

        @FXML
        private TableColumn<LateBookDetailTm, String> colUserID;

        @FXML
        private AnchorPane dashBoardPane;

        @FXML
        private Label lblBookCount;

        @FXML
        private Label lblDueBookCount;

        @FXML
        private Label lblUserCount;

        @FXML
        private TableView<LateBookDetailTm> tblBorrowBook;

        BookTransactionBO bookTransactionBO = (BookTransactionBO) BoFactory.getInstance().getBO(BoFactory.BOTypes.BOOK_TRANSACTION);
        UserBO userBO = (UserBO) BoFactory.getInstance().getBO(BoFactory.BOTypes.USER);
        BookBO bookBO = (BookBO) BoFactory.getInstance().getBO(BoFactory.BOTypes.BOOK);

        public void initialize(){
                setCellValueFactory();
                loadLateBookDetails();
                setLabelValues();
        }

        private void setLabelValues() {
                //Set User Count
                lblUserCount.setText(Integer.toString(userBO.getAllUsers().size()));
                //Set Book Count
                lblBookCount.setText(Integer.toString(bookBO.getAllBooks().size()));
                //Set Due Book Count
                lblDueBookCount.setText(Integer.toString(bookTransactionBO.getAllLateBookDetails().size()));
        }

        private void setCellValueFactory() {
                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
                colBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
                colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
                colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
                colLateCount.setCellValueFactory(new PropertyValueFactory<>("lateCount"));
        }

        private void loadLateBookDetails() {

                List<BorrowBookDto> allLateBookDetails = bookTransactionBO.getAllLateBookDetails();

                List<UserDto> allLateUsers = userBO.getUsersWithOverdueBooks();

//        Map<String, String> userIdToNameMap = allLateUsers.stream().collect(Collectors.toMap(UserDto::getEmail, UserDto::getName));

                Map<String, String> userIdToNameMap = new HashMap<>();
                for (UserDto user : allLateUsers) {
                        userIdToNameMap.put(user.getEmail(), user.getName());
                }

                for (BorrowBookDto borrowBookDto : allLateBookDetails) {
                        String userName = userIdToNameMap.get(borrowBookDto.getUserId());
                        tblBorrowBook.getItems().add(new LateBookDetailTm(
                                borrowBookDto.getId(),
                                userName,
                                borrowBookDto.getBookId(),
                                borrowBookDto.getBorrowDate(),
                                borrowBookDto.getReturnDate(),
                                Integer.toString(LocalDate.now().compareTo(LocalDate.parse(borrowBookDto.getReturnDate())))
                        ));
                }
        }



}


