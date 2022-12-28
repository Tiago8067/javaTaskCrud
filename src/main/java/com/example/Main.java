
package com.example;

import com.example.database.Database;
import com.example.menus.*;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        database.criaFicheiroSeNaoExistir();
        // database.criaFicheiroSeNaoExistirProjeto();
        // database.atualizaFicheiroTarefa();

        Menu menu = new Menu(database);
        menu.menuPrincipal();
    }
}