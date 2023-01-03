package com.example.models;

import com.example.database.*;
import java.util.*;
import com.example.utils.*;
import com.example.services.*;
import com.example.enums.*;

public class Admin extends Utilizador {
    AutenticacaoService autenticacaoService;
    Database database;
    Util util;
    Scanner scanner;
    Utilizador utilizador;
    String username;
    UserManager userManager;

    public Admin() {
    }

    public Admin(String username) {
        super(username);
    }

    public Admin(String username, int idAdmin) {
        super(username, idAdmin);
    }

    public Admin(String username, Database database, int idAdmin) {
        super(username, idAdmin);
        this.database = database;
        this.util = new Util(database);
        this.autenticacaoService = new AutenticacaoService(database);
        this.scanner = new Scanner(System.in);
    }

    public void listarUtilizadores() {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            System.out.println("Id:" + this.database.getUtilizadores().get(i).getId() + "\t-> Username: "
                    + this.database.getUtilizadores().get(i).getUsername() + "\t-> Estado"
                    + this.database.getUtilizadores().get(i).getEstadoUtilizador() + "\tTipo de utilizador: "
                    + this.util.checkPermissao(this.database.getUtilizadores().get(i)));
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
        int opcao, idAnteriorUser;

        System.out.printf("username que deseja alterar: ");
        this.username = scanner.next();

        this.utilizador = this.autenticacaoService.login(this.username);

        if (this.utilizador == null) {
            System.out.println("Nao existe este utilizador!\n");
            return;
        }

        idAnteriorUser = this.utilizador.getId();

        this.autenticacaoService.removerUtilizador(this.utilizador);

        System.out.println("\n1 - Admin");
        System.out.println("2 - User Manager");
        System.out.println("0 - Sair");

        System.out.printf("\nInsere a Nova Permissao para o Utilizador: ");
        opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                this.utilizador = new Admin(this.username, idAnteriorUser);
                this.utilizador.setEstadoUtilizador(EstadoUtilizador.ATIVO);
                this.autenticacaoService.registar(this.utilizador);
                break;
            case 2:
                this.utilizador = new UserManager(this.username, idAnteriorUser);
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