package com.example.database;

import java.io.*;
import java.util.*;
import com.example.models.*;
import com.example.enums.*;

public class Database implements Serializable {
    ArrayList<Utilizador> utilizadores;

    String path;
    File arquivo, arquivoTarefasAssociadas;

    public Database() {
        this.path = System.getProperty("user.dir");
        this.utilizadores = new ArrayList<>();
        this.arquivo = new File(this.path, "utilizadores.dat");
    }

    public void criaFicheiroSeNaoExistir() {
        if (!this.arquivo.exists() && !this.arquivo.isDirectory()) {

            Admin admin = new Admin("admin");
            admin.setEstadoUtilizador(EstadoUtilizador.ATIVO);

            User user = new User("user");
            user.setEstadoUtilizador(EstadoUtilizador.ATIVO);

            User gabriel = new User("gabriel");
            gabriel.setEstadoUtilizador(EstadoUtilizador.ATIVO);

            user.provisoria();
            user.provisoria();

            this.utilizadores.add(admin);
            this.utilizadores.add(user);
            this.utilizadores.add(gabriel);

            try {
                FileOutputStream outFileStream = new FileOutputStream(this.arquivo);
                ObjectOutputStream outDataStream = new ObjectOutputStream(outFileStream);

                outDataStream.writeObject(this.utilizadores);

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

            outDataStream.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public ArrayList<Utilizador> getUtilizadores() {
        return this.utilizadores;
    }

}