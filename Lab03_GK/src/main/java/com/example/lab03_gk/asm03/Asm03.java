package com.example.lab03_gk.asm03;

import com.example.lab03_gk.asm02.Customer;
import com.example.lab03_gk.asm02.Account;
import com.example.lab03_gk.asm02.Bank;
import com.example.lab03_gk.asm02.User;

import com.example.lab03_gk.asm03.models.DigitalBank;
import com.example.lab03_gk.asm03.models.DigitalCustomer;
import com.example.lab03_gk.asm03.models.LoansAccount;
import com.example.lab03_gk.asm03.models.SavingsAccount;
import com.example.lab03_gk.asm03.models.Transaction;
import com.example.lab03_gk.asm03.models.Utils;

import java.io.IOException;
import java.util.Scanner;

public class Asm03 {

    private static final String TITLE  = "NGAN HANG SO";
    private static final String AUTHOR  = "DOAN MINH KHUE";
    private static final String VERSION  = "3.0.0";

    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner s = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001215000001";
    private static final String CUSTOMER_NAME = "Khue";

    private static void showCustomer() throws IOException {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer!=null){
            customer.displayInformation(customer);
        }
    }
    private static void showHistory() throws IOException {
        System.out.println("+---------------+------------------------------------+----------+");
        System.out.println("|\t"+ "LICH SU GIAO DICH" +"\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------+------------------------------------+----------+");

        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer!=null){
            customer.printHistory(customer);
        }
        System.out.println("+---------------+------------------------------------+----------+");
    }
    public void nhapThongTinRutTien() throws IOException {
        System.out.printf("Nhap so tai khoan can rut: ");
        String soTk = s.nextLine();

        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer!=null && customer.isAccountExisted(soTk)){
            System.out.printf("Nhap so tien can rut: ");
            Double soTien = s.nextDouble();
            s.nextLine();
            activeBank.withdraw(CUSTOMER_ID,soTk,soTien);
        }else {
            System.out.println("So tai khoan khong ton tai");
            System.out.println("Xin vui long nhap lai!");
            nhapThongTinRutTien();
        }
    }
    public void menu() throws IOException {
        while (true){
            System.out.println("+-------------+------------------------+--------+");
            System.out.println("|\t"+ TITLE+"\t|\t"+AUTHOR+"@"+VERSION +"\t|");
            System.out.println("+-------------+------------------------+--------+");
            System.out.println("|\t1. Thong tin Khach hang");
            System.out.println("|\t2. Them tai khoan ATM");
            System.out.println("|\t3. Them tai khoan tin dung");

            System.out.println("|\t4. Rut tien");
            System.out.println("|\t5. Lich su giao dich");
            System.out.println("|\t0. Thoat");
            System.out.println("+-------------+------------------------+--------+");
            System.out.printf("Chuc nang: ");

            int chon = s.nextInt();
            s.nextLine();
            switch (chon)
            {
                case 0:
                    System.out.println("Ket thuc chuong trinh");
                    System.exit(EXIT_COMMAND_CODE);
                    break;
                case 1:
                    activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
                    showCustomer();
                    break;
                case 2:
                    activeBank.addAccountATM(CUSTOMER_ID);
                    showCustomer();
                    break;
                case 3:
                    activeBank.addAccountLoan(CUSTOMER_ID);
                    showCustomer();
                    break;
                case 4:
                    nhapThongTinRutTien();
                    break;
                case 5:
                    showHistory();
                    break;
            }
            System.out.println("Nhap phim ENTER de tiep tuc");
            s.nextLine();
            try{
                Runtime.getRuntime().exec("cls");
            }catch (IOException ex){}
        }
    }

    public static void main(String[] args) throws IOException {
        Asm03 asm = new Asm03();
        asm.menu();
    }
}
