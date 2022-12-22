package controllers;

import java.util.*;
import models.*;
import database.*;

public class AdminController {

    Database database;

    public AdminController(Database database) {
        this.database = database;
    }

    public void alterarEstadoDosUtilizadores() {
        this.listarUtilizadores();
    }

    public void listarUtilizadores() {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            System.out.println("Username: " + this.database.getUtilizadores().get(i).getUsername());
        }
    }
}