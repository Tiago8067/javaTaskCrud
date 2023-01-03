package com.example.controllers;

import java.util.*;
import com.example.exceptions.UsernameDuplicatedException;
import com.example.services.*;
import com.example.models.*;
import com.example.database.*;
import com.example.utils.*;

public class AutenticacaoController {
    AutenticacaoService autenticacaoService;
    Utilizador utilizador;
    Scanner scanner;
    FuncionalidadesService funcionalidadesService;
    Util util;

    public AutenticacaoController(Database database) {
        this.scanner = new Scanner(System.in);
        this.autenticacaoService = new AutenticacaoService(database);
        this.funcionalidadesService = new FuncionalidadesService(database);
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
        String username; // , email, pass, nome, genero, morada;
        int idUtilizador = 0;

        this.util.clearConsole();

        do {
            System.out.printf("Insira o username do utilizador: ");
            username = this.scanner.next();

            if (username.length() < 4) {
                System.out.println("O Username tem obrigatoriamente 4 carateres!");
            }
        } while (username.length() < 4);

        // VERIFICA O USERNAME COM LOWERCASE
        try {
            this.funcionalidadesService.verificarUsername(username.toLowerCase());
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
        Utilizador utilizadores = new User(username.toLowerCase(), this.autenticacaoService.adicionaId(idUtilizador));

        this.autenticacaoService.registar(utilizadores);
    }

}