package com.example.lab03_gk;

import com.example.lab03_gk.asm02.Account;
import com.example.lab03_gk.asm02.Customer;
import com.example.lab03_gk.asm03.models.DigitalBank;
import com.example.lab03_gk.asm03.models.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    public static WithdrawController withdrawController;
    public static EnterAmountController enterAmountController;
    public static final String TITLE  = "NGAN HANG SO";

    public static final String AUTHOR  = "NGUYEN NHAT LINH";

    public static final String VERSION  = "3.0.0";
    @FXML
    public Label title;
    @FXML
    public Label author;
    @FXML
    public Label version;
    public static final String CUSTOMER_ID = "001215000001";
    public static final String CUSTOMER_NAME = "Linh";
    public static final DigitalBank activeBank = new DigitalBank();

    @FXML
    private Button btnCN1;
    @FXML
    private void showTTKhachHang(ActionEvent event) throws IOException {
        showCustomer();
    }
    @FXML
    private void themTKATM(ActionEvent event) throws IOException {
        activeBank.addAccountATM(CUSTOMER_ID);
        showCustomer();
    }
    @FXML
    private void themTKTinDung(ActionEvent event) throws IOException {
        activeBank.addAccountLoan(CUSTOMER_ID);
        showCustomer();
    }
    @FXML
    private void nhapTTRutTien(ActionEvent event) throws IOException {
        nhapThongTinRutTien();
    }
    @FXML
    private void xemLichSuGD(ActionEvent event) throws IOException {
        showHistory();
    }
    @FXML
    private void Thoat(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    private static void showHistory() throws IOException {


        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer!=null){
            customer.printHistory(customer);
        }

    }
    private static void showCustomer() throws IOException {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer!=null){
            customer.displayInformation(customer);
        }
    }

    public void nhapThongTinRutTien() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("withdraw.fxml"));
        Parent parent = fxmlLoader.load();
        withdrawController = (WithdrawController) fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Rút tiền");
        stage.setScene(scene);
        stage.showAndWait();
        String soTk = withdrawController.SoTaiKhoan;

        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer!=null && customer.isAccountExisted(soTk)){
            FXMLLoader fxmlLoader1 = new FXMLLoader(MainApplication.class.getResource("enterAmount.fxml"));
            Parent parent1 = fxmlLoader1.load();
            enterAmountController = (EnterAmountController) fxmlLoader1.getController();
            Scene scene1 = new Scene(parent1);
            Stage stage1 = new Stage();
            stage1.setTitle("Rút tiền");
            stage1.setScene(scene1);
            stage1.showAndWait();
            String SoTien = enterAmountController.SoTien;
            Double soTien = Double.parseDouble(SoTien);
            activeBank.withdraw(CUSTOMER_ID,soTk,soTien);
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"So tai khoan khong ton tai");
            nhapThongTinRutTien();
        }
    }

}