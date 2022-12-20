package database;

import java.io.*;
import java.util.*;
import models.*;

public class Database {
    ArrayList<Utilizador> utilizadores = new ArrayList<>();

    String path = System.getProperty("user.dir");

    File arquivo = new File(path, "utilizadores.dat");

    public Database() {

    }

    public void criaFicheiroSeNaoExistir() {
        if (!arquivo.exists() && !arquivo.isDirectory()) {

            Admin admin = new Admin("admin");

            utilizadores.add(admin);

            try {

                FileOutputStream outFileStream = new FileOutputStream(arquivo);
                ObjectOutputStream outDataStream = new ObjectOutputStream(outFileStream);

                outDataStream.writeObject(utilizadores);

                outDataStream.close();

            } catch (Exception e) {
                System.out.println("Binary file input error: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream readData = new FileInputStream(arquivo);
                ObjectInputStream readStream = new ObjectInputStream(readData);
                utilizadores = (ArrayList<Utilizador>) readStream.readObject();
                readStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
