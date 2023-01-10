package com.example.models;

public class Admin extends Utilizador {

    public Admin() {
    }

    public Admin(String username) {
        super(username);
    }

    public Admin(String username, String pass) {
        super(username, pass);
    }

    public Admin(String username, String email, String pass, String nome, String genero, String morada,
            String codPostal, String dataNascimento) {
        super(username, email, pass, nome, genero, morada, codPostal, dataNascimento);
    }
}