import database.*;
import menus.*;

public class Main {
    public static void main(String[] args) {
        // SAI DEL SOL
        Database db = new Database();
        db.criaFicheiroSeNaoExistir();

        Menu menu = new Menu();
        menu.menuPrincipal(db.retornaDatabase());
    }
}