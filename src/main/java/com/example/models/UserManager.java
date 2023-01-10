package com.example.models;

public class UserManager extends Utilizador {

    public UserManager() {
        super();
    }

    public UserManager(String username) {
        super(username);
    }

    public UserManager(String username, String email, String pass, String nome, String genero, String morada,
            String codPostal, String dataNascimento) {
        super(username, email, pass, nome, genero, morada, codPostal, dataNascimento);
    }
}