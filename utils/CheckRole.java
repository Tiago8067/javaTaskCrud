package utils;

import models.*;

public class CheckRole {
    public CheckRole() {

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

}