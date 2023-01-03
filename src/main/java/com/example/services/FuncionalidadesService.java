package com.example.services;

import com.example.database.Database;
import com.example.models.Projeto;
import com.example.models.Tarefa;
import com.example.models.User;
import com.example.models.Utilizador;
import com.example.exceptions.*;

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

    public void removeConvidadosDoProjeto(Utilizador utilizador) {
        this.database.getUtilizadoresConvidados().remove(utilizador);
    }

    // pode listar tarefas no estado EM CURSO
    // tem de se obter o tempo total usado na tarefa realizada
    public void listarTarefasEmCurso() {
    }

    // pode listar tarefas no estado FIANLIZADO
    // ENTRE DUAS Datas
    public void listarTarefasFinalizadas() {
    }

    public void registarUtilizadorConvidado(Utilizador utilizador) {
        this.database.getUtilizadoresConvidados().add(utilizador);
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

    public Projeto verificarIdProjeto(int idProjeto) throws IdException {
        for (int i = 0; i < this.database.getProjetos().size(); i++) {
            if (this.database.getProjetos().get(i).getIdProjeto() == idProjeto) {
                return this.database.getProjetos().get(i);
            }
        }
        throw new IdException("Id Inserido Invalido!!!");
    }

    public Tarefa verificarIdTarefa(int idTarefa) throws IdException {
        for (int i = 0; i < this.database.getTarefas().size(); i++) {
            if (this.database.getTarefas().get(i).getIdTarefa() == idTarefa) {
                return this.database.getTarefas().get(i);
            }
        }
        throw new IdException("Id Inserido Invalido!!!");
    }

    public Utilizador verificarIdUtilizador(int idUtilizador) throws IdException {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i).getId() == idUtilizador) {
                return this.database.getUtilizadores().get(i);
            }
        }
        throw new IdException("Id Inserido Invalido!!!");
    }

    public Utilizador verificarIdUtilizadorConvidado(int idUtilizador) throws IdException {
        for (int i = 0; i < this.database.getUtilizadoresConvidados().size(); i++) {
            if (this.database.getUtilizadoresConvidados().get(i).getId() == idUtilizador) {
                return this.database.getUtilizadoresConvidados().get(i);
            }
        }
        throw new IdException("Id Inserido Invalido!!!");
    }
}
