package com.example.models;

import java.io.Serializable;
import com.example.enums.EstadoTarefa;

public class Tarefa implements Serializable {
    private String curtaDescricao;
    private String dataInicioHora;
    private String dataHoraTermino;
    private EstadoTarefa estadoTarefa;

    public Tarefa(String curtaDescricao, String dataInicioHora) {
        this.curtaDescricao = curtaDescricao;
        this.dataInicioHora = dataInicioHora;
        this.estadoTarefa = EstadoTarefa.EMCURSO;
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

    public EstadoTarefa getEstadoTarefa() {
        return this.estadoTarefa;
    }

    public void setEstadoTarefa(EstadoTarefa estadoTarefa) {
        this.estadoTarefa = estadoTarefa;
    }

    @Override
    public String toString() {
        return "{" +
                " curtaDescricao='" + getCurtaDescricao() + "'" +
                ", dataInicioHora='" + getDataInicioHora() + "'" +
                ", dataHoraTermino='" + getDataHoraTermino() + "'" +
                ", estadoTarefa='" + getEstadoTarefa() + "'" +
                "}";
    }

}