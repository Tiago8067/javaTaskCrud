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

}
