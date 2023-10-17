package com.example.lab03_gk.asm03.interfaces;

import java.io.IOException;

public interface Withdraw {
    boolean withdraw(double amount) throws IOException;
    boolean isAccepted(double amount) throws IOException;
}
