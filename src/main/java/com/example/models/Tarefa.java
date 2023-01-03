package com.example.models;

import java.io.Serializable;
import com.example.enums.EstadoTarefa;

public class Tarefa implements Serializable {
    private String curtaDescricao;
    private String dataInicioHora;
    private String dataHoraTermino;
    private int idTarefa = 0;
    Projeto projeto;
    private int idProjeto;
    private EstadoTarefa estadoTarefa;

    public Tarefa() {
    }

    public Tarefa(String curtaDescricao, String dataInicioHora, int idTarefa) {
        this.curtaDescricao = curtaDescricao;
        this.dataInicioHora = dataInicioHora;
        this.idTarefa = idTarefa;
        this.estadoTarefa = EstadoTarefa.EMCURSO;
    }

    public Tarefa(int idTarefa, int idProjeto) {
        this.idTarefa = idTarefa;
        this.idProjeto = idProjeto;
    }

    public Tarefa(String dataHoraTermino) {
        this.dataHoraTermino = dataHoraTermino;
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

    public Projeto getProjeto() {
        return this.projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public int getIdProjeto() {
        return this.idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public EstadoTarefa getEstadoTarefa() {
        return this.estadoTarefa;
    }

    public void setEstadoTarefa(EstadoTarefa estadoTarefa) {
        this.estadoTarefa = estadoTarefa;
    }

}