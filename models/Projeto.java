package models;

public class Projeto {

    private String nome;
    private String nomeCliente;
    private String precoPorHora;

    public Projeto() {
    }

    public Projeto(String nome, String nomeCliente, String precoPorHora) {
        this.nome = nome;
        this.nomeCliente = nomeCliente;
        this.precoPorHora = precoPorHora;
    }

}
