package com.example.models;

import com.example.database.*;
import com.example.enums.EstadoPedido;
import com.example.enums.EstadoTarefa;
import com.example.exceptions.IdException;
import com.example.exceptions.NomeDuplicatedException;

import java.io.IOException;
import java.util.*;
import com.example.utils.*;
import com.example.services.*;

public class User extends Utilizador {
    Database database;
    AutenticacaoService autenticacaoService;
    Util util;
    Scanner scanner;
    Utilizador utilizador;
    String username;
    Projeto projeto;
    Tarefa tarefa;
    private int idUser;
    private int idProjeto;
    private int idTarefa;
    FuncionalidadesService funcionalidadesService;
    private int idUtilizadorConvidado;
    EstadoPedido estadoPedido;

    public User() {
        super();
    }

    public User(String username) {
        super(username);
    }

    public User(String username, int idUser) {
        super(username, idUser);
    }

    public User(String username, Database database, int idUser) {// , String email, String password, String nome, String
                                                                 // genero,
        // String morada
        super(username, idUser);
        this.database = database;
        this.util = new Util(database);
        this.autenticacaoService = new AutenticacaoService(database);
        this.scanner = new Scanner(System.in);
        this.funcionalidadesService = new FuncionalidadesService(database);
    }

    // Contrutor para associar tarefas a um projeto pretendido
    public User(String username, Database database, int idUser, int idProjeto, int idTarefa) {
        super(username, idUser);
        this.database = database;
        this.util = new Util(database);
        this.autenticacaoService = new AutenticacaoService(database);
        this.scanner = new Scanner(System.in);
        this.idProjeto = idProjeto;
        this.idTarefa = idTarefa;
        this.funcionalidadesService = new FuncionalidadesService(database);
    }

    public User(int idUser, int idUtilizadorConvidado, EstadoPedido estadoPedido) {
        this.idUser = idUser;
        this.idUtilizadorConvidado = idUtilizadorConvidado;
        this.estadoPedido = estadoPedido;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUtilizadorConvidado() {
        return this.idUtilizadorConvidado;
    }

    public void setIdUtilizadorConvidado(int idUtilizadorConvidado) {
        this.idUtilizadorConvidado = idUtilizadorConvidado;
    }

    public void listarProjetos() {

        clearConsole();

        for (int i = 0; i < this.database.getProjetos().size(); i++) {
            System.out.println("Id: " + this.database.getProjetos().get(i).getIdProjeto() + "\t->" + "Nome do projeto: "
                    + this.database.getProjetos().get(i).getNomeProjeto());
        }
    }

    public void listarTarefas() {
        for (int i = 0; i < this.database.getTarefas().size(); i++) {
            System.out.println("Id: " + this.database.getTarefas().get(i).getIdTarefa() + "\t->"
                    + "Descricao da tarefa: " + this.database.getTarefas().get(i).getCurtaDescricao() + "-> Estado: "
                    + this.database.getTarefas().get(i).getEstadoTarefa());
        }
    }

    public void listarUtilizadorConvidados() {
        for (int i = 0; i < this.database.getUtilizadoresConvidados().size(); i++) {
            System.out.println("Id: " + this.database.getUtilizadoresConvidados().get(i).getId() + "\t->"
                    + "Username: " + this.database.getUtilizadoresConvidados().get(i).getUsername());
        }
    }

    public void editaDadosDaSuaConta() {
    }

    // nome do projeto tem de ser unico
    public void criaProjeto() {
        String nomeProjeto; // , nomeCliente, precoPorHora;
        int idProjeto = 0;

        clearConsole();

        do {
            System.out.printf("Insira o nome do Projeto: ");
            nomeProjeto = this.scanner.nextLine();

            if (nomeProjeto.length() < 4) {
                System.out.println("O Nome do Projeto tem obrigatoriamente 4 carateres!");
            }
        } while (nomeProjeto.length() < 4);

        try {
            this.util.verificarNomeProjeto(nomeProjeto.toLowerCase());
        } catch (NomeDuplicatedException e) {
            System.out.println(e.getMessage());
            return;
        }

        // System.out.printf("Insira o nome do Cliente: ");
        // nomeCliente = this.scanner.next();

        // System.out.printf("Insira o preco por hora: ");
        // precoPorHora = this.scanner.next();

        for (int i = 0; i < this.database.getProjetos().size(); i++) {
            idProjeto++;
        }
        this.projeto = new Projeto(nomeProjeto.toLowerCase(), idProjeto); // , nomeCliente, precoPorHora, idProjeto

        this.autenticacaoService.adicionaProjeto(this.projeto); // NOMEPROJETO - VERIFCAR QUANDO SE
                                                                // ADICIONA USA O
                                                                // MESMO NOME PARA DAR MENSAGEM DE ERRO
    }

    // quando cria inicia uma tarefa, indicando uma curta descricao e data e hora de
    // inicio, se data e hora nao inseridas atribuir o data e hora ATUAL
    public void criaTarefa() {
        String curtaDescricao; // , dataInicioHora, dataHoraTermino;
        int idTarefa = 0;

        do {
            System.out.printf("Insira a descricao da tarefa: ");
            curtaDescricao = this.scanner.nextLine();

            if (curtaDescricao.length() < 4) {
                System.out.println("A Descricao da Tarefa tem obrigatoriamente 4 carateres!");
            }
        } while (curtaDescricao.length() < 4);

        try {
            this.util.verificarDescricaoTarefa(curtaDescricao.toLowerCase());
        } catch (NomeDuplicatedException e) {
            System.out.println(e.getMessage());
            return;
        }

        // System.out.printf("Insira a data e hora de inicio da tarefa: ");
        // dataInicioHora = this.scanner.next();

        // System.out.printf("Insira a data e hora de termino da tarefa: ");
        // dataHoraTermino = this.scanner.next();

        for (int i = 0; i < this.database.getTarefas().size(); i++) {
            idTarefa++;
        }
        this.tarefa = new Tarefa(curtaDescricao.toLowerCase(), idTarefa); // dataInicioHora, dataHoraTermino

        this.autenticacaoService.adicionaTarefa(this.tarefa);
    }

    public void opcaoMenuEscolheProjeto() {
        int opcaoVerProjetos;

        while (true) {

            System.out.printf("\nPretende ver a sua lista de Projetos: ");

            System.out.println("\n1 - Sim");
            System.out.println("2 - Nao");

            System.out.printf("Escolha a Opcao: ");
            opcaoVerProjetos = scanner.nextInt();

            switch (opcaoVerProjetos) {
                case 1:
                    listarProjetos();
                    return;
                case 2:
                    return;
                default:
                    System.out.println("Opcao Invalida!!!\nEscolha a opcao correta.");
                    break;
            }
        }
    }

    public void opcaoMenuEscolheTarefa() {
        int opcaoVerTarefas;

        while (true) {

            System.out.printf("\nPretende ver a sua lista de Tarefas: ");

            System.out.println("\n1 - Sim");
            System.out.println("2 - Nao");

            System.out.printf("Escolha a Opcao: ");
            opcaoVerTarefas = scanner.nextInt();

            switch (opcaoVerTarefas) {
                case 1:
                    listarTarefas();
                    return;
                case 2:
                    return;
                default:
                    System.out.println("Opcao Invalida!!!\nEscolha a opcao correta.");
                    break;
            }
        }
    }

    public void opcaoMenuEscolheUtilizadorConvidado() {
        int opcaoVerUtilizadorConvidados;

        while (true) {

            System.out.printf("\nPretende ver a sua lista de Utilizadores Convidados: ");

            System.out.println("\n1 - Sim");
            System.out.println("2 - Nao");

            System.out.printf("Escolha a Opcao: ");
            opcaoVerUtilizadorConvidados = scanner.nextInt();

            switch (opcaoVerUtilizadorConvidados) {
                case 1:
                    listarUtilizadorConvidados();
                    return;
                case 2:
                    return;
                default:
                    System.out.println("Opcao Invalida!!!\nEscolha a opcao correta.");
                    break;
            }
        }
    }

    // associar tarefas
    public void agrupaTarefaParaProjeto() {
        int idUtilizadorAssociador, idProjetoAssociarTarefas, idTarefaAssociadaNoProjeto;

        System.out.printf("Verifique o seu Username, para puder agrupar Tarefas a Projetos: ");
        this.username = scanner.next();

        this.utilizador = this.autenticacaoService.login(this.username);

        if (this.utilizador == null) {
            System.out.println("Nao existe este utilizador!\n");
            return;
        }

        idUtilizadorAssociador = this.utilizador.getId();

        System.out.println("O id do user que vai realizar o agrupamento e: " + idUtilizadorAssociador);

        opcaoMenuEscolheProjeto();

        System.out.printf("Insere o Id do Projeto que deseja agrupar:  ");
        idProjetoAssociarTarefas = scanner.nextInt();

        try {
            this.util.verificarIdProjeto(idProjetoAssociarTarefas);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        opcaoMenuEscolheTarefa();

        System.out.printf("Insere o Id da Tarefa que deseja agrupar:  ");
        idTarefaAssociadaNoProjeto = scanner.nextInt();

        try {
            this.util.verificarIdTarefa(idTarefaAssociadaNoProjeto);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        // String username, Database database, int idUser, int idProjeto, int idTarefa
        this.utilizador = new User(this.username, this.database, idUtilizadorAssociador, idProjetoAssociarTarefas,
                idTarefaAssociadaNoProjeto);
        System.out.println(this.utilizador);
        this.database.getUtilizadores().add(this.utilizador);

        this.projeto = new Projeto(idProjetoAssociarTarefas, idTarefaAssociadaNoProjeto);
        this.database.getProjetos().add(this.projeto);
        System.out.println(this.projeto);

        this.tarefa = new Tarefa(idTarefaAssociadaNoProjeto, idProjetoAssociarTarefas);
        this.database.getTarefas().add(this.tarefa);
        System.out.println(this.tarefa);
    }

    public void editaDadosProjeto() {
    }

    public void removeProjeto() {
        int idProjetoQueVaiSerRemovido;

        opcaoMenuEscolheProjeto();

        System.out.printf("Insere o Id do Projeto que deseja agrupar:  ");
        idProjetoQueVaiSerRemovido = scanner.nextInt();

        try {
            this.projeto = this.util.verificarIdProjeto(idProjetoQueVaiSerRemovido);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        this.funcionalidadesService.removeProjeto(this.projeto);
    }

    // um utilizador tanto pode remover tarefas no estado EM CURSO, como no estado
    // FINALIZADO
    public void removeTarefa() {
        int idTarefaQueVaiSerRemovido;

        opcaoMenuEscolheTarefa();

        System.out.printf("Insere o Id da Tarefa que deseja agrupar:  ");
        idTarefaQueVaiSerRemovido = scanner.nextInt();

        try {
            this.tarefa = this.util.verificarIdTarefa(idTarefaQueVaiSerRemovido);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        this.funcionalidadesService.removeTarefa(this.tarefa);
    }

    // FIM, se data e hora de fim nao inseridas atribuir o data e hora ATUAL
    public void terminaTarefa() {
        int idTarefaAssociadaNoProjeto;
        // Date dataFimTarefa;

        opcaoMenuEscolheTarefa();

        System.out.printf("Insere o Id da Tarefa que deseja agrupar:  ");
        idTarefaAssociadaNoProjeto = scanner.nextInt();

        try {
            this.tarefa = this.util.verificarIdTarefa(idTarefaAssociadaNoProjeto);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        this.tarefa.setEstadoTarefa(EstadoTarefa.FINALIZADO);
    }

    // pode listar tarefas no estado EM CURSO
    // tem de se obter o tempo total usado na tarefa realizada
    public void listarTarefasEmCurso() {
        for (int i = 0; i < this.database.getTarefas().size(); i++) {
            if (this.database.getTarefas().get(i).getEstadoTarefa().equals(EstadoTarefa.EMCURSO)) {
                System.out.println("Id: " + this.database.getTarefas().get(i).getIdTarefa() + "\t->"
                        + "Descricao da tarefa: " + this.database.getTarefas().get(i).getCurtaDescricao()
                        + "-> Estado: "
                        + this.database.getTarefas().get(i).getEstadoTarefa());
            }
        }
    }

    // pode listar tarefas no estado FIANLIZADO
    // ENTRE DUAS Datas
    public void listarTarefasFinalizadas() {
        for (int i = 0; i < this.database.getTarefas().size(); i++) {
            if (this.database.getTarefas().get(i).getEstadoTarefa().equals(EstadoTarefa.FINALIZADO)) {
                System.out.println("Id: " + this.database.getTarefas().get(i).getIdTarefa() + "\t->"
                        + "Descricao da tarefa: " + this.database.getTarefas().get(i).getCurtaDescricao()
                        + "-> Estado: "
                        + this.database.getTarefas().get(i).getEstadoTarefa());
            }
        }
    }

    public void convidaUtilizadorParaParticiparNumProjeto() {
        int idUtilizadorQueConvida, idUtilizadorConvidado;

        System.out.printf("Verifique o seu Username, para puder agrupar Tarefas a Projetos: ");
        this.username = scanner.next();

        this.utilizador = this.autenticacaoService.login(this.username);

        if (this.utilizador == null) {
            System.out.println("Nao existe este utilizador!\n");
            return;
        }

        idUtilizadorQueConvida = this.utilizador.getId();

        System.out.println("O id do user que vai realizar o CONVITE e: " + idUtilizadorQueConvida);

        opcaoMenuEscolheUtilizadorConvidado();

        System.out.printf("Insere o Id do Utilizador que deseja Convidar:  ");
        idUtilizadorConvidado = scanner.nextInt();

        try {
            this.utilizador = this.util.verificarIdUtilizador(idUtilizadorConvidado);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        this.utilizador = new User(idUtilizadorQueConvida, idUtilizadorConvidado,
                EstadoPedido.EMESPERA);

        this.funcionalidadesService.registarUtilizadorConvidado(this.utilizador);
    }

    public void aceitaConvite() {
        int opcaoAceita;

        System.out.printf("Verifique o seu Username, para puder verificar se tem Convites: ");
        this.username = scanner.next();

        this.utilizador = this.autenticacaoService.login(this.username);

        if (this.utilizador == null) {
            System.out.println("Nao existe este utilizador!\n");
            return;
        }

        if (this.utilizador.getEstadoPedido() == null) {
            System.out.println("Nao tem pedidos, para puder Aceitar.");
            return;
        }

        while (true) {
            System.out.printf("\nPretende aceitar o convite?");

            System.out.println("\n1 - Sim");
            System.out.println("2 - Nao");

            System.out.printf("Escolha a Opcao: ");
            opcaoAceita = scanner.nextInt();

            switch (opcaoAceita) {
                case 1:
                    this.utilizador.setEstadoPedido(EstadoPedido.ACEITE);
                    return;
                case 2:
                    return;
                default:
                    System.out.println("Opcao Invalida!!!\nEscolha a opcao correta.");
                    break;
            }
        }
    }

    public void removeConvidadosDoProjeto() {
        int idUtilizadorConvidadoQueVaiSerRemovido;

        opcaoMenuEscolheUtilizadorConvidado();

        System.out.printf("Insere o Id do Utilizador Convidado que deseja Remover:  ");
        idUtilizadorConvidadoQueVaiSerRemovido = scanner.nextInt();

        try {
            this.utilizador = this.util.verificarIdUtilizador(idUtilizadorConvidadoQueVaiSerRemovido);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        this.funcionalidadesService.removeConvidadosDoProjeto(this.utilizador);
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J"); // Serve para limpar o ecra;
        // System.out.flush();
    }
}