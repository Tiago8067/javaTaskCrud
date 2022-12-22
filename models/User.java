package models;

import enums.EstadoUtilizador;

public class User extends Utilizador {

    public User() {
        super();
    }

    public User(String username) {// , String email, String password, String nome, String genero, String morada
        super(username);
        this.setEstadoUtilizador(EstadoUtilizador.INATIVO);
    }
}
