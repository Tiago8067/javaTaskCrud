import java.io.File;
import java.util.Scanner;

import models.*;
import database.*;
import menus.*;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        db.criaFicheiroSeNaoExistir();

        Menu menu = new Menu();
        menu.menuPrincipal(db.retornaDatabase());

    }
}