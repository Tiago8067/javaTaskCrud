package menus;

import java.io.*;
import java.util.*;

public class Menu {
    public Menu() {

    }

    public void menuPrincipal() {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("\n\n### Gestao de tarefas de projeto ###");
            System.out.println("\n                  =========================");
            System.out.println("                  |     1 - Logar       |");
            System.out.println("                  |     2 - Registar        |");
            System.out.println("                  =========================\n");

            System.out.printf("Opção: ");
            opcao = scanner.nextInt();
            System.out.print("\n");
            switch (opcao) {
                default:
                    System.out.println("Programa Finalizado!");
                    break;
            }
        } while (opcao != 0);
    }

}
