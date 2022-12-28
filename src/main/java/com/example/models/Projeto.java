package com.example.models;

import java.io.Serializable;
import com.example.database.Database;

public class Projeto implements Serializable {

    private String nomeProjeto;
    private String nomeCliente;
    private String precoPorHora;
    Database database;

    public Projeto() {
    }

    public Projeto(String nomeProjeto, Database database) {
        this.nomeProjeto = nomeProjeto;
        this.database = database;
    }

    public Projeto(String nomeProjeto, String nomeCliente, String precoPorHora) {
        this.nomeProjeto = nomeProjeto;
        this.nomeCliente = nomeCliente;
        this.precoPorHora = precoPorHora;
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

}
