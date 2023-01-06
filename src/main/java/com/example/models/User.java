package com.example.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.example.database.Database;
import com.example.enums.EstadoPedido;
import com.example.enums.EstadoTarefa;
import com.example.utils.RegexDados;

public class User extends Utilizador {
    private ArrayList<Projeto> projetos = new ArrayList<Projeto>();
    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    private ArrayList<Utilizador> usersConvidados = new ArrayList<Utilizador>();

    private Database database; // arraylist de utilizadores usado para
                               // o
    // case 11

    // passamos a database
    public User() { // Database database
        super();
        // this.database = database;
    }

    public User(String username) {
        super(username);
    }

    public ArrayList<Projeto> getProjetos() {
        return this.projetos;
    }

    public void editaDadosDaSuaConta() {
    }

    public void provisoria() {
        Projeto projeto = new Projeto("projeto", getUsername()); // , getUsername()
        this.projetos.add(projeto);
        // Tarefa tarefa = new Tarefa("tarefa");
        // this.tarefas.add(tarefa);
    }

    public void criaProjeto() {
        Scanner scanner = new Scanner(System.in);
        String nomeProjeto;
        Projeto projeto;

        System.out.printf("Insira o nome do Projeto: ");
        nomeProjeto = scanner.nextLine();

        if (nomeProjeto.length() < 4) {
            System.out.println("O Nome do Projeto tem obrigatoriamente 4 carateres!" + "\nInvalido!!! Vai sair.");
            return;
        }

        if (verificarNomeProjeto(nomeProjeto.toLowerCase())) {
            System.out.println("Invalido!!!");
            return;
        }

        // System.out.printf("Insira o nome do Cliente: ");
        // nomeCliente = this.scanner.next();

        // System.out.printf("Insira o preco por hora: ");
        // precoPorHora = this.scanner.next();

        projeto = new Projeto(nomeProjeto.toLowerCase(), getUsername()); // , getUsername()
        this.projetos.add(projeto);
    }

    public void listarProjetos() {
        for (int index = 0; index < this.projetos.size(); index++) {
            // if (this.projetos.isEmpty()) {
            // System.out.println("Ainb");
            // }

            System.out.println(this.projetos.get(index).toString());
        }
    }

    public void criarTarefa() {
        Scanner scanner = new Scanner(System.in);
        String curtaDescricao, dataInicioHora;
        Tarefa tarefa;
        RegexDados regex = new RegexDados();

        System.out.printf("Insira a descricao: ");
        curtaDescricao = scanner.nextLine();

        if (curtaDescricao.length() < 4) {
            System.out.println("A Descricao tem obrigatoriamente 4 carateres!");
            return;
        }

        if (verificarDescricaoTarefa(curtaDescricao.toLowerCase())) {
            System.out.println("Invalido!!!");
            return;
        }

        System.out.printf("Insira a data e hora de inicio da tarefa: ");
        dataInicioHora = scanner.nextLine();

        if (dataInicioHora.equals("")) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataInicioHora = formatter.format(date);
            System.out.println(dataInicioHora + " Registada");
        } else {
            if (regex.validateJavaDate(dataInicioHora)) {
                System.out.println(dataInicioHora + "Registada");
            } else {
                System.out.println(dataInicioHora
                        + " não é uma data e horas válidas, introduza uma com o seguinte formato: dd(31)/MM(12)/AAAA hh:mm:ss");
            }
        }

        tarefa = new Tarefa(curtaDescricao.toLowerCase(), dataInicioHora);
        this.tarefas.add(tarefa);

    }

    public void listarTarefas() {
        for (int index = 0; index < this.tarefas.size(); index++) {
            System.out.println(this.tarefas.get(index).toString());
        }
    }

    public void agruparTarefaProjeto() {
        Scanner scanner = new Scanner(System.in);

        opcaoMenuEscolheTarefa();

        System.out.printf("Nome da tarefa: ");
        String curtaDescricao = scanner.nextLine();

        opcaoMenuEscolheProjeto();

        System.out.printf("Nome do projeto: ");
        String nomeProjeto = scanner.nextLine();

        verificarDescricaoTarefa(curtaDescricao.toLowerCase());
        verificarNomeProjeto(nomeProjeto.toLowerCase());

        if (!verificarDescricaoTarefa(curtaDescricao.toLowerCase())
                || !verificarNomeProjeto(nomeProjeto.toLowerCase())) {
            System.out.println("Invalido!!!");
            return;
        }

        if (getSpecificTarefa(curtaDescricao).getProjeto() != null) {
            System.out.println("Tarefa ja em uso!!!");
            return;
        }

        linkaTarefaProjeto(nomeProjeto, getSpecificTarefa(curtaDescricao));
        getSpecificTarefa(curtaDescricao).setProjeto(nomeProjeto);

    }

    public Tarefa getSpecificTarefa(String nomeTarefa) {
        for (int index = 0; index < this.tarefas.size(); index++) {
            if (this.tarefas.get(index).getCurtaDescricao().equals(nomeTarefa)) {
                return this.tarefas.get(index);
            }
        }
        return null;
    }

    public Projeto getSpecificProjeto(String nomeProjeto) {
        for (int index = 0; index < this.projetos.size(); index++) {
            if (this.projetos.get(index).getNomeProjeto().equals(nomeProjeto)) {
                return this.projetos.get(index);
            }
        }
        return null;
    }

    public void linkaTarefaProjeto(String nomeProjeto, Tarefa tarefa) {
        for (int index = 0; index < this.projetos.size(); index++) {
            if (this.projetos.get(index).getNomeProjeto().equals(nomeProjeto)) {
                this.projetos.get(index).addTarefa(tarefa);
            }
        }
    }

    public void editaDadosProjeto() {
    }

    public void removeProjeto() {
        Scanner scanner = new Scanner(System.in);
        String nomeProjeto;

        opcaoMenuEscolheProjeto();

        System.out.printf("Insere o nome do Projeto que deseja remover:  ");
        nomeProjeto = scanner.nextLine();

        if (!verificarNomeProjeto(nomeProjeto)) {
            System.out.println("Invalido");
            return;
        }

        this.projetos.remove(getSpecificProjeto(nomeProjeto));
    }

    public void terminaTarefa() {
        Scanner scanner = new Scanner(System.in);
        RegexDados regex = new RegexDados();
        String dataFimTarefa, descricao;
        Date dataFimInseridaDate, dataInicioDate;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        opcaoMenuEscolheTarefa();

        System.out.printf("Insere a Descricao da Tarefa que deseja terminar:  ");
        descricao = scanner.nextLine();

        if (!verificarDescricaoTarefa(descricao)) {
            System.out.println("Invalido");
            return;
        }

        System.out.printf("Insira a data e hora de Fim da tarefa: ");
        dataFimTarefa = scanner.nextLine();

        if (dataFimTarefa.equals("")) {
            Date date = new Date();
            dataFimTarefa = formatter.format(date);
            System.out.println(dataFimTarefa + " Registada");
        } else {
            if (regex.validateJavaDate(dataFimTarefa)) {
                System.out.println(dataFimTarefa + "Registada");
            } else {
                System.out.println(dataFimTarefa
                        + " não é uma data e horas válidas, introduza uma com o seguinte formato: dd(31)/MM(12)/AAAA HH:mm:ss");
            }
        }

        try {
            dataFimInseridaDate = formatter.parse(dataFimTarefa);

            for (int i = 0; i < this.tarefas.size(); i++) {
                if (this.tarefas.get(i).getEstadoTarefa().equals(EstadoTarefa.FINALIZADO)) {

                    dataInicioDate = formatter.parse(this.tarefas.get(i).getDataInicioHora());

                    if (dataInicioDate.compareTo(dataFimInseridaDate) > 0) {
                        System.out.println(
                                "A data de termino da tarefa nunca pode ser menor que a data de inicio.\nInvalido!!!");
                        return;
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        getSpecificTarefa(descricao).setEstadoTarefa(EstadoTarefa.FINALIZADO);
        getSpecificTarefa(descricao).setDataHoraTermino(dataFimTarefa);
    }

    public void removeTarefa() {
        Scanner scanner = new Scanner(System.in);
        String descricao;

        opcaoMenuEscolheProjeto();

        System.out.printf("Insere a descricao da tarefa que deseja remover:  ");
        descricao = scanner.nextLine();

        if (!verificarNomeProjeto(descricao)) {
            System.out.println("Invalido");
            return;
        }

        this.tarefas.remove(getSpecificTarefa(descricao));
    }

    public void listarTarefasEmCurso() {
        Scanner scanner = new Scanner(System.in);
        RegexDados regex = new RegexDados();
        String dataCalculaTempoTarefa;
        Date data1, data2;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.printf("Insira a data e hora de Fim da tarefa: ");
        dataCalculaTempoTarefa = scanner.nextLine();

        if (dataCalculaTempoTarefa.equals("")) {
            Date date = new Date();
            dataCalculaTempoTarefa = formatter.format(date);
            System.out.println(dataCalculaTempoTarefa + " Registada");
        } else {
            if (regex.validateJavaDate(dataCalculaTempoTarefa)) {
                System.out.println(dataCalculaTempoTarefa + "Registada");
            } else {
                System.out.println(dataCalculaTempoTarefa
                        + " não é uma data e horas válidas, introduza uma com o seguinte formato: dd(31)/MM(12)/AAAA HH:mm:ss");
            }
        }

        try {
            data2 = formatter.parse(dataCalculaTempoTarefa);

            for (int i = 0; i < this.tarefas.size(); i++) {
                if (this.tarefas.get(i).getEstadoTarefa().equals(EstadoTarefa.EMCURSO)) {

                    data1 = formatter.parse(this.tarefas.get(i).getDataInicioHora());

                    long diff = data2.getTime() - data1.getTime();

                    long diffSeconds = diff / 1000 % 60;
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;
                    long diffDays = diff / (24 * 60 * 60 * 1000);

                    System.out.println("\t->"
                            + "Descricao da tarefa: " + this.tarefas.get(i).getCurtaDescricao()
                            + "\tData inico: "
                            + this.tarefas.get(i).getDataInicioHora() + "\t-> Estado: "
                            + this.tarefas.get(i).getEstadoTarefa()
                            + "\tTempo total utilizado ate ao momento na Tarefa e: " + diffDays + " dias " + diffHours
                            + " Horas " + diffMinutes + " Minutos " + diffSeconds + " Segundos");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void listarTarefasFinalizadas() {
        Scanner scanner = new Scanner(System.in);
        RegexDados regex = new RegexDados();
        String data1, data2;
        Date data1Format, data2Format, dataTermino;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.printf("Insira a primeira data: ");
        data1 = scanner.nextLine();

        if (regex.validateJavaDate(data1)) {
            System.out.println(data1 + "\nRegistada");
        } else {
            System.out.println(data1
                    + " não é uma data e horas válidas, introduza uma com o seguinte formato: dd(31)/MM(12)/AAAA HH:mm:ss");
        }

        System.out.printf("Insira a segunda data: ");
        data2 = scanner.nextLine();

        if (regex.validateJavaDate(data2)) {
            System.out.println(data2 + "\nRegistada");
        } else {
            System.out.println(data2
                    + " não é uma data e horas válidas, introduza uma com o seguinte formato: dd(31)/MM(12)/AAAA HH:mm:ss");
        }

        try {
            data1Format = formatter.parse(data1);
            data2Format = formatter.parse(data2);

            if (data1Format.compareTo(data2Format) > 0) {
                System.out.println("A primeira data inserida tem de ser menor que a segunda data.\nInvalido!!!");
                return;
            }

            for (int i = 0; i < this.tarefas.size(); i++) {
                if (this.tarefas.get(i).getEstadoTarefa().equals(EstadoTarefa.FINALIZADO)) {

                    dataTermino = formatter.parse(this.tarefas.get(i).getDataHoraTermino());

                    if (dataTermino.after(data1Format) && dataTermino.before(data2Format)) {
                        System.out.println("\t->"
                                + "Descricao da tarefa: " + this.tarefas.get(i).getCurtaDescricao()
                                + "\tData inico: "
                                + this.tarefas.get(i).getDataInicioHora() + "\t-> Estado: "
                                + this.tarefas.get(i).getEstadoTarefa());
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean verificarNomeProjeto(String nomeProjeto) {
        for (int i = 0; i < this.projetos.size(); i++) {
            if (this.projetos.get(i).getNomeProjeto().equals(nomeProjeto)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarDescricaoTarefa(String curtaDescricao) {
        for (int i = 0; i < this.tarefas.size(); i++) {
            if (this.tarefas.get(i).getCurtaDescricao().equals(curtaDescricao)) {
                return true;
            }
        }
        return false;
    }

    // verifica nome do user convidado
    public boolean verificarNomeUtilizadorConvidado(String nome, Database database) {
        this.database = database;
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i).getUsername().equals(nome)) {
                this.database.getUtilizadores().get(i).setEstadoPedido(EstadoPedido.EMESPERA);
                return true;
            }
        }
        return false;
    }

    public void opcaoMenuEscolheProjeto() {
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
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

    // convidar utilizador CASE 11
    public void convidaUtilizadorParaParticiparNumProjeto() { // Database database
        // this.database = database;
        Scanner scanner = new Scanner(System.in);

        opcaoMenuEscolheUtilizador();

        System.out.printf("Nome do utilizador que deseja convidar: ");
        String nomeUtilizador = scanner.nextLine();

        opcaoMenuEscolheProjeto();

        System.out.printf("Nome projeto que deseja partilhar com o utilizador: ");
        String nomeProjeto = scanner.nextLine();

        verificarNomeUtilizadorConvidado(nomeUtilizador.toLowerCase(), database);
        verificarNomeProjeto(nomeProjeto.toLowerCase());

        // FAZER VERIFICACAO PARA ADICIONAR APENAS USERS QUE NAO SEJAM O USER LOGADO
        // ESTA A ADICIONAR O UTILIZADOR LOGADO NAO PODE!!!!
        if (!verificarNomeUtilizadorConvidado(nomeUtilizador.toLowerCase(), database)) {
            System.out.println("Nome de utilizador Invalido!!!");
            return;
        }

        if (!verificarNomeProjeto(nomeProjeto.toLowerCase())) {
            // !verificarNomeUtilizadorConvidado(nomeUtilizador.toLowerCase())
            System.out.println("Nome Projeto Invalido!!!");
            return;

        }

        linkaUsernameProjeto(nomeProjeto, getSpecificUtilizador(nomeUtilizador, database));
        getSpecificUtilizador(nomeUtilizador, database).setProjeto(nomeProjeto);
    }

    // Menu que nos mostra a lista de utilizadores
    public void opcaoMenuEscolheUtilizador() {
        Scanner scanner = new Scanner(System.in);
        int opcaoVerUtilizadores;
        // AutenticacaoService autenticacaoService = new AutenticacaoService(database);

        while (true) {
            System.out.printf("\nPretende ver a sua lista de Utilizadores?");

            System.out.println("\n1 - Sim");
            System.out.println("2 - Nao");

            System.out.printf("Escolha a Opcao: ");
            opcaoVerUtilizadores = scanner.nextInt();

            switch (opcaoVerUtilizadores) {
                case 1:
                    // listarUtilizadores();
                    // autenticacaoService.listarUtilizadores();
                    listarUtilizadorUserParaConvidar(database);
                    return;
                case 2:
                    return;
                default:
                    System.out.println("Opcao Invalida!!!\nEscolha a opcao correta.");
                    break;
            }
        }
    }

    // VAI SE PUDER CONVIDAR SÓ USERS OU TODOS OS UTILIZADORES?
    public void listarUtilizadorUserParaConvidar(Database database) {
        this.database = database;
        // System.out.println("teste1");
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            // System.out.println("teste2");
            System.out.println(this.database.getUtilizadores().get(i).toString());
        }
    }

    public void listarUtilizadorUserConvidados() {
        for (int i = 0; i < this.usersConvidados.size(); i++) {
            System.out.println(this.usersConvidados.get(i).toString());
        }
    }

    // Retorna o arraylist dos utilizadores
    public Utilizador getSpecificUtilizador(String nomeUtilizador, Database database) {
        this.database = database;
        for (int index = 0; index < this.database.getUtilizadores().size(); index++) {
            if (this.database.getUtilizadores().get(index).getUsername().equals(nomeUtilizador)) {
                return this.database.getUtilizadores().get(index);
            }
        }
        return null;
    }

    // associa o nome do projeto ao user
    public void linkaUsernameProjeto(String nomeProjeto, Utilizador user) {
        for (int index = 0; index < this.projetos.size(); index++) {
            if (this.projetos.get(index).getNomeProjeto().equals(nomeProjeto)) {
                this.projetos.get(index).addUserConvidado(user);
            }
        }
    }
}