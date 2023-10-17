package com.example.lab03_gk.asm03.models;


import com.example.lab03_gk.MainApplication;
import com.example.lab03_gk.asm02.Account;
import com.example.lab03_gk.asm02.Customer;
import com.example.lab03_gk.AccountViewController;
import com.example.lab03_gk.MainApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/*
* Lớp định nghĩa cho class quản lý thông tin khách hàng tiềm năng
* */
public class DigitalCustomer extends Customer{

    /*
    *Hàm tìm tài khoản theo số tài khoản
     */
    public static AccountViewController root;
    public Account getAcountByAcountNumber(String soTk ){
        for (Account ac: getAccounts()) {
            if (ac.getAccountNumber().equals(soTk)){
                return ac;
            }
        }
        return null;
    }

    //Hàm withdraw này kiểm tra nếu accountNumber có tồn tại
    // thì truy xuất đối tượng đó ra và gọi hàm withdraw của đối tượng đó
    public void withdraw(String accountNumber,double amount) throws IOException {
        if (isAccountExisted(accountNumber)){
            Account a = getAcountByAcountNumber(accountNumber);
            if (isLoanAcount(a)){
                ((LoansAccount)a).withdraw(amount);
            }else {
                ((SavingsAccount)a).withdraw(amount);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"So tai khoan khong ton tai");

        }
    }

    @Override
    public void  displayInformation(Customer cs) throws IOException {
        System.out.println(cs.getCustomerId()+"\t|\t\t\t"+cs.getName()+"\t|\t"+cs.checkLoaiKhachHang()+"\t|\t"+ Utils.fomatBalance(cs.getBalance()));

        //in ra cac tai khoan cu the

       FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("accountView.fxml"));
       Parent parent = fxmlLoader.load();

       root = fxmlLoader.getController();
        for (Account ac:cs.getAccounts()) {
            System.out.println((getAccounts().indexOf(ac)+1)+"\t\t"+ac.getAccountNumber()+"\t|\t\t\t"+ ac.loaiTK() +"\t|\t\t\t\t"+Utils.fomatBalance(ac.getBalance()));
            Label lb = new Label();
            lb.setText((getAccounts().indexOf(ac)+1)+"\t\t"+ac.getAccountNumber()+"\t|\t\t\t"+ ac.loaiTK() +"\t|\t\t\t\t"+Utils.fomatBalance(ac.getBalance()));
            root.Root.getChildren().add(lb);
        }
        Scene scene = new Scene(parent, 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Ngân hàng số");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void printHistory(Customer cs) throws IOException {
        //displayInformation(cs);
        //in ra lich su giao dich cac tai khoan
        for (Account ac:cs.getAccounts()) {
            ac.printHistory();
        }

    }
}
