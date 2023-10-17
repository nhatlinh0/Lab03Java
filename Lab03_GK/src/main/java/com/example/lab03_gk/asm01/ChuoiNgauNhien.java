package com.example.lab03_gk.asm01;

import java.util.Random;

public class ChuoiNgauNhien {
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;

    /*+ Chuoi sinh ngau nhien  (tru ký tu dac biet)
    * */
    public String soNgauNhien2(){
        return Integer.toString(soNgauNhien(100,999));
    }
    public String dayChuSoNgauNhien() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int number = soNgauNhien(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    private static final Random sinh = new Random();
    public int soNgauNhien(int min, int max) {
        return sinh.nextInt((max - min) + 1) + min;
    }
}
