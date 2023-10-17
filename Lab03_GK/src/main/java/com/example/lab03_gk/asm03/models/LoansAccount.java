package com.example.lab03_gk.asm03.models;

import com.example.lab03_gk.BillsController;
import com.example.lab03_gk.MainApplication;
import com.example.lab03_gk.WithdrawController;
import com.example.lab03_gk.asm02.Account;
import com.example.lab03_gk.asm02.Customer;
import com.example.lab03_gk.asm03.interfaces.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoansAccount extends Account implements Withdraw, ReportService {
    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;//định nghĩa phí rút tiền cho tài khoản thường
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;//định nghĩa phí rút tiền tài khoản premium
    private static final double  LOAN_ACCOUNT_MAX_BALANCE = 100000000;// định nghĩa hạn mức tối đa cho loại tài khoản LOAN

    private static BillsController billsController;
    public LoansAccount() {
    }

    //Gán số dư  hạn mức cho tài khoản LOAN
    public void setBalance(){
        super.setBalance(LOAN_ACCOUNT_MAX_BALANCE);
    }
    /*
    *Xác định rút tiền
    *Hạn mức tín dụng là số tiền tối đa mà tổ chức tín dụng cấp để có thể sử dụng trên thẻ tín dụng
    * */
    @Override
    public boolean withdraw(double amount) throws IOException {
        double newBalance = 0.0;
        Transaction trans  = new Transaction();
        //Hạn mức rút với tài khoản Loan là 100 triệu đồng
        if (amount <= LOAN_ACCOUNT_MAX_BALANCE){
            newBalance = getBalance() - (amount + (super.getTransactionFee()*amount));

            //nếu số dư mới (sau khi rút) thỏa điều kiện còn  lại 50 ngàn
            if (isAccepted(newBalance)){
                //cập nhật số dư mới vào tài khoản
                setBalance(newBalance);
                log(amount);
                //ghi lich sư giao dich
                super.logHistory(getAccountNumber(),-amount);
                return true;
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"So tien rut vuot qua han muc");
            alert.show();
        }
        return false;
    }


    /**
     * Hàm isAccepted xác định xem giá trị có thoả "điều kiện" rút tiền hay không
     * số tiền rút vượt quá số dư hay không?
     */

    @Override
    public boolean isAccepted(double amount) throws IOException {
        //số dư trong tài khoản sau khi rút phải lớn hơn 50 000
        if (amount >= 50000){
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
        billsController.Phi.setText(Utils.fomatBalance(getTransactionFee()*amount));

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Biên lai");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public String loaiTK(){
        return "LOAN";
    }
}
