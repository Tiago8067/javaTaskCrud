package controllers;

import java.util.*;
import models.*;
import exceptions.*;
import enums.EstadoUtilizador;

public class AdminController {
    ArrayList<Utilizador> utilizadores = new ArrayList<>();

    public AdminController(ArrayList<Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    public void alterarEstadoDosUtilizadores() {
        this.listarUtilizadores();
    }

    public void listarUtilizadores() {
        for (int i = 0; i < utilizadores.size(); i++) {
            System.out.println("Username: " + utilizadores.get(i).getUsername());
            System.out.println("Estado do Utilizador: " + utilizadores.get(i).getEstadoUtilizador());
        }
    }

}