package com.example.services;

import com.example.models.*;
import com.example.database.*;

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
    }

    public void removerUtilizador(Utilizador utilizador) {
        this.database.getUtilizadores().remove(utilizador);
    }

    public void adicionaProjeto(Projeto projeto) {
        this.database.getProjetos().add(projeto);
    }

    public void adicionaTarefa(Tarefa tarefa) {
        this.database.getTarefas().add(tarefa);
    }
}