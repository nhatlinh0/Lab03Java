package com.example.lab03_gk.asm03.models;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String getDateTime(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }
    public static String getDivider(){
        return "+-----------------------------------+-------------+";
    }

    public static String fomatBalance(Double money){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String stringTienTe = currencyVN.format(money);
        return stringTienTe;
    }
}

