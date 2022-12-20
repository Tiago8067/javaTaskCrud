package menus;

import java.util.*;
import models.*;
import controllers.*;

public class Menu {

    Utilizador utilizador;

    public Menu() {

    }

    public void menuPrincipal(ArrayList<Utilizador> utilizadores) {

        AutenticacaoController autenticacaoController = new AutenticacaoController(utilizadores);

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n\n### Gestao de tarefas de projeto ###");
            System.out.println("\n");
            System.out.println("1 - Logar");
            System.out.println("2 - Registar");
            System.out.println("0 - Sair");
            System.out.println("\n");

            System.out.printf("Opção: ");
            opcao = scanner.nextInt();
            System.out.print("\n");
            switch (opcao) {
                case 1:
                    this.utilizador = autenticacaoController.login();
                    if (this.utilizador == null) {
                        System.out.println("nao existe esse usuario \n");
                        break;
                    }
                    System.out.println("logado\n");
                    break;
                default:
                    System.out.println("Programa Finalizado!");
                    break;
            }
        } while (opcao != 0);
    }

}
