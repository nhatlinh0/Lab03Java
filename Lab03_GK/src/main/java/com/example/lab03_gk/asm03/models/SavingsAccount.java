package com.example.lab03_gk.asm03.models;

import com.example.lab03_gk.BillsController;
import com.example.lab03_gk.MainApplication;
import com.example.lab03_gk.asm02.Account;
import com.example.lab03_gk.asm03.interfaces.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
//import jdk.jshell.execution.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccount extends Account implements ReportService, Withdraw{
    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    private static BillsController billsController;
    public SavingsAccount() {
    }

    @Override
    public boolean withdraw(double amount) throws IOException {
        double newBalance = 0.0;
        //Số tiền rút phải lớn hơn hoặc bằng 50.000đ và là bội số của 10.000đ
        //kiểm tra xem có phải là tài khoản Premium
        if (amount >= 50000 && amount % 10000 == 0){
            if (!isPremium() && amount > SAVINGS_ACCOUNT_MAX_WITHDRAW){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Tai khoan thuong chi duoc phep toi da 5.000.000d/lan");
                alert.show();
                return false;
            }else {
                newBalance = getBalance() - amount;
                //nếu số dư mới (sau khi rút) thỏa điều kiện còn lại 50 ngàn
                if (isAccepted(newBalance)){
                    //cập nhật số dư mới vào tài khoản
                    setBalance(newBalance);
                    log(amount);
                    //ghi lai lich su giao dich
                    super.logHistory(getAccountNumber(),-amount);
                    return true;
                }
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"So tien rut phai tren 50.000 va la boi cua 10.000");
            alert.show();
        }
        return false;
    }
    @Override
    public boolean isAccepted(double amount) throws IOException {
        //số dư trong tài khoản sau khi rút phải lớn hơn 50 000
        if (amount >= 50000){
            //cho phép rút tiền
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"So Du con lai khong du 50.000 D");
            alert.show();
            log(0.0);
            return false;
        }
        //return false;
    }

    @Override
    public void log(double amount) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("bills.fxml"));
        Parent parent = fxmlLoader.load();
        billsController = (BillsController) fxmlLoader.getController();

        billsController.NgayGD.setText(Utils.getDateTime());
        billsController.ATMID.setText("DIGITAL-BANK-ATM 2022");
        billsController.STK.setText(getAccountNumber());
        billsController.SoTien.setText(Utils.fomatBalance(amount));
        billsController.SoDu.setText(Utils.fomatBalance(getBalance()));
        billsController.Phi.setText(Utils.fomatBalance(0.0));

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Biên lai");
        stage.setScene(scene);
        stage.showAndWait();
    }
    public String loaiTK(){
        return "SAVINGS";
    }
}
