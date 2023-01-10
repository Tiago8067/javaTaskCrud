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

    public void registoEmail() {

    }

    public void registar() {
        RegexDados regex = new RegexDados();
        int opcao;
        String username, email, pass, nome, genero, morada, codPostal, dataNascimento;

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

        System.out.print("Insira o email: ");
        email = scanner.next();

        if (!regex.validateEmail(email)) {
            System.out.println("Email Invalido, tem de se registar de novo");
            this.util.waitForCont();
            this.util.clearConsole();
            return;
        } else {
            System.out.println("Email Valido");
        }

        System.out.print("Insira a password: ");
        pass = scanner.next();

        if (!regex.isValidPassword(pass)) {
            System.out.println("A password deve ter entre 4 e 20 catacteres e conter pelo menos:" +
                    " \num digito numerico" +
                    " \numa letra maiúscula" +
                    " \numa letraminuscula" +
                    " \num carater especial" +
                    "\nnão pode ter espaços, \n tem de se registar de novo");
            this.util.waitForCont();
            this.util.clearConsole();
            return;
        } else {
            System.out.println("Password Válida !");
        }

        this.util.clearBuffer(scanner);
        System.out.print("Insira o nome: ");
        nome = scanner.nextLine();

        System.out.println("Insira o genero do utilizador: ");
        System.out.println("1 - Masculino");
        System.out.println("2 - Feminino");
        System.out.println("Caso nao se identifique seleciona outro numero");

        System.out.print("Escolha: ");
        opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                genero = "Masculino";
                System.out.println("Genero: " + genero);
                break;
            case 2:
                genero = "Feminino";
                System.out.println("Genero: " + genero);
                break;
            default:
                genero = "Genero Indefinido";
                System.out.println("Genero: " + genero);
                break;
        }

        this.util.clearBuffer(scanner);
        System.out.print("Insira a morada: ");
        morada = scanner.nextLine();

        System.out.print("Insira o codigo postal: ");
        codPostal = scanner.next();

        if (!regex.isValidCP(codPostal)) {
            System.out.println("O código Postal deve ter o seguinte padrão: ####-###, \n tem de se registar de novo");
            this.util.waitForCont();
            this.util.clearConsole();
            return;
        } else {
            System.out.print("Código Postal Válido !");
        }

        this.util.clearBuffer(scanner);
        System.out.print("Introduza data de nascimento: ");
        dataNascimento = scanner.next();

        if (regex.validateJavaDate(dataNascimento)) {
            System.out.println("Data de nascimento Válida");
        } else {
            System.out
                    .println(dataNascimento
                            + " não é uma data válida, introduza uma com o seguinte formato: dd(31)/MM(12)/AAAA");
        }

        Utilizador utilizadores = new User(username.toLowerCase(), email, pass, nome, genero, morada, codPostal,
                dataNascimento);

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

    public void editaDadosUtilizador() {
        RegexDados regex = new RegexDados();
        int opcao;
        String username, email, pass, nome, genero, morada, codPostal, dataNascimento;

        System.out.println("Alterar/editar informacoes do Utilizador");
        System.out.println("1 - Username");
        System.out.println("2 - Email");
        System.out.println("3 - Password");
        System.out.println("4 - Nome do Utilizador");
        System.out.println("5 - Genero");
        System.out.println("6 - Morada");
        System.out.println("7 - Codigo Postal");
        System.out.println("8 - Data de Nascimento");
        System.out.println("0 - Sair");

        System.out.println("Opcao: ");
        opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                do {
                    System.out.printf("Insira Novo o username do utilizador: ");
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

                utilizador.setUsername(username);

                break;
            case 2:
                System.out.print("Insira o Novo email: ");
                email = scanner.next();

                if (!regex.validateEmail(email)) {
                    System.out.println("Email Invalido, tem de se registar de novo");
                    this.util.waitForCont();
                    this.util.clearConsole();
                    return;
                } else {
                    System.out.println("Email Valido");
                }
                this.utilizador.setEmail(email);

                break;
            case 3:
                System.out.print("Insira a Nova password: ");
                pass = scanner.next();

                if (!regex.isValidPassword(pass)) {
                    System.out.println("A password deve ter entre 4 e 20 catacteres e conter pelo menos:" +
                            " \num digito numerico" +
                            " \numa letra maiúscula" +
                            " \numa letraminuscula" +
                            " \num carater especial" +
                            "\nnão pode ter espaços, \n tem de se registar de novo");
                    this.util.waitForCont();
                    this.util.clearConsole();
                    return;
                } else {
                    System.out.println("Password Válida !");
                }

                this.utilizador.setPassword(pass);

                break;
            case 4:
                this.util.clearBuffer(scanner);
                System.out.print("Insira o Novo nome: ");
                nome = scanner.nextLine();

                this.utilizador.setNome(nome);

                break;
            case 5:
                System.out.println("Insira o Novo genero: ");
                System.out.println("1 - Masculino");
                System.out.println("2 - Feminino");
                System.out.println("Caso nao se identifique seleciona outro numero");

                System.out.print("Escolha: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        genero = "Masculino";
                        System.out.println("Genero: " + genero);
                        break;
                    case 2:
                        genero = "Feminino";
                        System.out.println("Genero: " + genero);
                        break;
                    default:
                        genero = "Genero Indefinido";
                        System.out.println("Genero: " + genero);
                        break;
                }

                this.utilizador.setGenero(genero);

                break;
            case 6:
                this.util.clearBuffer(scanner);
                System.out.print("Insira a Nova morada: ");
                morada = scanner.nextLine();

                this.utilizador.setMorada(morada);

                break;
            case 7:
                System.out.print("Insira o Novo codigo postal: ");
                codPostal = scanner.next();

                if (!regex.isValidCP(codPostal)) {
                    System.out.println(
                            "O código Postal deve ter o seguinte padrão: ####-###, \n tem de se registar de novo");
                    this.util.waitForCont();
                    this.util.clearConsole();
                    return;
                } else {
                    System.out.print("Código Postal Válido !");
                }

                this.utilizador.setCodigoPostal(codPostal);

                break;
            case 8:
                this.util.clearBuffer(scanner);
                System.out.print("Introduza a Nova data de nascimento: ");
                dataNascimento = scanner.next();

                if (regex.validateJavaDate(dataNascimento)) {
                    System.out.println("Data de nascimento Válida");
                } else {
                    System.out
                            .println(dataNascimento
                                    + " não é uma data válida, introduza uma com o seguinte formato: dd(31)/MM(12)/AAAA");
                }

                this.utilizador.setDataDeNascimento(dataNascimento);

                break;
            case 0:
                return;
            default:
                System.out.println("Opcao Invalida!!!");
                return;
        }
    }

}