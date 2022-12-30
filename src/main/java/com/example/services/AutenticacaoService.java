package com.example.services;

import com.example.models.*;
import com.example.utils.Util;

import org.apache.commons.collections.functors.IdentityPredicate;

import com.example.database.*;
import com.example.exceptions.IdDuplicatedException;

public class AutenticacaoService {
    Database database;
    Util util;

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

    public int adicionaId(int idUtilizador) {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            idUtilizador++;
        }
        return idUtilizador;
    }

    public void registar(Utilizador utilizador) {
        this.database.getUtilizadores().add(utilizador);
    }

    public void removerUtilizador(Utilizador utilizador) {
        this.database.getUtilizadores().remove(utilizador);
    }

    public void adicionaProjeto(Projeto projeto, int idProjeto) { // AO ADICIONAR PROJETO VERIFICAMOS O IDPROJETO
        this.database.getProjetos().add(projeto);

        try {
            this.util.verificarIdProjeto(idProjeto);
        } catch (IdDuplicatedException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    /*
     * public int adicionaIdProjeto(int idProjeto) {
     * for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
     * idProjeto++;
     * }
     * return idProjeto;
     * }
     */

    public void adicionaTarefa(Tarefa tarefa) {
        this.database.getTarefas().add(tarefa);
    }
}