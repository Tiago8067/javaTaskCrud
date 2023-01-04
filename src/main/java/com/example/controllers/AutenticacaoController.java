package com.example.controllers;

import java.util.*;

import com.example.services.*;
import com.example.models.*;
import com.example.database.*;
import com.example.enums.EstadoUtilizador;
import com.example.exceptions.UsernameDuplicatedException;
import com.example.utils.*;

public class AutenticacaoController {
    AutenticacaoService autenticacaoService;
    Utilizador utilizador;
    Scanner scanner;
    Util util;
    Database database;

    public AutenticacaoController(Database database) {
        this.database = database;
        this.scanner = new Scanner(System.in);
        this.autenticacaoService = new AutenticacaoService(database);
        this.util = new Util(database);
    }

    public Utilizador login() {
        String username;

        this.util.clearConsole();

        System.out.printf("username: ");
        username = this.scanner.next();

        this.utilizador = this.autenticacaoService.login(username);

        return this.utilizador;
    }

    public void registar() {
        String username;

        this.util.clearConsole();

        do {
            System.out.printf("Insira o username do utilizador: ");
            username = this.scanner.next();

            if (username.length() < 4) {
                System.out.println("O Username tem obrigatoriamente 4 carateres!");
            }
        } while (username.length() < 4);

        try {
            this.autenticacaoService.verificarUsername(username.toLowerCase());
        } catch (UsernameDuplicatedException e) {
            System.out.println(e.getMessage());
            return;
        }

        /*
         * System.out.println("Insira o email do utilizador: ");
         * email = scanner.next();
         * 
         * System.out.println("Insira a password do utilizador: ");
         * pass = scanner.next();
         * 
         * System.out.println("Insira o nome de utilizador: ");
         * nome = scanner.next();
         * 
         * System.out.println("Insira o genero do utilizador: ");
         * genero = scanner.next();
         * 
         * System.out.println("Insira a morada do utilizador: ");
         * morada = scanner.next();
         */
        Utilizador utilizadores = new User(username.toLowerCase());

        this.autenticacaoService.registar(utilizadores);
    }

    public void listarUtilizadores() {
        this.autenticacaoService.listarUtilizadores();
    }

    public void alterarEstadoDosUtilizadores() {
        String username;

        System.out.printf("username que deseja alterar: ");
        username = scanner.next();

        this.utilizador = this.autenticacaoService.login(username);

        if (this.utilizador == null) {
            System.out.println("Nao existe este utilizador!\n");
            return;
        }

        utilizador.setEstadoUtilizador(EstadoUtilizador.ATIVO);
    }

    public void alterarPermissaoDosUtilizadoresAdmin() {
        int opcao;
        String username;

        System.out.printf("username que deseja alterar: ");
        username = scanner.next();

        this.utilizador = this.autenticacaoService.login(username);

        if (this.utilizador == null) {
            System.out.println("Nao existe este utilizador!\n");
            return;
        }

        this.autenticacaoService.removerUtilizador(this.utilizador);

        System.out.println("\n1 - Admin");
        System.out.println("2 - User Manager");
        System.out.println("0 - Sair");

        System.out.printf("\nInsere a Nova Permissao para o Utilizador: ");
        opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                this.utilizador = new Admin(username);
                this.utilizador.setEstadoUtilizador(EstadoUtilizador.ATIVO);
                this.autenticacaoService.registar(this.utilizador);
                break;
            case 2:
                this.utilizador = new UserManager(username);
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

    public void alterarPermissaoDosUtilizadoresUserManager() {
        int opcao;
        String username;

        System.out.printf("username que deseja alterar: ");
        username = scanner.next();

        this.utilizador = this.autenticacaoService.login(username);

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
                this.utilizador = new UserManager(username);
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