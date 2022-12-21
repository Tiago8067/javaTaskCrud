package controllers;

import java.util.*;
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

        return this.utilizador;
    }

    public void registar() {
        String username, email, pass, nome, genero, morada;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira o username do utilizador: " );
        username = scanner.next();

        System.out.println("Insira o email do utilizador: " );
        email = scanner.next();

        System.out.println("Insira a password do utilizador: " );
        pass = scanner.next();

        System.out.println("Insira o nome de utilizador: " );
        nome = scanner.next();

        System.out.println("Insira o genero do utilizador: " );
        genero = scanner.next();

        System.out.println("Insira a morada do utilizador: " );
        morada = scanner.next();

        Utilizador utilizador = new User(username, email, pass, nome, genero, morada);
        
        this.autenticacaoService.registar(utilizador);
    }
}
