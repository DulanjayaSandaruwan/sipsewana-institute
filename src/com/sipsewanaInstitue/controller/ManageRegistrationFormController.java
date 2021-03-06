package com.sipsewanaInstitue.controller;

import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import com.jfoenix.controls.JFXButton;
import com.sipsewanaInstitue.stages.StageList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ManageRegistrationFormController extends StageList {
    public AnchorPane childPane;
    public JFXButton btnMakeRegistration;
    public JFXButton btnViewRegistrations;
    public AnchorPane menuPane;

    public void initialize() throws IOException {
        setMakeRegistrationForm();
    }

    private void setMakeRegistrationForm() throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/MakeRegistrationForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new SlideInRight(childPane).setSpeed(0.6).play();
    }

    public void btnMakeRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        setMakeRegistrationForm();
    }

    public void btnViewRegistrationsOnAction(ActionEvent actionEvent) throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ViewRegistrationsForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new SlideInRight(childPane).setSpeed(0.6).play();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        mainMenuFormStage.show();
        manageRegistrationFormStage.close();
    }
}
