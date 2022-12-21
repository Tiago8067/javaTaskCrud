package services;

import java.util.*;
import models.*;
import exceptions.*;
import enums.EstadoUtilizador;

public class AdminService {
    ArrayList<Utilizador> utilizadores = new ArrayList<>();

    public AdminService(ArrayList<Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    public void alterarEstadoDosUtilizadores(Utilizador utilizador) {
    }

}
