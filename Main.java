import database.*;
import menus.*;

public class Main {
    public static void main(String[] args) {
        // SAI DEL SOL
        Database database = new Database();
        database.criaFicheiroSeNaoExistir();

        Menu menu = new Menu(database);
        menu.menuPrincipal();
    }
}