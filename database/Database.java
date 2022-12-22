package database;

import java.io.*;
import java.util.*;
import models.*;

public class Database {
    ArrayList<Utilizador> utilizadores;

    // diretoria padrao para guardar o ficheiro
    String path;
    // FICHEIRO que GUARDA os UTILIZADORES no ESTADO ATIVO
    File arquivo;

    public Database() {
        this.path = System.getProperty("user.dir");
        this.utilizadores = new ArrayList<>();
        this.arquivo = new File(this.path, "utilizadores.dat");
    }

    public void criaFicheiroSeNaoExistir() {
        if (!this.arquivo.exists() && !this.arquivo.isDirectory()) {

            Admin admin = new Admin("admin");

            this.utilizadores.add(admin);

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
