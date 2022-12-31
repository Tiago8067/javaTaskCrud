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

    public boolean verificarNomeProjeto(String nomeProjeto) throws NomeDuplicatedException {
        for (int i = 0; i < this.database.getProjetos().size(); i++) {
            if (this.database.getProjetos().get(i).getNomeProjeto().equals(nomeProjeto)) {
                throw new NomeDuplicatedException("O Nome do projeto está duplicado!!!");
            }
        }
        return true;
    }

    public boolean verificarDescricaoTarefa(String curtaDescricao) throws NomeDuplicatedException {
        for (int i = 0; i < this.database.getTarefas().size(); i++) {
            if (this.database.getTarefas().get(i).getCurtaDescricao().equals(curtaDescricao)) {
                throw new NomeDuplicatedException("O descricao da tarefa está duplicada!!!");
            }
        }
        return true;
    }

    public boolean verificarIdProjeto(int idProjeto) throws IdException {
        for (int i = 0; i < this.database.getProjetos().size(); i++) {
            if (this.database.getProjetos().get(i).getIdProjeto() == idProjeto) {
                return true;
            }
        }
        throw new IdException("Id Inserido Invalido!!!");
    }

    public Tarefa verificarIdTarefa(int idTarefa) throws IdException {
        for (int i = 0; i < this.database.getTarefas().size(); i++) {
            if (this.database.getTarefas().get(i).getIdTarefa() == idTarefa) {
                return null;
            } else {
                return this.database.getTarefas().get(i);
            }
        }
        throw new IdException("Id Inserido Invalido!!!");
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