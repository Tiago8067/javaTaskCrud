package com.example.models;

import com.example.database.*;
import com.example.exceptions.NomeDuplicatedException;

import java.util.*;
import com.example.utils.*;
import com.example.services.*;

public class User extends Utilizador {
    Database database;
    AutenticacaoService autenticacaoService;
    Util util;
    Scanner scanner;
    Utilizador utilizador;
    String username;
    Projeto projeto;
    Tarefa tarefa;
    private int idUser;

    public User() {
        super();
    }

    public User(String username) {
        super(username);
    }

    public User(String username, int idUser) {
        super(username, idUser);
    }

    public User(String username, Database database, int idUser) {// , String email, String password, String nome, String
                                                                 // genero,
        // String morada
        super(username, idUser);
        this.database = database;
        this.util = new Util(database);
        this.autenticacaoService = new AutenticacaoService(database);
        this.scanner = new Scanner(System.in);
    }

    // Contrutor para associar tarefas a um projeto pretendido
    public User(String username, Database database, int idUser, Projeto projeto, Tarefa tarefa) {
        super(username, idUser);
        this.database = database;
        this.util = new Util(database);
        this.autenticacaoService = new AutenticacaoService(database);
        this.scanner = new Scanner(System.in);
        this.projeto = projeto;
        this.tarefa = tarefa;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void listarProjetos() {
        for (int i = 0; i < this.database.getProjetos().size(); i++) {
            System.out.println("Id: " + this.database.getProjetos().get(i).getIdProjeto() + "\t->" + "Nome do projeto: "
                    + this.database.getProjetos().get(i).getNomeProjeto());
        }
    }

    public void listarTarefas() {
        for (int i = 0; i < this.database.getTarefas().size(); i++) {
            System.out.println("Id: " + this.database.getTarefas().get(i).getIdTarefa() + "\t->"
                    + "Descricao da tarefa: " + this.database.getTarefas().get(i).getCurtaDescricao());
        }
    }

    public void editaDadosDaSuaConta() {
    }

    // nome do projeto tem de ser unico
    public void criaProjeto() {
        String nomeProjeto; // , nomeCliente, precoPorHora;
        int idProjeto = 0;

        do {
            System.out.printf("Insira o nome do Projeto: ");
            nomeProjeto = this.scanner.nextLine();

            if (nomeProjeto.length() < 4) {
                System.out.println("O Nome do Projeto tem obrigatoriamente 4 carateres!");
            }
        } while (nomeProjeto.length() < 4);

        try {
            this.util.verificarNomeProjeto(nomeProjeto.toLowerCase());
        } catch (NomeDuplicatedException e) {
            System.out.println(e.getMessage());
            return;
        }

        // System.out.printf("Insira o nome do Cliente: ");
        // nomeCliente = this.scanner.next();

        // System.out.printf("Insira o preco por hora: ");
        // precoPorHora = this.scanner.next();

        for (int i = 0; i < this.database.getProjetos().size(); i++) {
            idProjeto++;
        }
        this.projeto = new Projeto(nomeProjeto.toLowerCase(), idProjeto); // , nomeCliente, precoPorHora, idProjeto

        this.autenticacaoService.adicionaProjeto(this.projeto); // NOMEPROJETO - VERIFCAR QUANDO SE
                                                                // ADICIONA USA O
                                                                // MESMO NOME PARA DAR MENSAGEM DE ERRO
    }

    // quando cria inicia uma tarefa, indicando uma curta descricao e data e hora de
    // inicio, se data e hora nao inseridas atribuir o data e hora ATUAL
    public void criaTarefa() {
        String curtaDescricao; // , dataInicioHora, dataHoraTermino;
        int idTarefa = 0;

        do {
            System.out.printf("Insira a descricao da tarefa: ");
            curtaDescricao = this.scanner.nextLine();

            if (curtaDescricao.length() < 4) {
                System.out.println("A Descricao da Tarefa tem obrigatoriamente 4 carateres!");
            }
        } while (curtaDescricao.length() < 4);

        try {
            this.util.verificarDescricaoTarefa(curtaDescricao.toLowerCase());
        } catch (NomeDuplicatedException e) {
            System.out.println(e.getMessage());
            return;
        }

        // System.out.printf("Insira a data e hora de inicio da tarefa: ");
        // dataInicioHora = this.scanner.next();

        // System.out.printf("Insira a data e hora de termino da tarefa: ");
        // dataHoraTermino = this.scanner.next();

        for (int i = 0; i < this.database.getTarefas().size(); i++) {
            idTarefa++;
        }
        this.tarefa = new Tarefa(curtaDescricao.toLowerCase(), idTarefa); // dataInicioHora, dataHoraTermino

        this.autenticacaoService.adicionaTarefa(this.tarefa);
    }

    // associar tarefas
    public void agrupaTarefaParaProjeto() {
        int idUtilizadorAssociador, idProjetoAssociarTarefas, idTarefaAssociadaNoProjeto, opcaoVerProjetos,
                opcaoEscolheProjeto;

        System.out.printf("Verifique o seu Username, para puder agrupar Tarefas a Projetos: ");
        this.username = scanner.next();

        this.utilizador = this.autenticacaoService.login(this.username);

        if (this.utilizador == null) {
            System.out.println("Nao existe este utilizador!\n");
            return;
        }

        idUtilizadorAssociador = this.utilizador.getId();

        System.out.println("O id do user que vai realizar o agrupamento e: " + idUtilizadorAssociador);

        while (true) {
            System.out.println("\n1 - Sim");
            System.out.println("2 - Nao");

            System.out.printf("\nPretende ver a sua lista de projetos: ");
            opcaoVerProjetos = scanner.nextInt();

            switch (opcaoVerProjetos) {
                case 1:
                    listarProjetos();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opcao Invalida!!!\nEscolha a opcao correta.");
                    break;
            }
            System.out.println("Insere o Porjeto que vais agrupar?");
            opcaoEscolheProjeto = scanner.nextInt();
        }
    }

    public void editaDadosProjeto() {

    }

    public void removeProjeto() {
    }

    // um utilizador tanto pode remover tarefas no estado EM CURSO, como no estado
    // FINALIZADO
    public void removeTarefa() {
    }

    // FIM, se data e hora de fim nao inseridas atribuir o data e hora ATUAL
    public void terminaTarefa() {
    }

    // pode listar tarefas no estado EM CURSO
    // tem de se obter o tempo total usado na tarefa realizada
    public void listarTarefasEmCurso() {
    }

    // pode listar tarefas no estado FIANLIZADO
    // ENTRE DUAS Datas
    public void listarTarefasFinalizadas() {
    }

    public void convidaUtilizadorParaParticiparNumProjeto() {
    }

    public void aceitaConvite() {
    }

    public void removeConvidados() {
    }
}