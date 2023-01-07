package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

import com.example.database.Database;

public class Projeto implements Serializable {
    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    // private ArrayList<Projeto> convitesProjetos = new ArrayList<Projeto>();
    // private Map<String, ArrayList<Utilizador>> convitesProjetos = new
    // HashMap<String, ArrayList<Utilizador>>();
    private String user;
    // private ArrayList<Utilizador> usersConvidados = new ArrayList<Utilizador>();
    public ArrayList<Utilizador> usersConvidados = new ArrayList<Utilizador>();
    private String nomeProjeto;
    private String nomeCliente;
    private String precoPorHora;
    Database database;

    public Projeto() {
        this.usersConvidados = new ArrayList<Utilizador>();
    }

    public Projeto(String nomeProjeto, String user) { // , String user
        this.nomeProjeto = nomeProjeto;
        this.user = user;
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

    public void setPrecoPorHora(String precoPorHora) {
        this.precoPorHora = precoPorHora;
    }

    public String getPrecoPorHora() {
        return this.precoPorHora;
    }

    public String getUser() {
        return this.user;
    }

    public ArrayList<Tarefa> getTarefas() {
        return this.tarefas;
    }

    // retorna os utilizadores convidados
    public ArrayList<Utilizador> getUsersConvidados() {
        return this.usersConvidados;
    }

    // public ArrayList<Projeto> getConvitesProjetos() {
    // return this.convitesProjetos;
    // }

    // public Map<String, ArrayList<Utilizador>> getConvitesProjetos() {
    // return this.convitesProjetos;
    // }

    public void addTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }

    // adiciona um utilizador convidado aos projetos
    public void addUserConvidado(Utilizador user) {
        this.usersConvidados.add(user);
    }

    // public void addConviteProjeto(Projeto projeto) {
    // this.convitesProjetos.add(projeto);
    // }

    public void listarUtilizadorConvidados() {
        for (int i = 0; i < this.usersConvidados.size(); i++) {
            System.out.println(this.usersConvidados.toString());
        }
    }

    // public void addConviteProjeto(String nomeProjeto, ArrayList<Utilizador>
    // usersConvidados) {
    // this.getConvitesProjetos().put(nomeProjeto, usersConvidados);
    // }

    @Override
    public String toString() {
        return "{" +
                "username='" + getUser() + "'" +
                ", nomeProjeto='" + getNomeProjeto() + "'" +
                ", nomeCliente='" + getNomeCliente() + "'" +
                ", precoPorHora='" + getPrecoPorHora() + "'" +
                ",\n tarefas='" + getTarefas() + "'" +
                ",\n usersConvidados='" + getUsersConvidados() + "'" +
                // ",\n convites='" + getConvitesProjetos() + "'" +
                "}";
    }
}