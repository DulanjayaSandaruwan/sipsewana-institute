package com.sipsewanaInstitue.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sipsewanaInstitue.bo.BOFactory;
import com.sipsewanaInstitue.bo.custom.StudentBO;
import com.sipsewanaInstitue.dto.StudentDTO;
import com.sipsewanaInstitue.stages.StageList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageStudentsFormController extends StageList {
    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnRefresh;
    public JFXTextField txtFind;
    public AnchorPane titlePane;
    public TextField txtContact;
    public TextField txtDOB;
    public TextField txtGender;

    @FXML
    private TableView<StudentDTO> tblStudents;

    @FXML
    private TableColumn<StudentDTO, String> clmID;

    @FXML
    private TableColumn<StudentDTO, String> clmName;

    @FXML
    private TableColumn<StudentDTO, String> clmAddress;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() {
        generateId();
        getAllStudent();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

    }

    private void getAllStudent() {
        try {
            List<StudentDTO> list = studentBO.getAll();
            ObservableList<StudentDTO> rows = FXCollections.observableArrayList();
            rows.addAll(list);
            tblStudents.setItems(rows);
            setTblStudentCellValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateId() {
        try {
            String lastPid = studentBO.getLastId();
            int newId = Integer.parseInt(lastPid.substring(1, 4))+1;
            if (newId < 10) {
                txtID.setText("S00"+newId);
            }else if (newId < 100) {
                txtID.setText("S0"+newId);
            }else {
                txtID.setText("S"+newId);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            txtID.setText("S001");
        }
    }

    private void setTblStudentCellValue() {
        clmID.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("sid"));
        clmName.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("address"));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            studentBO.update(new StudentDTO(txtID.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(), txtDOB.getText(), txtGender.getText()));
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("Student Update");
            notification.setMessage("Successfully Updated!!");
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.showAndDismiss(Duration.millis(2000));
            getAllStudent();
            clearFields();
        } catch (Exception e) {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setTitle("Student");
            notification.setMessage("Update error..No Student found!");
            notification.setAnimationType(AnimationType.POPUP);
            notification.showAndDismiss(Duration.millis(2000));
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            studentBO.delete(txtID.getText());
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("Student Delete");
            notification.setMessage("Successfully Deleted!!");
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.showAndDismiss(Duration.millis(2000));
            getAllStudent();
            clearFields();
        } catch (Exception e) {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setTitle("Student");
            notification.setMessage("Delete error..No Student found!");
            notification.setAnimationType(AnimationType.POPUP);
            notification.showAndDismiss(Duration.millis(2000));
        }
    }

    private void clearFields() {
        generateId();
        tblStudents.getSelectionModel().clearSelection();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDOB.clear();
        txtGender.clear();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void btnClear(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        mainMenuFormStage.show();
        manageStudentsFormStage.close();
    }

    public void tblStudentsOnMouseClicked(MouseEvent mouseEvent) {
        try {
            if (!tblStudents.getItems().isEmpty()) {
                txtID.setText(tblStudents.getSelectionModel().getSelectedItem().getSid());
                txtName.setText(tblStudents.getSelectionModel().getSelectedItem().getName());
                txtAddress.setText(tblStudents.getSelectionModel().getSelectedItem().getAddress());
                txtContact.setText(tblStudents.getSelectionModel().getSelectedItem().getContact());
                txtDOB.setText(tblStudents.getSelectionModel().getSelectedItem().getDob());
                txtGender.setText(tblStudents.getSelectionModel().getSelectedItem().getGender());
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
            }
        } catch (NullPointerException ex) {}
    }
    public boolean checkRegEx(String pattern, String text) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(text);
        return matcher.matches();
    }

    private void addStudent() {
        try {
            studentBO.add(new StudentDTO(txtID.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(), txtDOB.getText(), txtGender.getText()));
            getAllStudent();
            generateId();
            txtName.clear();
            txtAddress.clear();
            txtContact.clear();
            txtDOB.clear();
            txtGender.requestFocus();

            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("Program Adding");
            notification.setMessage("Successfully Added!!");
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.showAndDismiss(Duration.millis(2000));
        } catch (Exception e) {
//            e.printStackTrace();
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("RegEX");
            notification.setMessage("Error!");
            notification.setNotificationType(NotificationType.ERROR);
            notification.showAndDismiss(Duration.millis(2000));
        }
    }
    public void btnAddOnAction(ActionEvent actionEvent) {
        if (checkRegEx("^[A-z\\s\\-0-9]{1,}$", txtName.getText())) {
            txtAddress.requestFocus();
            if (checkRegEx("^[A-z\\s0-9]{1,}$", txtAddress.getText())) {
                txtContact.requestFocus();
                if (checkRegEx("^[0-9\\-]{9,}$", txtDOB.getText())) {
                    addStudent();
                } else {
                    txtContact.requestFocus();
                    errorNotificationForAddProgram("Please enter a correct decimal value for the Fee!");
                }
            } else {
                txtAddress.requestFocus();
                errorNotificationForAddProgram("Cannot add symbols..please check \" Duration \" field again!");
            }
        } else {
            txtName.requestFocus();
            errorNotificationForAddProgram("Cannot add symbols..please check \" Program \" field again!");
        }
    }

    private void errorNotificationForAddProgram(String message) {
        TrayNotification notification = new TrayNotification();
        notification.setAnimationType(AnimationType.FADE);
        notification.setTitle("RegEX");
        notification.setMessage(message);
        notification.setNotificationType(NotificationType.ERROR);
        notification.showAndDismiss(Duration.millis(2000));
    }
}
