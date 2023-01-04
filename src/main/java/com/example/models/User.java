package com.example.models;

import java.util.*;

public class User extends Utilizador {
    private ArrayList<Projeto> projetos = new ArrayList<Projeto>();
    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();

    public User() {
        super();
    }

    public User(String username) {
        super(username);
    }

    public ArrayList<Projeto> getProjetos() {
        return this.projetos;
    }

    public void editaDadosDaSuaConta() {
    }

    public void provisoria() {
        Projeto projeto = new Projeto("testani", getUsername()); // , getUsername()
        this.projetos.add(projeto);
    }

    public void criaProjeto() {
        Scanner scanner = new Scanner(System.in);
        String nomeProjeto;
        Projeto projeto;

        System.out.printf("Insira o nome do Projeto: ");
        nomeProjeto = scanner.nextLine();

        if (nomeProjeto.length() < 4) {
            System.out.println("O Nome do Projeto tem obrigatoriamente 4 carateres!");
            return;
        }

        projeto = new Projeto(nomeProjeto.toLowerCase(), getUsername()); // , getUsername()
        this.projetos.add(projeto);

    }

    public void listarProjetos() {
        for (int index = 0; index < this.projetos.size(); index++) {
            System.out.println(this.projetos.get(index).toString());
        }
    }

    public void criarTarefa() {
        Scanner scanner = new Scanner(System.in);
        String curtaDescricao;
        Tarefa tarefa;

        System.out.printf("Insira a descricao: ");
        curtaDescricao = scanner.nextLine();

        if (curtaDescricao.length() < 4) {
            System.out.println("A Descricao tem obrigatoriamente 4 carateres!");
            return;
        }

        tarefa = new Tarefa(curtaDescricao);
        this.tarefas.add(tarefa);

    }

    public void listarTarefas() {
        for (int index = 0; index < this.tarefas.size(); index++) {
            System.out.println(this.tarefas.get(index).toString());
        }
    }

    public void agruparTarefaProjeto() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Id da tarefa: ");
        int idTarefa = scanner.nextInt();
        System.out.printf("Id do projeto: ");
        int idProjeto = scanner.nextInt();

        this.projetos.get(idProjeto).addTarefa(this.tarefas.get(idTarefa));

    }

}