package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.database.Database;

public class Projeto implements Serializable {
    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    private String user;
    public ArrayList<Utilizador> usersConvidados = new ArrayList<Utilizador>();
    private String nomeProjeto;
    private String nomeCliente;
    private float precoPorHora;
    Database database;

    public Projeto() {
        this.usersConvidados = new ArrayList<Utilizador>();
    }

    public Projeto(String nomeProjeto, String nomeCliente, float precoPorHora, String user) {
        this.nomeProjeto = nomeProjeto;
        this.nomeCliente = nomeCliente;
        this.precoPorHora = precoPorHora;
        this.user = user;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getNomeProjeto() {
        return this.nomeProjeto;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setPrecoPorHora(float precoPorHora) {
        this.precoPorHora = precoPorHora;
    }

    public float getPrecoPorHora() {
        return this.precoPorHora;
    }

    public String getUser() {
        return this.user;
    }

    public ArrayList<Tarefa> getTarefas() {
        return this.tarefas;
    }

    public ArrayList<Utilizador> getUsersConvidados() {
        return this.usersConvidados;
    }

    public void addTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }

    public void addUserConvidado(Utilizador user) {
        this.usersConvidados.add(user);
    }

    public void listarUtilizadorConvidados() {
        for (int i = 0; i < this.usersConvidados.size(); i++) {
            System.out.println(this.usersConvidados.toString());
        }
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + getUser() + "'" +
                ", nomeProjeto='" + getNomeProjeto() + "'" +
                ", nomeCliente='" + getNomeCliente() + "'" +
                ", precoPorHora='" + getPrecoPorHora() + "'" +
                ",\n tarefas='" + getTarefas() + "'" +
                ",\n usersConvidados='" + getUsersConvidados() + "'" +
                "}";
    }
}