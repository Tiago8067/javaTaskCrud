package com.example.services;

import com.example.models.*;
import com.example.utils.Util;
import com.example.database.*;
import com.example.exceptions.UsernameDuplicatedException;

public class AutenticacaoService {
    Database database;
    Util util;

    public AutenticacaoService(Database database) {
        this.database = database;
        this.util = new Util(database);
    }

    public Utilizador loginIncio(String username, String pass) {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i).getUsername().equals(username)) {
                return this.database.getUtilizadores().get(i);
            }
        }
        return null;
    }

    public Utilizador login(String username) {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i).getUsername().equals(username)) {
                return this.database.getUtilizadores().get(i);
            }
        }
        return null;
    }

    public boolean verificarUsername(String username) throws UsernameDuplicatedException {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i).getUsername().equals(username)) {
                throw new UsernameDuplicatedException("O username está duplicado!!!");
            }
        }
        return true;
    }

    public void listarUtilizadores() {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            System.out.println("Username: "
                    + this.database.getUtilizadores().get(i).getUsername() + "\t-> Estado"
                    + this.database.getUtilizadores().get(i).getEstadoUtilizador() + "\tTipo de utilizador: "
                    + this.util.checkPermissao(this.database.getUtilizadores().get(i)));
        }
    }

    public void registar(Utilizador utilizador) {
        this.database.getUtilizadores().add(utilizador);
    }

    public void removerUtilizador(Utilizador utilizador) {
        this.database.getUtilizadores().remove(utilizador);
    }

}