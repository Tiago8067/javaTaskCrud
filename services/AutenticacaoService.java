package services;

import java.util.*;
import models.*;
import exceptions.*;
import database.*;

public class AutenticacaoService {

    Database database;

    public AutenticacaoService(Database database) {
        this.database = database;
    }

    public Utilizador login(String username) {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i).getUsername().equals(username)) {
                return this.database.getUtilizadores().get(i);
            }
        }
        return null;
    }

    public void registar(Utilizador utilizador) {
        this.database.getUtilizadores().add(utilizador);
        listarUtilizadores();
    }

    public boolean verificarUsername(String username) throws UsernameDuplicatedException {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i).getUsername().equals(username)) {
                throw new UsernameDuplicatedException("O username está duplicado!!!");
            }
        }
        return true;
    }

    // pode se apagar
    public void listarUtilizadores() {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            System.out.println("Username: " + this.database.getUtilizadores().get(i).getUsername());
            System.out.println("Estado do Utilizador: " + this.database.getUtilizadores().get(i).getEstadoUtilizador());
        }
    }
}