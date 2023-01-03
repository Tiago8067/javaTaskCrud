package com.example.menus;

import java.util.*;
import com.example.models.*;
import com.example.controllers.*;
import com.example.utils.*;
import com.example.database.*;
import com.example.enums.EstadoUtilizador;

public class Menu {
    AutenticacaoController autenticacaoController;
    Utilizador utilizador;
    Scanner scanner;
    Util util;
    Database database;
    int opcao = 0;

    public Menu(Database database) {
        this.scanner = new Scanner(System.in);
        this.database = database;
        this.util = new Util(database);
        this.autenticacaoController = new AutenticacaoController(this.database);
    }

    public void menuPrincipal() {

        // this.util.clearConsole();

        while (true) {
            System.out.println("\n\n### Menu Principal ###");
            System.out.println("\n");
            System.out.println("1 - Login");
            System.out.println("2 - Registar");
            System.out.println("0 - Sair");
            System.out.println("\n");

            System.out.printf("Opção: ");
            this.opcao = this.scanner.nextInt();

            System.out.print("\n");
            switch (opcao) {
                case 1:
                    this.utilizador = autenticacaoController.login();

                    if (this.utilizador == null) {
                        System.out.println("Nao existe este utilizador!\n");
                        break;
                    }

                    if (this.utilizador.getEstadoUtilizador().equals(EstadoUtilizador.INATIVO)) {
                        System.out.println(
                                "Estado do utilizador inserido e INATIVO. \n Tem de ser ATIVADO para puder realizar Login");
                        break;
                    }

                    if (util.checkPermissao(this.utilizador).equals("admin")) {
                        Admin admin = new Admin(this.utilizador.getUsername(), this.database, this.utilizador.getId());
                        menuAdmin(admin);
                    }
                    if (util.checkPermissao(this.utilizador).equals("usermanager")) {
                        UserManager usermanager = new UserManager(this.utilizador.getUsername(), this.database,
                                this.utilizador.getId());
                        menuUserManager(usermanager); // usermanager
                    }
                    if (util.checkPermissao(this.utilizador).equals("user")) {
                        User user = new User(this.utilizador.getUsername(), this.database, this.utilizador.getId());
                        menuUser(user);
                    }
                    break;
                case 2:
                    this.autenticacaoController.registar();
                    this.database.atualizaFicheiro();
                    break;
                case 0:
                    System.out.println("Sair");
                    return;
                default:
                    System.out.println("Opcao Invalida!!!");
                    return;
            }
        }
    }

    public void menuAdmin(Admin admin) {

        this.util.clearConsole();

        while (true) {
            System.out.println("\n\n### Menu do Admin ###");
            System.out.println("\n");
            System.out.println("1 - Visualizar todos os registos disponiveis");
            System.out.println("2 - Alterar estado dos utilizadores");
            System.out.println("3 - Alterar permissao dos utilizadores");
            System.out.println("0 - Sair");
            System.out.println("\n");

            System.out.printf("Opção: ");

            this.opcao = this.scanner.nextInt();

            System.out.print("\n");

            switch (opcao) {
                case 1:
                    this.util.clearConsole();
                    admin.listarUtilizadores();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 2:
                    this.util.clearConsole();
                    admin.alterarEstadoDosUtilizadores();
                    this.database.atualizaFicheiro();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 3:
                    this.util.clearConsole();
                    admin.alterarPermissaoDosUtilizadores();
                    this.database.atualizaFicheiro();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 0:
                    System.out.println("Sair");
                    return;
                default:
                    System.out.println("Opcao Invalida!!!");
                    return;
            }
        }
    }

    public void menuUserManager(UserManager usermanager) {

        this.util.clearConsole();

        while (true) {
            System.out.println("\n\n### Menu do UserManager ###");
            System.out.println("\n");
            System.out.println("1 - Visualizar todos os registos disponiveis");
            System.out.println("2 - Alterar estado dos utilizadores");
            System.out.println("3 - Alterar permissao dos utilizadores");
            System.out.println("0 - Sair");
            System.out.println("\n");

            System.out.printf("Opção: ");

            this.opcao = this.scanner.nextInt();

            System.out.print("\n");

            switch (opcao) {
                case 1:
                    this.util.clearConsole();
                    usermanager.listarUtilizadores();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 2:
                    this.util.clearConsole();
                    usermanager.alterarEstadoDosUtilizadores();
                    this.database.atualizaFicheiro();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 3:
                    this.util.clearConsole();
                    usermanager.alterarPermissaoDosUtilizadores();
                    this.database.atualizaFicheiro();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 0:
                    return;
            }
        }
    }

    public void menuUser(User user) {

        this.util.clearConsole();

        while (true) {
            System.out.println("\n\n### Menu do User ###");
            System.out.println("\n");
            System.out.println("1 - Criar Projeto");
            System.out.println("2 - Listar Projetos -> so para teste");
            System.out.println("3 - Criar Tarefa");
            System.out.println("4 - Listar Tarefas -> so para teste");
            System.out.println("5 - Agrupar tarefas a projetos");
            System.out.println("6 - Remover Projetos");
            System.out.println("7 - Terminar Tarefa em determinada data e hora");
            System.out.println("8 - Remover Tarefas Em Curso e Finalizadas");
            System.out.println("9 - Listar Tarefas Em Curso, obter tempo total ate ao momento na Tarefa usada");
            System.out.println("10 - Listar Tarefas Finalizadas entre duas datas");
            System.out.println("11 - Convidar outro Utilizador para participar no Projeto");
            System.out.println("12 - Aceitar convites de Projetos");
            System.out.println("13 - Remover Utilizadores Convidados");
            System.out.println("0 - Sair");
            System.out.println("\n");

            System.out.printf("Opção: ");

            this.opcao = this.scanner.nextInt();

            System.out.print("\n");

            switch (opcao) {
                case 1:
                    this.util.clearConsole();
                    user.criaProjeto();
                    this.database.atualizaFicheiro();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 2:
                    this.util.clearConsole();
                    user.listarProjetos();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 3:
                    this.util.clearConsole();
                    user.criaTarefa();
                    this.database.atualizaFicheiro();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 4:
                    this.util.clearConsole();
                    user.listarTarefas();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 5:
                    this.util.clearConsole();
                    user.agrupaTarefaParaProjeto();
                    this.database.atualizaFicheiro();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 6:
                    this.util.clearConsole();
                    user.removeProjeto();
                    this.database.atualizaFicheiro();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 7:
                    this.util.clearConsole();
                    user.terminaTarefa();
                    this.database.atualizaFicheiro();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 8:
                    this.util.clearConsole();
                    user.removeTarefa();
                    this.database.atualizaFicheiro();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 9:
                    this.util.clearConsole();
                    user.listarTarefasEmCurso();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 10:
                    this.util.clearConsole();
                    user.listarTarefasFinalizadas();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 11:
                    this.util.clearConsole();
                    user.convidaUtilizadorParaParticiparNumProjeto();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 12:
                    this.util.clearConsole();
                    user.aceitaConvite();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 13:
                    this.util.clearConsole();
                    user.removeConvidadosDoProjeto();
                    this.util.waitForCont();
                    this.util.clearConsole();
                    break;
                case 0:
                    System.out.println("Sair");
                    return;
                default:
                    System.out.println("Opcao Invalida!!!");
                    return;
            }
        }
    }
}