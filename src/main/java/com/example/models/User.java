package com.example.models;

import com.example.database.*;
import com.example.enums.EstadoPedido;
import com.example.enums.EstadoTarefa;
import com.example.exceptions.IdException;
import com.example.exceptions.NomeDuplicatedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    RegexDados regex;

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
        this.regex = new RegexDados(database);
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

    public User(int idUser, int idUtilizadorConvidado) {
        this.idUser = idUser;
        this.idUtilizadorConvidado = idUtilizadorConvidado;
        this.estadoPedido = EstadoPedido.EMESPERA;
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

    public int getIdProjeto() {
        return this.idProjeto;
    }

    public int getIdTarefa() {
        return this.idTarefa;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public void listarProjetos() {

        this.util.clearConsole();

        for (int i = 0; i < this.database.getProjetos().size(); i++) {
            System.out.println("Id: " + this.database.getProjetos().get(i).getIdProjeto() + "\t->" + "Nome do projeto: "
                    + this.database.getProjetos().get(i).getNomeProjeto());
        }
    }

    public void listarTarefas() {
        for (int i = 0; i < this.database.getTarefas().size(); i++) {
            System.out.println("Id: " + this.database.getTarefas().get(i).getIdTarefa() + "\t->"
                    + "Descricao da tarefa: " + this.database.getTarefas().get(i).getCurtaDescricao() + "\tData inico: "
                    + this.database.getTarefas().get(i).getDataInicioHora() + "\tData Termino: "
                    + this.database.getTarefas().get(i).getDataHoraTermino() + "\t-> Estado: "
                    + this.database.getTarefas().get(i).getEstadoTarefa());
        }
    }

    public void listarUtilizadorUserParaConvidar() {
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i) instanceof User) {
                System.out.println("Id: " + this.database.getUtilizadores().get(i).getId() + "\t->"
                        + "Username: " + this.database.getUtilizadores().get(i).getUsername());
            }
        }
    }

    public void listarUtilizadorConvidados() {
        for (int i = 0; i < this.database.getUtilizadoresConvidados().size(); i++) {
            System.out.println("Id do que convida: " + this.database.getUtilizadoresConvidados().get(i).getId() + "\t->"
                    + "Username: " + this.database.getUtilizadoresConvidados().get(i).getUsername()
                    + "\tEstado Pedido: "
                    + this.database.getUtilizadoresConvidados().get(i).getEstadoPedido());
        }
    }

    public void editaDadosDaSuaConta() {
    }

    // nome do projeto tem de ser unico
    public void criaProjeto() {
        String nomeProjeto; // , nomeCliente, precoPorHora;
        int idProjeto = 0;

        this.util.clearConsole();

        do {
            System.out.printf("Insira o nome do Projeto: ");
            nomeProjeto = this.scanner.nextLine();

            if (nomeProjeto.length() < 4) {
                System.out.println("O Nome do Projeto tem obrigatoriamente 4 carateres!");
            }
        } while (nomeProjeto.length() < 4);

        try {
            this.funcionalidadesService.verificarNomeProjeto(nomeProjeto.toLowerCase());
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
        String curtaDescricao, dataInicioHora; // dataHoraTermino;
        int idTarefa = 0;

        this.util.clearConsole();

        do {
            System.out.printf("Insira a descricao da tarefa: ");
            curtaDescricao = this.scanner.nextLine();

            if (curtaDescricao.length() < 4) {
                System.out.println("A Descricao da Tarefa tem obrigatoriamente 4 carateres!");
            }
        } while (curtaDescricao.length() < 4);

        try {
            this.funcionalidadesService.verificarDescricaoTarefa(curtaDescricao.toLowerCase());
        } catch (NomeDuplicatedException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.printf("Insira a data e hora de inicio da tarefa: ");
        dataInicioHora = this.scanner.nextLine();

        if (dataInicioHora.equals("")) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataInicioHora = formatter.format(date);
            System.out.println(dataInicioHora + " Registada");
        } else {
            if (this.regex.validateJavaDate(dataInicioHora)) {
                System.out.println(dataInicioHora + "Registada");
            } else {
                System.out.println(dataInicioHora
                        + " não é uma data e horas válidas, introduza uma com o seguinte formato: dd(31)/MM(12)/AAAA hh:mm:ss");
            }
        }

        for (int i = 0; i < this.database.getTarefas().size(); i++) {
            idTarefa++;
        }
        this.tarefa = new Tarefa(curtaDescricao.toLowerCase(), dataInicioHora, idTarefa);

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

    public void opcaoMenuEscolheUtilizadorParaConvidar() {
        int opcaoVerUtilizadorConvidados;

        while (true) {

            System.out.printf("\nPretende ver a sua lista de Utilizadores Convidados: ");

            System.out.println("\n1 - Sim");
            System.out.println("2 - Nao");

            System.out.printf("Escolha a Opcao: ");
            opcaoVerUtilizadorConvidados = scanner.nextInt();

            switch (opcaoVerUtilizadorConvidados) {
                case 1:
                    listarUtilizadorUserParaConvidar();
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
            this.funcionalidadesService.verificarIdProjeto(idProjetoAssociarTarefas);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        opcaoMenuEscolheTarefa();

        System.out.printf("Insere o Id da Tarefa que deseja agrupar:  ");
        idTarefaAssociadaNoProjeto = scanner.nextInt();

        try {
            this.funcionalidadesService.verificarIdTarefa(idTarefaAssociadaNoProjeto);
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

        System.out.printf("Insere o Id do Projeto que deseja remover:  ");
        idProjetoQueVaiSerRemovido = scanner.nextInt();

        try {
            this.projeto = this.funcionalidadesService.verificarIdProjeto(idProjetoQueVaiSerRemovido);
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

        System.out.printf("Insere o Id da Tarefa que deseja remover:  ");
        idTarefaQueVaiSerRemovido = scanner.nextInt();

        try {
            this.tarefa = this.funcionalidadesService.verificarIdTarefa(idTarefaQueVaiSerRemovido);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        this.funcionalidadesService.removeTarefa(this.tarefa);
    }

    // FIM, se data e hora de fim nao inseridas atribuir o data e hora ATUAL
    public void terminaTarefa() {
        int idTarefaAssociadaNoProjeto;
        String dataFimTarefa;

        opcaoMenuEscolheTarefa();

        System.out.printf("Insere o Id da Tarefa que deseja terrminar:  ");
        idTarefaAssociadaNoProjeto = scanner.nextInt();

        try {
            this.tarefa = this.funcionalidadesService.verificarIdTarefa(idTarefaAssociadaNoProjeto);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        System.out.printf("Insira a data e hora de Fim da tarefa: ");
        dataFimTarefa = this.scanner.nextLine();

        if (dataFimTarefa.equals("")) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataFimTarefa = formatter.format(date);
            System.out.println(dataFimTarefa + " Registada");
        } else {
            if (this.regex.validateJavaDate(dataFimTarefa)) {
                System.out.println(dataFimTarefa + "Registada");
            } else {
                System.out.println(dataFimTarefa
                        + " não é uma data e horas válidas, introduza uma com o seguinte formato: dd(31)/MM(12)/AAAA HH:mm:ss");
            }
        }

        this.tarefa.setEstadoTarefa(EstadoTarefa.FINALIZADO);
        this.tarefa.setDataHoraTermino(dataFimTarefa);
    }

    // pode listar tarefas no estado EM CURSO
    // tem de se obter o tempo total usado na tarefa realizada
    public void listarTarefasEmCurso() {
        String dataCalculaTempoTarefa;
        Date data1, data2;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.printf("Insira a data e hora de Fim da tarefa: ");
        dataCalculaTempoTarefa = this.scanner.nextLine();

        if (dataCalculaTempoTarefa.equals("")) {
            Date date = new Date();
            dataCalculaTempoTarefa = formatter.format(date);
            System.out.println(dataCalculaTempoTarefa + " Registada");
        } else {
            if (this.regex.validateJavaDate(dataCalculaTempoTarefa)) {
                System.out.println(dataCalculaTempoTarefa + "Registada");
            } else {
                System.out.println(dataCalculaTempoTarefa
                        + " não é uma data e horas válidas, introduza uma com o seguinte formato: dd(31)/MM(12)/AAAA HH:mm:ss");
            }
        }

        try {
            data2 = formatter.parse(dataCalculaTempoTarefa);

            for (int i = 0; i < this.database.getTarefas().size(); i++) {
                if (this.database.getTarefas().get(i).getEstadoTarefa().equals(EstadoTarefa.EMCURSO)) {

                    data1 = formatter.parse(this.database.getTarefas().get(i).getDataInicioHora());

                    long diff = data2.getTime() - data1.getTime();

                    long diffSeconds = diff / 1000 % 60;
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;
                    long diffDays = diff / (24 * 60 * 60 * 1000);

                    System.out.println("Id: " + this.database.getTarefas().get(i).getIdTarefa() + "\t->"
                            + "Descricao da tarefa: " + this.database.getTarefas().get(i).getCurtaDescricao()
                            + "\tData inico: "
                            + this.database.getTarefas().get(i).getDataInicioHora() + "\t-> Estado: "
                            + this.database.getTarefas().get(i).getEstadoTarefa()
                            + "\tTempo total utilizado ate ao momento na Tarefa e: " + diffDays + " dias " + diffHours
                            + " Horas " + diffMinutes + " Minutos " + diffSeconds + " Segundos");
                }
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
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

        opcaoMenuEscolheUtilizadorParaConvidar();

        System.out.printf("Insere o Id do Utilizador que deseja Convidar:  ");
        idUtilizadorConvidado = scanner.nextInt();

        try {
            this.utilizador = this.funcionalidadesService.verificarIdUtilizador(idUtilizadorConvidado);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        this.utilizador.setEstadoPedido(EstadoPedido.EMESPERA);

        this.utilizador = new User(idUtilizadorQueConvida, idUtilizadorConvidado);

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
            this.utilizador = this.funcionalidadesService
                    .verificarIdUtilizadorConvidado(idUtilizadorConvidadoQueVaiSerRemovido);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        this.funcionalidadesService.removeConvidadosDoProjeto(this.utilizador);
    }
}