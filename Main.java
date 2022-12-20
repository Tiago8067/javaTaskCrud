import java.io.File;
import java.util.Scanner;

import models.*;
import database.*;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        db.criaFicheiroSeNaoExistir();

        System.out.println("mudei porra = " + System.getProperty("user.dir"));

    }
}