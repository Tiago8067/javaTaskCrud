package com.example;

import java.io.IOException;
import com.example.database.Database;
import com.example.menus.*;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();

        // try {
        // database.insereDadosTesteNoMap();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        database.criaFicheiroSeNaoExistir();
        // database.criaFicheiroSeNaoExistirProjeto();
        // database.atualizaFicheiroTarefa();

        Menu menu = new Menu(database);
        menu.menuPrincipal();
    }
}