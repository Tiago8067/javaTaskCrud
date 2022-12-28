package com.example.models;

import java.io.Serializable;

public class Tarefa implements Serializable {
    private String curtaDescricao;
    private String dataInicioHora;
    private String dataHoraTermino;

    public Tarefa() {
    }

    public Tarefa(String curtaDescricao, String dataInicioHora, String dataHoraTermino) {
        this.curtaDescricao = curtaDescricao;
        this.dataInicioHora = dataInicioHora;
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

}
