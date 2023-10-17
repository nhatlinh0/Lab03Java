package com.example.lab03_gk.asm03.models;

import com.example.lab03_gk.asm02.Bank;
import com.example.lab03_gk.asm02.Customer;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Scanner;

/*
* Lớp định nghĩa cho class quản lý ngân hàng tiềm năng.
* */
public class DigitalBank extends Bank{
    public Customer getCustomerById(String customerId ){
        for (Customer kh: getCustomers()) {
            if (isCustomerExisted(customerId)){
                return kh;
            }
        }
        return null;
    }

    public void addCustomer(String customerId, String name) {
        //super.addCustomer();
        DigitalCustomer newCustomer = new DigitalCustomer();
        if (isCustomerExisted(customerId)) {
            //Alert alert = new Alert(Alert.AlertType.INFORMATION,"Khach Hang da co trong he thong ngan hang");
            //alert.show();
        } else {
            newCustomer.setName(name);
            newCustomer.setCustomerId(customerId);
            getCustomers().add(newCustomer);
        }
    }
    public void addAccountATM(String customerId) throws IOException {
            //gọi hàm addAccount từ Bank
            //tu Bank gọi hàm addAccount từ Customer
            if (isCustomerExisted(customerId)){
                SavingsAccount sa = new SavingsAccount();
                super.addAccount(customerId, sa);
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"So CCCD khong ton tai trong he thong");
                alert.show();
            }
        }


    public  void addAccountLoan(String customerId) throws IOException {
        //gọi hàm addAcount từ Bank
        //tu Bank gọi hàm addAcount từ Customer
        if (isCustomerExisted(customerId)){
            LoansAccount la = new LoansAccount();
            super.addAccount(customerId,la);
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Số tài khoản không tồn tại");
            alert.show();
        }
    }
    /*
    * Hàm rút tiền từ ngân hàng số
    * */

    public void withdraw(String customerId, String accountNumber, double amount) throws IOException {
        if (isCustomerExisted(customerId)){
            Customer ct = getCustomerById(customerId);
            //gọi hàm withdraw của đối tượng khách hàng tìm được.
            //goi tu lop DigitalCustomer
            ct.withdraw(accountNumber,amount);
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Khach Hang khong ton tai trong he thong");
            alert.show();
        }
    }
}
