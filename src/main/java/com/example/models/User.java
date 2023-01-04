package com.example.models;

import java.util.*;

public class User extends Utilizador {
    private ArrayList<Projeto> projetos = new ArrayList<Projeto>();
    private Scanner scanner;

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
        Projeto projeto = new Projeto("testani", getUsername());
        this.projetos.add(projeto);
    }

    public void criaProjeto() {
        String nomeProjeto;
        Projeto projeto;

        do {
            System.out.printf("Insira o nome do Projeto: ");
            nomeProjeto = this.scanner.nextLine();

            if (nomeProjeto.length() < 4) {
                System.out.println("O Nome do Projeto tem obrigatoriamente 4 carateres!");
            }
        } while (nomeProjeto.length() < 4);

        projeto = new Projeto(nomeProjeto.toLowerCase(), getUsername());
        this.projetos.add(projeto);
    }

    public void listarProjetos() {
        for (int index = 0; index < this.projetos.size(); index++) {
            System.out.println(this.projetos.get(index).toString());
        }
    }

    public void provisorioTarefa() {
        Tarefa tarefa = new Tarefa("tarefa");
        this.projetos.get(0).addTarefa(tarefa);
    }

}