package com.example.services;

import com.example.database.Database;
import com.example.models.Projeto;
import com.example.models.Tarefa;
import com.example.models.User;
import com.example.models.Utilizador;

public class FuncionalidadesService {
    Database database;
    Projeto projeto;
    Tarefa tarefa;
    Utilizador utilizador;
    User user;

    public FuncionalidadesService(Database database) {
        this.database = database;
    }

    public void removeProjeto(Projeto projeto) {
        this.database.getProjetos().remove(projeto);
    }

    public void removeTarefa(Tarefa tarefa) {
        this.database.getTarefas().remove(tarefa);
    }

    public void removeConvidadosDoProjeto(User user) {
        this.database.getUtilizadores().remove(user);
    }
}
