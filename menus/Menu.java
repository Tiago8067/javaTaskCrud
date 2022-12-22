package menus;

import java.util.*;
import models.*;
import controllers.*;
import utils.*;
import database.*;

public class Menu {
    AutenticacaoController autenticacaoController;
    AdminController adminController;
    Utilizador utilizador;
    Scanner scanner;
    CheckRole checkRole;
    Database database;
    int opcao = 0;

    // CONSTRUTOR
    public Menu(Database database) {
        this.scanner = new Scanner(System.in);
        this.database = database;
        this.checkRole = new CheckRole();
        this.autenticacaoController = new AutenticacaoController(this.database);
        this.adminController = new AdminController(this.database);
    }

    public void menuPrincipal() {

        while (true) {
            System.out.println("\n\n### Menu Principal ###");
            System.out.println("\n");
            System.out.println("1 - Login");
            System.out.println("2 - Registar");
            System.out.println("3 - Listar");
            System.out.println("0 - Sair");
            System.out.println("\n");

            System.out.printf("Opção: ");
            this.opcao = this.scanner.nextInt();

            System.out.print("\n");
            switch (opcao) {
                case 1:
                    this.utilizador = autenticacaoController.login();
                    if (this.utilizador == null) {
                        System.out.println("Nao existe este utilizador!\n");
                        break;
                    }

                    if (checkRole.checkPermissao(this.utilizador).equals("admin")) {
                        menuAdmin(this.database.getUtilizadores());
                    }
                    break;
                case 2:
                    this.autenticacaoController.registar();
                    this.database.atualizaFicheiro();
                    break;
                case 3:
                    this.adminController.listarUtilizadores();
                    break;
                case 0:
                    return;
            }
        }
    }

    public void menuAdmin(ArrayList<Utilizador> utilizadores) {
        while (true) {
            System.out.println("\n\n### Menu do Admin ###");
            System.out.println("\n");
            System.out.println("1 - Alterar estado dos utilizadores");
            System.out.println("2 - Visualizar todos os registos disponiveis");
            System.out.println("0 - Sair");
            System.out.println("\n");

            System.out.printf("Opção: ");

            this.opcao = this.scanner.nextInt();

            System.out.print("\n");

            switch (opcao) {
                case 1:
                    this.adminController.alterarEstadoDosUtilizadores();
                    break;
                case 0:
                    return;
            }
        }
    }

    public void menuUserManager() {
        do {
            System.out.println("\n\n### Menu do UserManager ###");
            System.out.println("\n");
            System.out.println(
                    "1 - Criar/editar utilizaores do tipo User, ou seja, atribuir o nivel de utilizador aos registos de cada um dos Users que se encotram inativos");
            System.out.println("2 - Visualizar todos os registos disponiveis dos Users");
            System.out.println("0 - Sair");
            System.out.println("\n");

            System.out.printf("Opção: ");
            opcao = scanner.nextInt();
            System.out.print("\n");
            switch (opcao) {
                case 1:
                    break;
                default:
                    System.out.println("Programa Finalizado!");
                    break;
            }
        } while (opcao != 0);
        // scanner.close();
        // TESTAR PENSAMOS QUE ERA O SCANNER CLOSE(AUTENTICACAOCONTROLLER.JAVA)
        // TESTAR AQUI TBM
    }
}