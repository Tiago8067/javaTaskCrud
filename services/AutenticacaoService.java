package services;

import java.util.*;
import models.*;
import exceptions.*;

public class AutenticacaoService {
    ArrayList<Utilizador> utilizadores = new ArrayList<>();

    public AutenticacaoService(ArrayList<Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    public Utilizador login(String username) {
        for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getUsername().equals(username)) {
                return utilizadores.get(i);
            }
        }
        return null;
    }

    public void registar(Utilizador utilizador) {
        utilizadores.add(utilizador);
    }

    public boolean verificarUsername(String username) throws UsernameDuplicatedException {
        for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getUsername().equals(username)) {
                throw new UsernameDuplicatedException("O username está duplicado!!!");
            }
        }
        return true;
    }
}
