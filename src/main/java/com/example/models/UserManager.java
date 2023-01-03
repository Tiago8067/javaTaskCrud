package com.example.models;

import com.example.database.*;
import java.util.*;
import com.example.utils.*;
import com.example.services.*;
import com.example.enums.*;

public class UserManager extends Utilizador {
    Database database;
    AutenticacaoService autenticacaoService;
    Util util;
    Scanner scanner;
    Utilizador utilizador;
    String username;
    private int idUserManager;

    public UserManager() {
        super();
    }

    public UserManager(String username) {
        super(username);
    }

    public UserManager(String username, int idUserManager) {
        super(username, idUserManager);
    }

    public UserManager(String username, Database database, int idUserManager) {
        super(username, idUserManager);
        this.database = database;
        this.util = new Util(database);
        this.autenticacaoService = new AutenticacaoService(database);
        this.scanner = new Scanner(System.in);
    }

    public int getIdUserManager() {
        return this.idUserManager;
    }

    public void setIdUserManager(int idUserManager) {
        this.idUserManager = idUserManager;
    }

    public void listarUtilizadores() {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            System.out.println("Username: " + this.database.getUtilizadores().get(i).getUsername() + "\t-> Estado"
                    + this.database.getUtilizadores().get(i).getEstadoUtilizador() + "\tTipo de utilizador: "
                    + this.util.checkPermissao(this.database.getUtilizadores().get(i))); //
        }
    }

    public void alterarEstadoDosUtilizadores() {
        System.out.printf("username que deseja alterar: ");
        this.username = scanner.next();

        this.utilizador = this.autenticacaoService.login(this.username);

        if (this.utilizador == null) {
            System.out.println("Nao existe este utilizador!\n");
            return;
        }

        utilizador.setEstadoUtilizador(EstadoUtilizador.ATIVO);
    }

    public void alterarPermissaoDosUtilizadores() {
        int opcao;

        System.out.printf("username que deseja alterar: ");
        this.username = scanner.next();

        this.utilizador = this.autenticacaoService.login(this.username);

        if (this.utilizador == null) {
            System.out.println("Nao existe este utilizador!\n");
            return;
        }
        this.autenticacaoService.removerUtilizador(this.utilizador);

        System.out.println("\n1 - User Manager");
        System.out.println("0 - Sair");

        System.out.printf("\nInsere a Nova Permissao para o Utilizador: ");
        opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                this.utilizador = new UserManager(this.username, this.idUserManager);
                this.utilizador.setEstadoUtilizador(EstadoUtilizador.ATIVO);
                this.autenticacaoService.registar(this.utilizador);
                break;
            case 0:
                this.autenticacaoService.registar(this.utilizador);
                System.out.println("Sair");
                break;
            default:
                this.autenticacaoService.registar(this.utilizador);
                System.out.println("Opcao Invalida!!!");
                break;
        }
    }
}