import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Repositorio rep1 = new Repositorio();
        File ficheiro = new File("C:\\Users\\tiago\\IdeaProjects\\tp_prog2V2\\tp_prog2V2\\src\\ficheiros\\utilizadores.csv");

        rep1.deserializar(ficheiro);

        if (rep1.getUtilizadorMap().isEmpty()){
            Utilizador utilizadorAdmin = new Admin();
            utilizadorAdmin.setUsername("admin");
            utilizadorAdmin.setPassword("admin");
            rep1.serializar(ficheiro);
        }

        menuPrincipal();
    }

    public static void menuPrincipal(){
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("\n\n### Sistema de rastreio de tempo em tarefas ###");
            System.out.println("\n                  =========================");
            System.out.println("                  |     1 - Admin         |");
            System.out.println("                  |     2 - UserManger    |");
            System.out.println("                  |     3 - User          |");
            System.out.println("                  |     4 - Produto       |");
            System.out.println("                  |     0 - Sair          |");
            System.out.println("                  =========================\n");

            System.out.printf("Opção: ");
            opcao = scanner.nextInt();
            System.out.print("\n");
            switch (opcao) {
                case 1:
                    //menuCliente(utilizadores);
                    break;
                case 2:
                    //menuGestor(utilizadores);
                    break;
                case 3:
                    //menuFuncionario(utilizadores);
                    break;
                case 4:
                    //menuMecanico(utilizadores);
                    break;
                default:
                    System.out.println("Programa Finalizado!");
                    break;
            }
        } while (opcao != 0);
    }

    public static void menuAdmin(){

        Scanner scanner = new Scanner(System.in);
        Admin admin;
        int opcao = 0;

        do {
            System.out.println("\n\n### Menu Admin ###");
            System.out.println("\n                  =========================");
            System.out.println("                  |     1 - Login       |");
            System.out.println("                  |     2 - Registar    |");
            System.out.println("                  |     0 - Voltar      |");
            System.out.println("                  =========================\n");

            System.out.printf("Opção: ");
            opcao = scanner.nextInt();

            System.out.print("\n");
            switch (opcao) {
                case 1:
                    /*int posicao;
                    c= new Cliente();
                    posicao=c.login(utilizadores);
                    if (posicao!=-1) {
                        menuClienteDois(utilizadores,posicao);
                    }*/
                    break;
                case 2:
                    /*c = new Cliente();
                    c.registar(utilizadores);
                    atualizaFIcheiro(utilizadores);
                    break;*/
                default:
                    break;
            }
        } while (opcao != 0);

    }
}