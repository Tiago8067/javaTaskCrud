package com.example.controllers;

import java.util.Scanner;

import com.example.database.Database;
import com.example.models.Projeto;
import com.example.models.User;
import com.example.models.Utilizador;
import com.example.services.FuncionalidadesService;

public class FuncionalidadesController {
    Scanner scanner;
    FuncionalidadesService funcionalidadesService;
    Database database;
    Utilizador utilizador;
    // User user;

    public FuncionalidadesController(Database database) {
        this.scanner = new Scanner(System.in);
        this.funcionalidadesService = new FuncionalidadesService(database);
        this.database = database;
        this.utilizador = new User();
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

        projeto = new Projeto(nomeProjeto.toLowerCase(),
                this.utilizador.getUsername()); // ,
                                                // this.utilizador.getUsername()
        this.funcionalidadesService.criarProjeto(projeto);
    }

    public void listarProjetos() {
        this.funcionalidadesService.listarProjetos();
    }

}
