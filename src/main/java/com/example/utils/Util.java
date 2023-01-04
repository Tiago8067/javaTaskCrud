package com.example.utils;

import com.example.models.*;

import java.io.IOException;
import java.util.Scanner;
import com.example.database.*;

public class Util {
    Database database;
    Scanner scanner;

    public Util(Database database) {
        this.database = database;
        this.scanner = new Scanner(System.in);
    }

    public String checkPermissao(Utilizador utilizador) {
        if (utilizador instanceof Admin) {
            return "admin";
        }
        if (utilizador instanceof UserManager) {
            return "usermanager";
        }
        if (utilizador instanceof User) {
            return "user";
        }
        return "";
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J"); // Serve para limpar o ecra;
        System.out.flush();
    }

    public void waitForCont() {
        System.out.println("\n\nPressiona enter para continuar");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}