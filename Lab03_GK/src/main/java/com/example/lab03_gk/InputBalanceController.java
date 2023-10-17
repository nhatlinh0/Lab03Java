package com.example.lab03_gk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InputBalanceController {
    public String SoDu;
    @FXML
    public TextField txtBalance;
    @FXML
    public Button btnDongY;
    @FXML
    public void onClick1(ActionEvent event){
        this.SoDu = txtBalance.getText();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
