package com.example.lab03_gk.asm02;

import com.example.lab03_gk.HistoryController;
import com.example.lab03_gk.MainApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import com.example.lab03_gk.asm03.models.LoansAccount;
import com.example.lab03_gk.asm03.models.SavingsAccount;
import com.example.lab03_gk.asm03.models.Transaction;
import com.example.lab03_gk.asm03.models.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private static HistoryController historyController;
    private String accountNumber;
    private Double balance;

    List<Transaction> transactions = new ArrayList<>();
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Account() {
        this.accountNumber = "";
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isPremium(){
        if (this.balance >= 10000000){
            return true;
        }
        return false;
    }

    public double getTransactionFee(){
        double fee = 0.05;
        if (isPremium())
        {
            fee = 0.01;
        }
        return fee;
    }

    public void logHistory(String soTK, double amount){
        Transaction trans = new Transaction();
        trans.setAccountNumber(soTK);
        trans.setAmount(amount);
        trans.setTime(Utils.getDateTime());
        trans.setStatus(true);
        transactions.add(trans);
    }
    public void printHistory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("history.fxml"));
        Parent parent = fxmlLoader.load();
        historyController = (HistoryController) fxmlLoader.getController();

        for (Transaction tr:getTransactions()){
            if (tr.getStatus()){
                //System.out.println(tr.getAccountNumber()+ "\t|\t"+Utils.fomatBalance(tr.getAmount())+"\t\t\t\t\t|\t"+tr.getTime());

                Label lb = new Label(tr.getAccountNumber()+"\t|\t"+Utils.fomatBalance(tr.getAmount())+"\t"+tr.getTime());
                historyController.Root.getChildren().add(lb);
            }
        }
        Scene scene = new Scene(parent, 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Lịch sử giao dịch");
        stage.setScene(scene);
        stage.show();
    }
    public abstract String loaiTK();

    @Override
    public  String toString(){
        return this.accountNumber+" |\t\t\t"+this.balance+"d";
    }
}
