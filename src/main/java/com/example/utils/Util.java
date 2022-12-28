package com.example.utils;

import com.example.models.*;
import com.example.database.*;
import com.example.exceptions.*;

public class Util {

    Database database;

    public Util(Database database) {
        this.database = database;
    }

    public boolean verificarUsername(String username) throws UsernameDuplicatedException {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i).getUsername().equals(username)) {
                throw new UsernameDuplicatedException("O username está duplicado!!!");
            }
        }
        return true;
    }

    public String checkPermissao(Utilizador utilizador) {
        if (utilizador instanceof Admin) {
            // Admin a = (Admin) utilizador;
            // return a.getUsername();
            return "admin";
        }
        if (utilizador instanceof UserManager) {
            // UserManager um = (UserManager) utilizador;
            // return um.getUsername();
            return "usermanager";
        }
        if (utilizador instanceof User) {
            // User u = (User) utilizador;
            // return u.getUsername();
            return "user";
        }
        return "";
    }

}