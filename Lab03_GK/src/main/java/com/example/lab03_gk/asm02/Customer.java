package com.example.lab03_gk.asm02;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.lab03_gk.AddAccountController;
import com.example.lab03_gk.InputBalanceController;
import com.example.lab03_gk.MainApplication;
import com.example.lab03_gk.asm01.Asm01;
import com.example.lab03_gk.asm03.models.LoansAccount;
import com.example.lab03_gk.asm03.models.SavingsAccount;
import com.example.lab03_gk.asm03.models.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public abstract class Customer extends User{
    List<Account> accounts = new ArrayList<>();
    public static AddAccountController addAccountController;
    public static InputBalanceController inputBalanceController;
    public List<Account> getAccounts() {
        return accounts;
    }

    protected boolean isPremium()
    {
        for (Account a: accounts) {
            if (a.isPremium()){
                return true;
            }
        }
        return  false;
    }

    public boolean isAccountExisted(String soTK) {
        for (Account a: accounts) {
            if (a.getAccountNumber().equals(soTK)){
                return true;
            }
        }
        return  false;
    }
    public Double getBalance(){
        double tong = 0;
        for (Account ac:accounts) {
            tong+= ac.getBalance();
        }
        return tong;
    }
    //check khach han co phải là premium
    public String checkLoaiKhachHang(){
        String loai = "Normal";
        for (Account ac: getAccounts()) {
            if (ac.isPremium()){
                loai= "Premium";
            }
        }
        return loai;
    }
    public boolean isLoanAcount(Account a){
        if (a instanceof LoansAccount){
            return true;
        }
        return false;
    }
    //Them tai khoan cho khach hang
    public void addAccount(Account newAccount) throws IOException {
        double soDu = 0.0;

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("addAccount.fxml"));
        Parent parent = fxmlLoader.load();
        addAccountController = (AddAccountController)fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Thêm tài khoản");
        stage.setScene(scene);
        stage.showAndWait();
        String soTK = addAccountController.SoTK;

        //kiem tra so tai khoan la cac con so va co do dai la 6
        String laSo = "[0-9]+";
        if (soTK.length() ==6 && soTK.matches(laSo)){
            //kiem tra xem tai khoan da co trong he thong chua
            if (isAccountExisted(soTK)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Tai khoan da ton tai");
            }else {
                //đưa vào List tài khoản accounts không phân biệt là Loan hay Saving
                newAccount.setAccountNumber(soTK);
                if (newAccount instanceof LoansAccount){
                    ((LoansAccount)newAccount).setBalance();
                }else if (newAccount instanceof SavingsAccount){
                    soDu = inputBalance(newAccount);
                    //ghi lịch sử thêm tài khoản và tiền cho người dùng
                    newAccount.logHistory(soTK, soDu);
                }
                accounts.add(newAccount);
                //ghi lịch sử thêm tài khoản và tiền cho người dùng

            }
        }else {
            Alert a = new Alert(Alert.AlertType.INFORMATION,"Sai dinh danh so tai khoan!");
            a.show();
            addAccount(newAccount);
        }
    }

    private double inputBalance(Account newAccount) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("inputBalance.fxml"));
        Parent parent = fxmlLoader.load();
        inputBalanceController = (InputBalanceController)fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Thêm số dư");
        stage.setScene(scene);
        stage.showAndWait();
        String SoDu = inputBalanceController.SoDu;
        double soDu = Double.parseDouble(SoDu);
        if (soDu<50000){
            Alert b = new Alert(Alert.AlertType.INFORMATION,"So du phai tren 50 000");
            b.show();
            inputBalance(newAccount);
        }
        newAccount.setBalance(soDu);
        return soDu;
    }

    //in ra thong tin cac tai khoan cua 1 khach hang
    public abstract void displayInformation(Customer cs) throws IOException;
    public abstract void printHistory(Customer cs) throws IOException;
    public abstract void withdraw(String accountNumber,double amount) throws IOException;

}
