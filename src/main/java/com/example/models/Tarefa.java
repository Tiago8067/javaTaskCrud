package com.example.models;

import java.io.Serializable;

public class Tarefa implements Serializable {

    // id da tarefa
    // associar por ids
    private String curtaDescricao;
    private String dataInicioHora;
    private String dataHoraTermino;
    private int idTarefa = 0;
    Projeto projeto;
    private int idProjeto;

    public Tarefa() {
    }

    public Tarefa(String curtaDescricao, int idTarefa) { // String dataInicioHora, String dataHoraTermino
        this.curtaDescricao = curtaDescricao;
        // this.dataInicioHora = dataInicioHora;
        // this.dataHoraTermino = dataHoraTermino;
        this.idTarefa = idTarefa;
    }

    public Tarefa(int idTarefa, int idProjeto) {
        this.idTarefa = idTarefa;
        this.idProjeto = idProjeto;
    }

    public String getCurtaDescricao() {
        return this.curtaDescricao;
    }

    public void setCurtaDescricao(String curtaDescricao) {
        this.curtaDescricao = curtaDescricao;
    }

    public String getDataInicioHora() {
        return this.dataInicioHora;
    }

    public void setDataInicioHora(String dataInicioHora) {
        this.dataInicioHora = dataInicioHora;
    }

    public String getDataHoraTermino() {
        return this.dataHoraTermino;
    }

    public void setDataHoraTermino(String dataHoraTermino) {
        this.dataHoraTermino = dataHoraTermino;
    }

    public int getIdTarefa() {
        return this.idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

}
