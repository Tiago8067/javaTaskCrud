package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.enums.EstadoPedido;
import com.example.enums.EstadoUtilizador;

public abstract class Utilizador implements Serializable {
    private int id;
    private String username;
    private String email;
    private String password;
    private String nome;
    private String genero;
    private String dataDeNascimento;
    private String codigoPostal;
    private String morada;
    private EstadoUtilizador estadoUtilizador;
    private EstadoPedido estadoPedido;
    private int idProjeto;
    // private ArrayList<Integer> idProjeto2 = new ArrayList<>();
    private int idTarefa;
    // private ArrayList<Integer> idTarefa = new ArrayList<>();
    // private Map<ArrayList<Integer>, ArrayList<Integer>> tarefasAssociadasMap;
    // private List<HashMap<Integer, Integer>> tarefasAssociadas;

    public Utilizador() {
    }

    public Utilizador(String username) {
        this.username = username;
        this.estadoUtilizador = EstadoUtilizador.INATIVO;
    }

    public Utilizador(String username, int idUtilizador) {
        this.username = username;
        this.id = idUtilizador;
        this.estadoUtilizador = EstadoUtilizador.INATIVO;
    }

    public Utilizador(String username, String email, String password, String nome, String genero, String morada) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.genero = genero;
        this.morada = morada;
        this.estadoUtilizador = EstadoUtilizador.INATIVO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EstadoUtilizador getEstadoUtilizador() {
        return estadoUtilizador;
    }

    public void setEstadoUtilizador(EstadoUtilizador estadoUtilizador) {
        this.estadoUtilizador = estadoUtilizador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public EstadoPedido getEstadoPedido() {
        return this.estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
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

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", username='" + getUsername() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", nome='" + getNome() + "'" +
                ", genero='" + getGenero() + "'" +
                ", dataDeNascimento='" + getDataDeNascimento() + "'" +
                ", codigoPostal='" + getCodigoPostal() + "'" +
                ", morada='" + getMorada() + "'" +
                ", estadoUtilizador='" + getEstadoUtilizador() + "'" +
                ", estadoPedido='" + getEstadoPedido() + "'" +
                ", idProjeto='" + getIdProjeto() + "'" +
                ", idTarefa='" + getIdTarefa() + "'" +
                "}";
    }

}