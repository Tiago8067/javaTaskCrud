package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Scanner;

import com.example.database.Database;

public class Projeto implements Serializable {
    static int totalProjetos = 0;
    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    private String user;
    // private ArrayList<String> usersConvidados = new ArrayList<>();
    private String nomeProjeto;
    private String nomeCliente;
    private String precoPorHora;
    private int idProjeto;
    Database database;
    // tentativa
    // Scanner scanner;

    public Projeto() {
    }

    public Projeto(String nomeProjeto, String user) { // , String user
        this.nomeProjeto = nomeProjeto;
        this.user = user;
        this.idProjeto = totalProjetos;
        totalProjetos++;
        // this.scanner = new Scanner(System.in);
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

    public int getIdProjeto() {
        return this.idProjeto;
    }

    public String getUser() {
        return this.user;
    }

    public void addTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }

    public ArrayList<Tarefa> getTarefas() {
        return this.tarefas;
    }

    @Override
    public String toString() {
        return "{" +
                " idProjeto='" + getIdProjeto() + "'" +
                ", user='" + getUser() + "'" +
                ", nomeProjeto='" + getNomeProjeto() + "'" +
                ", nomeCliente='" + getNomeCliente() + "'" +
                ", precoPorHora='" + getPrecoPorHora() + "'" +
                ", tarefas='" + getTarefas() + "'" +
                "}";
    }

}