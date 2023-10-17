package com.example.lab03_gk.asm03.models;

import java.util.UUID;

public class Transaction {
    private final String Id;
    private String accountNumber;
    private Double amount;
    private String time;
    private Boolean status;

    public Transaction(){
        this.Id = String.valueOf(UUID.randomUUID());
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
