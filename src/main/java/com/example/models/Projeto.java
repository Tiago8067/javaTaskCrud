package com.example.models;

import java.io.Serializable;
import com.example.database.Database;

public class Projeto implements Serializable {
    private int idProjeto;
    private int idTarefa;
    private String nomeProjeto;
    private String nomeCliente;
    private String precoPorHora;
    Database database;
    Tarefa tarefa;

    public Projeto() {
    }

    public Projeto(String nomeProjeto, Database database) {
        this.nomeProjeto = nomeProjeto;
        this.database = database;
    }

    public Projeto(String nomeProjeto, int idProjeto) { // , String nomeCliente, String precoPorHora
        this.nomeProjeto = nomeProjeto;
        // this.nomeCliente = nomeCliente;
        // this.precoPorHora = precoPorHora;
        this.idProjeto = idProjeto;
    }

    public Projeto(int idProjeto, int idTarefa) {
        this.idProjeto = idProjeto;
        this.idTarefa = idTarefa;
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

    public void setPrecoPorHora(String precoPorHora) {
        this.precoPorHora = precoPorHora;
    }

    public String getPrecoPorHora() {
        return this.precoPorHora;
    }

    public int getIdProjeto() {
        return this.idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdTarefa() {
        return this.idTarefa;
    }

    public int gerarIdProjeto(int idProjeto) {
        idProjeto++;
        return idProjeto;
    }
}