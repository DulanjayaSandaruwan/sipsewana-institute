package com.sipsewanaInstitue.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sipsewanaInstitue.bo.BOFactory;
import com.sipsewanaInstitue.bo.custom.ProgramBO;
import com.sipsewanaInstitue.bo.custom.RegistrationDetailBO;
import com.sipsewanaInstitue.bo.custom.StudentBO;
import com.sipsewanaInstitue.dto.CustomDTO;
import com.sipsewanaInstitue.dto.ProgramDTO;
import com.sipsewanaInstitue.dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class ViewRegistrationsFormController {
    public AnchorPane programPane;
    public AnchorPane studentPane;
    public AnchorPane titlePane;
    public TextField txtName;
    public TextField txtAddress;
    public JFXComboBox cmbSid;
    public TextField txtSid;
    public TextField txtPid;
    public TextField txtProgram;
    public TextField txtDuration;
    public TextField txtFee;
    public JFXComboBox cmbPid;
    public Label lblSid;
    public Label lblName;
    public Label lblAddress;
    public Label lblPid;
    public Label lblProgram;
    public Label lblDuration;
    public Label lblFee;
    public JFXTextField txtStudents;
    public JFXTextField txtPrograms;
    public TextField txtContact;
    public Label lblContact;
    public TextField txtDOB;
    public Label lblDOB;
    public TextField txtGender;
    public Label lblGender;
    StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    ProgramBO programBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAM);
    RegistrationDetailBO registrationDetailBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.REGISTRATIONDETAIL);
    @FXML
    private TableView<CustomDTO> tblStudentAndRegistration;
    @FXML
    private TableColumn<CustomDTO, String> clmSid;
    @FXML
    private TableColumn<CustomDTO, String> clmRegId2;
    @FXML
    private TableColumn<CustomDTO, String> clmName;
    @FXML
    private TableColumn<CustomDTO, String> clmAddress;
    @FXML
    private TableView<CustomDTO> tblProgramAndRegistration;
    @FXML
    private TableColumn<CustomDTO, String> clmRegId1;
    @FXML
    private TableColumn<CustomDTO, String> clmDate;
    @FXML
    private TableColumn<CustomDTO, String> clmProgram;

    public void initialize() {
        setProgramPane(true);
        addValuesToCmbStudent();
        addValuesToCmbProgram();
    }

    private void addValuesToCmbStudent() {
        try {
            cmbSid.getItems().clear();
            List<StudentDTO> list = studentBO.getAll();
            for (StudentDTO studentDTO : list) {
                cmbSid.getItems().add(studentDTO.getSid());
            }
        } catch (Exception e) {
        }
    }

    private void addValuesToCmbProgram() {
        try {
            cmbPid.getItems().clear();
            List<ProgramDTO> list = programBO.getAll();
            for (ProgramDTO programDTO : list) {
                cmbPid.getItems().add(programDTO.getPid());
            }
        } catch (Exception e) {
        }
    }

    private void setStudentPane(boolean value) {
        txtStudents.setDisable(value);
        cmbSid.setDisable(value);
        lblSid.setDisable(value);
        txtSid.setDisable(value);
        lblName.setDisable(value);
        txtName.setDisable(value);
        lblAddress.setDisable(value);
        txtAddress.setDisable(value);
        lblContact.setDisable(value);
        txtContact.setDisable(value);
        lblDOB.setDisable(value);
        txtDOB.setDisable(value);
        lblGender.setDisable(value);
        txtGender.setDisable(value);
        tblProgramAndRegistration.setDisable(value);
    }

    private void setProgramPane(boolean value) {
        txtPrograms.setDisable(value);
        cmbPid.setDisable(value);
        lblPid.setDisable(value);
        txtPid.setDisable(value);
        lblProgram.setDisable(value);
        txtProgram.setDisable(value);
        lblDuration.setDisable(value);
        txtDuration.setDisable(value);
        lblFee.setDisable(value);
        txtFee.setDisable(value);
        tblStudentAndRegistration.setDisable(value);
    }

    public void programPaneOnMouseEntered(MouseEvent mouseEvent) {
        setProgramPane(false);
        setStudentPane(true);
        cmbSid.getSelectionModel().clearSelection();
        txtSid.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDOB.clear();
        txtGender.clear();
        tblProgramAndRegistration.getItems().clear();
    }

    public void studentPaneOnMouseEntered(MouseEvent mouseEvent) {
        setProgramPane(true);
        setStudentPane(false);
        cmbPid.getSelectionModel().clearSelection();
        txtPid.clear();
        txtProgram.clear();
        txtDuration.clear();
        txtFee.clear();
        tblStudentAndRegistration.getItems().clear();
    }

    public void tblStudentAndRegistrationOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void tblProgramAndRegistrationOnAction(MouseEvent mouseEvent) {
    }

    public void cmbSidOnAction(ActionEvent actionEvent) {
        try {
            StudentDTO student = studentBO.search(cmbSid.getSelectionModel().getSelectedItem().toString());
            txtSid.setText(student.getSid());
            txtName.setText(student.getName());
            txtAddress.setText(student.getAddress());
            txtContact.setText(student.getContact());
            txtDOB.setText(student.getDob());
            txtGender.setText(student.getGender());

            List<CustomDTO> list = registrationDetailBO.getRegDetailsBySid(student.getSid());
            ObservableList<CustomDTO> rows = FXCollections.observableArrayList();
            rows.addAll(list);
            tblProgramAndRegistration.setItems(rows);
            setTblProgramAndRegistrationCellValue();
        } catch (Exception e) {
        }
    }

    private void setTblProgramAndRegistrationCellValue() {
        clmRegId1.setCellValueFactory(new PropertyValueFactory<CustomDTO, String>("regId"));
        clmDate.setCellValueFactory(new PropertyValueFactory<CustomDTO, String>("date"));
        clmProgram.setCellValueFactory(new PropertyValueFactory<CustomDTO, String>("program"));
    }

    private void setTblStudentAndRegistrationCellValue() {
        clmSid.setCellValueFactory(new PropertyValueFactory<CustomDTO, String>("sid"));
        clmRegId2.setCellValueFactory(new PropertyValueFactory<CustomDTO, String>("regId"));
        clmName.setCellValueFactory(new PropertyValueFactory<CustomDTO, String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<CustomDTO, String>("address"));
    }

    public void cmbPidOnAction(ActionEvent actionEvent) {
        try {
            ProgramDTO program = programBO.search(cmbPid.getSelectionModel().getSelectedItem().toString());
            txtPid.setText(program.getPid());
            txtProgram.setText(program.getProgram());
            txtDuration.setText(program.getDuration());
            txtFee.setText(program.getFee() + "");


            List<CustomDTO> list = registrationDetailBO.getRegDetailsByPid(program.getPid());
            ObservableList<CustomDTO> rows = FXCollections.observableArrayList();
            rows.addAll(list);
            tblStudentAndRegistration.setItems(rows);
            setTblStudentAndRegistrationCellValue();
        } catch (Exception e) {
        }
    }
}
