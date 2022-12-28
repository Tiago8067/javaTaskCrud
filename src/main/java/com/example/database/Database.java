package com.example.database;

import java.io.*;
import java.util.*;
import com.example.models.*;
import com.example.enums.*;
import java.util.HashMap;
import java.util.Map;

public class Database implements Serializable {
    ArrayList<Utilizador> utilizadores;
    ArrayList<Projeto> projetos;
    ArrayList<Tarefa> tarefas;
    Map<Integer, Tarefa> tarefasAssociadas;

    // diretoria padrao para guardar o ficheiro
    String path;
    File arquivo, arquivoTarefasAssociadas;

    public Database() {
        this.path = System.getProperty("user.dir");
        this.utilizadores = new ArrayList<>();
        this.projetos = new ArrayList<>();
        this.tarefas = new ArrayList<>();
        this.tarefasAssociadas = new HashMap<>();
        this.arquivo = new File(this.path, "utilizadores.dat");
        this.arquivoTarefasAssociadas = new File(this.path, "TarefasAssociadas.csv");
    }

    public void criaFicheiroSeNaoExistir() {
        if (!this.arquivo.exists() && !this.arquivo.isDirectory()) {

            Admin admin = new Admin("admin");

            admin.setEstadoUtilizador(EstadoUtilizador.ATIVO);

            this.utilizadores.add(admin);

            try {
                FileOutputStream outFileStream = new FileOutputStream(this.arquivo);
                ObjectOutputStream outDataStream = new ObjectOutputStream(outFileStream);

                outDataStream.writeObject(this.utilizadores);
                outDataStream.writeObject(this.projetos);
                outDataStream.writeObject(this.tarefas);

                outDataStream.close();

            } catch (Exception e) {
                System.out.println("Binary file input error: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream readData = new FileInputStream(this.arquivo);
                ObjectInputStream readStream = new ObjectInputStream(readData);

                // ler os arrays
                this.utilizadores = (ArrayList<Utilizador>) readStream.readObject();
                this.projetos = (ArrayList<Projeto>) readStream.readObject();
                this.tarefas = (ArrayList<Tarefa>) readStream.readObject();

                readStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void atualizaFicheiro() {

        try {
            FileOutputStream outFileStream = new FileOutputStream(this.arquivo);
            ObjectOutputStream outDataStream = new ObjectOutputStream(outFileStream);

            outDataStream.writeObject(this.utilizadores);
            outDataStream.writeObject(this.projetos);
            outDataStream.writeObject(this.tarefas);

            outDataStream.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public ArrayList<Utilizador> getUtilizadores() {
        return this.utilizadores;
    }

    public ArrayList<Projeto> getProjetos() {
        return this.projetos;
    }

    public ArrayList<Tarefa> getTarefas() {
        return this.tarefas;
    }

    // public void criaFicheiroSeNaoExistirTarefasAssociadas() {
    // if (!this.arquivoTarefasAssociadas.exists() &&
    // !this.arquivoTarefasAssociadas.isDirectory()) {
    // try {
    // FileWriter fileWriter = new FileWriter(this.arquivoTarefasAssociadas);
    // csv

    // } catch (Exception e) {
    // System.out.println("Binary file input error: " + e.getMessage());
    // e.printStackTrace();
    // }
    // } else {
    // try {
    // FileInputStream readData = new FileInputStream(this.arquivo);
    // ObjectInputStream readStream = new ObjectInputStream(readData);

    // // ler os arrays
    // this.arquivoTarefasAssociadas = (HashMap<Integer, Tarefa>)
    // readStream.readObject();

    // readStream.close();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // }

    // public void atualizaFicheiroTarefasAssociadas() {

    // try {
    // FileOutputStream outFileStream = new FileOutputStream(this.arquivo);
    // ObjectOutputStream outDataStream = new ObjectOutputStream(outFileStream);

    // outDataStream.writeObject(this.utilizadores);
    // outDataStream.writeObject(this.projetos);
    // outDataStream.writeObject(this.tarefas);

    // outDataStream.close();

    // } catch (Exception e) {
    // System.out.println("Erro: " + e.getMessage());
    // e.printStackTrace();
    // }

    // }
}
