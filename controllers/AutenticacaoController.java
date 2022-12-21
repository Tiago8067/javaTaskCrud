package controllers;

import java.util.*;

import exceptions.UsernameDuplicatedException;
import services.*;
import models.*;

public class AutenticacaoController {
    AutenticacaoService autenticacaoService;
    Utilizador utilizador;

    public AutenticacaoController(ArrayList<Utilizador> utilizadores) {
        this.autenticacaoService = new AutenticacaoService(utilizadores);
    }

    public Utilizador login() {
        String username;
        Scanner scanner = new Scanner(System.in);

        System.out.printf("username: ");
        username = scanner.next();

        this.utilizador = this.autenticacaoService.login(username);
        scanner.close();

        return this.utilizador;
    }

    public void registar() {
        String username, email, pass, nome, genero, morada;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Insira o username do utilizador: ");
            username = scanner.next();
            if (username.length() < 4) {
                System.out.println("O Username tem obrigatoriamente 4 carateres!");
            }
        } while (username.length() < 4);

        // VERIFICA O USERNAME COM LOWERCASE
        try {
            this.autenticacaoService.verificarUsername(username.toLowerCase());
        } catch (UsernameDuplicatedException e) {
            System.out.println(e.getMessage());
            scanner.close();
            return;
        }

        System.out.println("Insira o email do utilizador: ");
        email = scanner.next();

        System.out.println("Insira a password do utilizador: ");
        pass = scanner.next();

        System.out.println("Insira o nome de utilizador: ");
        nome = scanner.next();

        System.out.println("Insira o genero do utilizador: ");
        genero = scanner.next();

        System.out.println("Insira a morada do utilizador: ");
        morada = scanner.next();

        Utilizador utilizadores = new User(username.toLowerCase(), email, pass, nome, genero, morada);

        this.autenticacaoService.registar(utilizadores);
        scanner.close();
    }

}
