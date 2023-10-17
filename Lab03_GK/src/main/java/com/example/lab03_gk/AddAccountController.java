package com.example.lab03_gk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAccountController {
    public String SoTK;
    @FXML
    public TextField txtSoTK;
    @FXML
    public Button btnDone;
    @FXML
    public void onClick(ActionEvent event){
        this.SoTK = txtSoTK.getText();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
