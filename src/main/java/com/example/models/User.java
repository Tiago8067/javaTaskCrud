package com.example.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.example.database.Database;
import com.example.enums.EstadoPedido;
import com.example.enums.EstadoTarefa;
import com.example.utils.RegexDados;
import com.example.utils.Util;

public class User extends Utilizador {
    private ArrayList<Projeto> projetos = new ArrayList<Projeto>();
    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    private Database database;

    public User() {
        super();
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
        // Projeto projeto = new Projeto("projeto", getUsername()); // , getUsername()
        // this.projetos.add(projeto);
        // Tarefa tarefa = new Tarefa("tarefa");
        // this.tarefas.add(tarefa);
    }

    public void criaProjeto() {
        Scanner scanner = new Scanner(System.in);
        Util util = new Util(database);
        String nomeProjeto, nomeCliente;
        float precoPorHora;
        Projeto projeto;

        util.clearBuffer(scanner);

        System.out.printf("Insira o nome do Projeto: ");
        nomeProjeto = scanner.nextLine();

        if (nomeProjeto.length() < 4) {
            System.out
                    .println("O Nome do Projeto tem obrigatoriamente de ter 4 carateres!" + "\nInvalido!!! Vai sair.");
            return;
        }

        if (verificarNomeProjeto(nomeProjeto.toLowerCase())) {
            System.out.println("Invalido!!! Nome projeto ja existe");
            return;
        }

        System.out.printf("Insira o nome do Cliente: ");
        nomeCliente = scanner.nextLine();

        System.out.printf("Insira o preco por hora: ");
        precoPorHora = scanner.nextFloat();

        projeto = new Projeto(nomeProjeto.toLowerCase(), nomeCliente, precoPorHora, getUsername());
        this.projetos.add(projeto);
    }

    public void listarProjetos() {
        Util util = new Util(database);
        if (this.projetos.isEmpty() == true) {
            System.out.println("Nao tem Projetos!!!\nVai ser reencaminhado para puder criar projetos");
            util.waitForCont();
            util.clearConsole();
            criaProjeto();
        }

        for (int index = 0; index < this.projetos.size(); index++) {
            System.out.println("\n" + this.projetos.get(index).toString());
        }
    }

    public void criarTarefa() {
        Scanner scanner = new Scanner(System.in);
        Util util = new Util(database);
        String curtaDescricao, dataInicioHora;
        Tarefa tarefa;
        RegexDados regex = new RegexDados();

        util.clearBuffer(scanner);

        System.out.printf("Insira a descricao: ");
        curtaDescricao = scanner.nextLine();

        if (curtaDescricao.length() < 4) {
            System.out.println("A Descricao tem obrigatoriamente de ter 4 carateres!+ \nInvalido!!! Vai sair.");
            return;
        }

        if (verificarDescricaoTarefa(curtaDescricao.toLowerCase())) {
            System.out.println("Invalido!!! Descricao da tarefa ja existe");
            return;
        }

        System.out
                .printf("Insira a data e hora de inicio da tarefa, no seguinte formato: dd(31)/MM(12)/AAAA hh:mm:ss: ");
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
                        + " não é uma data e horas válidas, a sua tarefa nao e introduzida");
                return;
            }
        }

        tarefa = new Tarefa(curtaDescricao.toLowerCase(), dataInicioHora);
        this.tarefas.add(tarefa);

    }

    public void listarTarefas() {
        Util util = new Util(database);
        if (this.tarefas.isEmpty() == true) {
            System.out.println("Nao tem Tarefas!!!\nVai ser reencaminhado para puder criar tarefas");
            util.waitForCont();
            util.clearConsole();
            criarTarefa();
        }

        for (int index = 0; index < this.tarefas.size(); index++) {
            System.out.println("\n" + this.tarefas.get(index).toString());
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

        verificarDescricaoTarefa(curtaDescricao);
        verificarNomeProjeto(nomeProjeto);

        if (!verificarDescricaoTarefa(curtaDescricao)
                || !verificarNomeProjeto(nomeProjeto)) {
            System.out.println("Descricao da tarefa ou nome do projeto estao Invalidos!!!");
            return;
        }

        linkaTarefaProjeto(nomeProjeto, getSpecificTarefa(curtaDescricao));
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
        Scanner scanner = new Scanner(System.in);
        Util util = new Util(database);
        String nomeProjeto, novoNomeProjeto, novoNomeCliente;
        float novoPrecoPorHora;
        int opcao;

        util.clearBuffer(scanner);

        opcaoMenuEscolheProjeto();

        System.out.printf("Insere o nome do Projeto que deseja alterar/editar:  ");
        nomeProjeto = scanner.nextLine();

        if (!verificarNomeProjeto(nomeProjeto)) {
            System.out.println("Invalido");
            return;
        }

        System.out.println("Alterar/editar informacoes dos projetos");
        System.out.println("1 - Nome do Projeto");
        System.out.println("2 - Nome do Cliente");
        System.out.println("3 - Preco por Hora");
        System.out.println("0 - Sair");

        System.out.println("Opcao: ");
        opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                util.clearBuffer(scanner);
                System.out.printf("Insira o novo nome do Projeto: ");
                novoNomeProjeto = scanner.nextLine();

                if (novoNomeProjeto.length() < 4) {
                    System.out
                            .println("O Nome do Projeto tem obrigatoriamente de ter 4 carateres!"
                                    + "\nInvalido!!! Vai sair.");
                    return;
                }

                if (verificarNomeProjeto(novoNomeProjeto.toLowerCase())) {
                    System.out.println("Invalido!!! Nome projeto ja existe");
                    return;
                }
                getSpecificProjeto(nomeProjeto).setNomeProjeto(novoNomeProjeto);
                break;
            case 2:
                util.clearBuffer(scanner);
                System.out.printf("Insira o nome do Cliente: ");
                novoNomeCliente = scanner.nextLine();
                getSpecificProjeto(nomeProjeto).setNomeCliente(novoNomeCliente);
                break;
            case 3:
                util.clearBuffer(scanner);
                System.out.printf("Insira o preco por hora: ");
                novoPrecoPorHora = scanner.nextFloat();
                getSpecificProjeto(nomeProjeto).setPrecoPorHora(novoPrecoPorHora);
                break;
            case 0:
                return;
            default:
                System.out.println("Opcao Invalida!!!");
                return;
        }
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

        System.out.printf("Insira a data e hora de Fim da tarefa, no seguinte formato: dd(31)/MM(12)/AAAA HH:mm:ss: ");
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
                        + " não é uma data e horas válidas, a sua tarefa nao foi terminada");
                return;
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

        opcaoMenuEscolheTarefa();

        System.out.printf("Insere a descricao da tarefa que deseja remover:  ");
        descricao = scanner.nextLine();

        if (!verificarDescricaoTarefa(descricao)) {
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
        int contador = 0;

        for (int i = 0; i < this.tarefas.size(); i++) {
            // if (!this.tarefas.get(i).getEstadoTarefa().equals(EstadoTarefa.FINALIZADO))
            if (this.tarefas.get(i).getEstadoTarefa().equals(EstadoTarefa.FINALIZADO)) {
                contador++;
            }
        }

        if (this.tarefas.size() == contador) {
            System.out.println("Nao existem tarefas em curso!!!");
            return;
        }

        System.out.printf(
                "Insira a data e hora, para calcular o tempo despendido ate ao momento em todas as tarefas em curso,\n no seguinte formato: dd(31)/MM(12)/AAAA HH:mm:ss: ");
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
                        + " não é uma data e horas válidas, impossivel de listar o tempo despendido ate ao momento das tarefas en curso");
                return;
            }
        }

        try {
            data2 = formatter.parse(dataCalculaTempoTarefa);

            for (int i = 0; i < this.tarefas.size(); i++) {
                if (this.tarefas.get(i).getEstadoTarefa().equals(EstadoTarefa.EMCURSO)) {

                    data1 = formatter.parse(this.tarefas.get(i).getDataInicioHora());

                    if (data1.compareTo(data2) > 0) {
                        System.out.println(
                                "A data inserida tem de ser maior que a data de inicio da tarefa.\nInvalido!!!");
                        return;
                    }

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
        int contador = 0;

        for (int i = 0; i < this.tarefas.size(); i++) {
            // if (!this.tarefas.get(i).getEstadoTarefa().equals(EstadoTarefa.FINALIZADO))
            if (this.tarefas.get(i).getEstadoTarefa().equals(EstadoTarefa.EMCURSO)) {
                contador++;
            }
        }

        if (this.tarefas.size() == contador) {
            System.out.println("Nao existem tarefas finalizadas!!!");
            return;
        }

        System.out.printf("Insira a primeira data, no seguinte formato: dd(31)/MM(12)/AAAA HH:mm:ss: ");
        data1 = scanner.nextLine();

        if (regex.validateJavaDate(data1)) {
            System.out.println(data1 + "\nRegistada");
        } else {
            System.out.println(data1
                    + " não é uma data e horas válidas");
            return;
        }

        System.out.printf("Insira a segunda data, no seguinte formato: dd(31)/MM(12)/AAAA HH:mm:ss: ");
        data2 = scanner.nextLine();

        if (regex.validateJavaDate(data2)) {
            System.out.println(data2 + "\nRegistada");
        } else {
            System.out.println(data2
                    + " não é uma data e horas válidas");
            return;
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

                    if (dataTermino.before(data1Format) || dataTermino.after(data2Format)) {
                        System.out.println("Nao existem tarefas terminadas entre essas datas!!!");
                        return;
                    }

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

    public boolean verificarNomeUtilizador(String nome, Database database) {
        this.database = database;
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i).getUsername().equals(nome)) {
                // this.database.getUtilizadores().get(i).setEstadoPedido(EstadoPedido.ACEITE);
                return true;
            }
        }
        return false;
    }

    public boolean verificarNomeUtilizadorConvidado(String nome, Database database) {
        this.database = database;
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i).getUsername().equals(nome)) {
                // this.database.getUtilizadores().get(i).setEstadoPedido(EstadoPedido.EMESPERA);
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
                    Util util = new Util(database);
                    if (this.projetos.isEmpty() == true) {
                        System.out.println("Nao tem Projetos!!!\nVai ser reencaminhado para puder criar projetos");
                        util.waitForCont();
                        util.clearConsole();
                        criaProjeto();
                    }
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
                    Util util = new Util(database);
                    if (this.tarefas.isEmpty() == true) {
                        System.out.println("Nao tem Tarefas!!!\nVai ser reencaminhado para puder criar tarefas");
                        util.waitForCont();
                        util.clearConsole();
                        criarTarefa();
                    }
                    return;
                default:
                    System.out.println("Opcao Invalida!!!\nEscolha a opcao correta.");
                    break;
            }
        }
    }

    public void convidaUtilizadorParaParticiparNumProjeto(Database database) {
        Scanner scanner = new Scanner(System.in);
        this.database = database;

        opcaoMenuEscolheUtilizador(database);

        System.out.printf("Nome do utilizador que deseja convidar: ");
        String nomeUtilizador = scanner.nextLine();

        if (nomeUtilizador.equals(getUsername())) {
            System.out.println("Nao se pode autoconvidar!!!Invalido!!!");
            return;
        }

        opcaoMenuEscolheProjeto();

        System.out.printf("\nNome projeto que deseja partilhar com o utilizador: ");
        String nomeProjeto = scanner.nextLine();

        verificarNomeUtilizadorConvidado(nomeUtilizador, database);
        verificarNomeProjeto(nomeProjeto);

        if (!verificarNomeUtilizadorConvidado(nomeUtilizador, database) || !verificarNomeProjeto(nomeProjeto)) {
            System.out.println("Nome Utilizador ou Nome Projeto Invalido!!!");
            return;
        }

        linkaUsernameProjeto(nomeProjeto, getSpecificUtilizador(nomeUtilizador, database));
        getSpecificUtilizador(nomeUtilizador, database).getProjetosPartilhados().put(nomeProjeto,
                EstadoPedido.EMESPERA);
    }

    public void listarUtilizadorUserParaConvidar(Database database) {
        this.database = database;
        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i) instanceof User) {
                System.out.println(this.database.getUtilizadores().get(i).toString());
            }
        }
    }

    public void opcaoMenuEscolheUtilizador(Database database) {
        Scanner scanner = new Scanner(System.in);
        this.database = database;
        int opcaoVerUtilizadores;

        while (true) {
            System.out.printf("\nPretende ver a sua lista de Utilizadores?");

            System.out.println("\n1 - Sim");
            System.out.println("2 - Nao");

            System.out.printf("Escolha a Opcao: ");
            opcaoVerUtilizadores = scanner.nextInt();

            switch (opcaoVerUtilizadores) {
                case 1:
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

    public Utilizador getSpecificUtilizador(String nomeUtilizador, Database database) {
        this.database = database;
        for (int index = 0; index < this.database.getUtilizadores().size(); index++) {
            if (this.database.getUtilizadores().get(index).getUsername().equals(nomeUtilizador)) {
                return this.database.getUtilizadores().get(index);
            }
        }
        return null;
    }

    public void linkaUsernameProjeto(String nomeProjeto, Utilizador user) {
        for (int index = 0; index < this.projetos.size(); index++) {
            if (this.projetos.get(index).getNomeProjeto().equals(nomeProjeto)) {
                this.projetos.get(index).addUserConvidado(user);
            }
        }
    }

    public boolean verificarNomeProjetoConvidado(String nomeProjeto) {
        for (int i = 0; i < getProjetosPartilhados().size(); i++) {
            if (getProjetosPartilhados().keySet().contains(nomeProjeto)) {
                return true;
            }
        }
        return false;
    }

    public void aceitaConvite(Database database) {
        Scanner scanner = new Scanner(System.in);
        this.database = database;
        String nomeUser, nomeProjeto;

        System.out.printf("Insere o seu username, para aceitar os seus pedidos: ");
        nomeUser = scanner.nextLine();

        verificarNomeUtilizador(nomeUser, database);

        if (!verificarNomeUtilizador(nomeUser, database)) {
            System.out.println("Invalido!!!\n Nao existe este username inserido");
            return;
        }

        if (!nomeUser.equals(getUsername())) {
            System.out.println("Invalido!!!\n Nao e o seu username");
            return;
        }

        if (getProjetosPartilhados().isEmpty() == true) {
            System.out.println("Nao tem projetos para aceitar!!!");
            return;
        }

        for (int i = 0; i < this.database.getUtilizadores().size(); i++) {
            if (this.database.getUtilizadores().get(i).getProjetosPartilhados().values()
                    .contains(EstadoPedido.EMESPERA)
                    && this.database.getUtilizadores().get(i).getUsername().equals(nomeUser)) {
                System.out.println(this.database.getUtilizadores().get(i).toString());
                break;
            }
            if (this.database.getUtilizadores().get(i).getProjetosPartilhados().values()
                    .contains(EstadoPedido.ACEITE)) {
                System.out.println("Os projetos ja foram aceites!!!");
                return;
            }
        }

        System.out.printf("Insere o nome do projeto que pretende aceitar:  ");
        nomeProjeto = scanner.nextLine();

        if (!verificarNomeProjetoConvidado(nomeProjeto)) {
            System.out.println("Nome do projeto inserido e invalido!!!");
            return;
        }

        if (getSpecificUtilizador(getUsername(), database).getProjetosPartilhados().get(nomeProjeto)
                .equals(EstadoPedido.ACEITE)) {
            System.out.println("Este projeto ja foi aceite!!!");
            return;
        }

        getSpecificUtilizador(getUsername(), database).getProjetosPartilhados().replace(nomeProjeto,
                EstadoPedido.ACEITE);

        System.out.println("O pedido foi aceite");
    }

    public Utilizador getSpecificUtilizadorConvidado(String nomeUtilizador) {
        for (int index = 0; index < this.projetos.size(); index++) {
            if (this.projetos.get(index).getUsersConvidados().get(index).getUsername().equals(nomeUtilizador)) {
                return this.projetos.get(index).getUsersConvidados().get(index);
            }
        }
        return null;
    }

    public String getSpecificProjetosPartilhados(String nomeUtilizador) {
        for (int index = 0; index < this.projetos.size(); index++) {
            if (this.projetos.get(index).getUsersConvidados().get(index).getUsername().equals(nomeUtilizador)) {
                return this.projetos.get(index).getNomeProjeto();
            }
        }
        return null;
    }

    public void removeConvidadosDoProjeto(Database database) {
        Scanner scanner = new Scanner(System.in);
        this.database = database;
        String nomeUserConvidadoParaRemover, nomeProjeto;

        opcaoMenuEscolheProjeto();

        System.out.printf("Insere o nome do utilizador que pretende remover:  ");
        nomeUserConvidadoParaRemover = scanner.nextLine();

        if (!verificarNomeUtilizadorConvidado(nomeUserConvidadoParaRemover, database)) {
            System.out.println("Nome do utilizador inserido e invalido!!!");
            return;
        }

        System.out.printf("Insere o nome do projeto que pretende remover:  ");
        nomeProjeto = scanner.nextLine();

        // Nao consigo remover no mapa
        // EstadoPedido valormapa = getProjetosPartilhados().get(nomeProjeto);
        // getProjetosPartilhados().remove(nomeProjeto, valormapa);

        for (int i = 0; i < getSpecificUtilizador(nomeUserConvidadoParaRemover, database).getProjetosPartilhados()
                .size(); i++) {
            if (getSpecificUtilizador(nomeUserConvidadoParaRemover, database).getProjetosPartilhados().get(nomeProjeto)
                    .equals(EstadoPedido.EMESPERA)) {
                getSpecificUtilizador(nomeUserConvidadoParaRemover, database).getProjetosPartilhados()
                        .remove(nomeProjeto);
            }
        }

        for (int index = 0; index < this.projetos.size(); index++) {
            if (this.projetos.get(index).getUsersConvidados().get(index).getUsername()
                    .equals(nomeUserConvidadoParaRemover)) {
                this.projetos.get(index).getUsersConvidados()
                        .remove(getSpecificUtilizadorConvidado(nomeUserConvidadoParaRemover));
            }
            // this.usersConvidados.get(index).getProjetosPartilhados().remove(nomeProjeto);
        }
    }

    public boolean validarMes(int dataMes) {
        if (dataMes > 0 && dataMes < 13) {
            return true;
        }
        return false;
    }

    public void buscarMes() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataTermino = null;
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM", new Locale("pt", "PT"));

        try {
            for (int i = 0; i < this.tarefas.size(); i++) {
                dataTermino = formatter.parse(this.tarefas.get(i).getDataHoraTermino());
                System.out.println(dateFormat2.format(dataTermino));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void escolheMes() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat formatterMes = new SimpleDateFormat("MM");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMMM", new Locale("pt", "PT"));
        Date opcaoData;

        // int opcao = 0;
        String opcao;
        System.out.println("1-Janeiro");
        System.out.println("2-Fevereiro");
        System.out.println("3-Março");
        System.out.println("4-Abril");
        System.out.println("5-Maio");
        System.out.println("6-Junho");
        System.out.println("7-Julho");
        System.out.println("8-Agosto");
        System.out.println("9-Setembro");
        System.out.println("10-Outubro");
        System.out.println("11-Novembro");
        System.out.println("12-Dezembro");

        System.out.println("Insira o mes que pretende: ");
        opcao = scanner.next(); // nextInt();

        switch (opcao) {
            case "1":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "2":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "3":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "4":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "5":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "6":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "7":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "8":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "9":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "10":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "11":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "12":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;

            default:
                break;
        }
    }

    public void relatorioTarefasFinalizadasMensal() {
        Scanner scanner = new Scanner(System.in);
        Util util = new Util(database);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat formatterMes = new SimpleDateFormat("MM");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMMM", new Locale("pt", "PT"));
        Date dataTermino = null;
        Date opcaoData = null;
        String opcao;

        util.clearBuffer(scanner);

        System.out.println("1-Janeiro");
        System.out.println("2-Fevereiro");
        System.out.println("3-Março");
        System.out.println("4-Abril");
        System.out.println("5-Maio");
        System.out.println("6-Junho");
        System.out.println("7-Julho");
        System.out.println("8-Agosto");
        System.out.println("9-Setembro");
        System.out.println("10-Outubro");
        System.out.println("11-Novembro");
        System.out.println("12-Dezembro");

        System.out.println("Insira o mes que pretende: ");
        opcao = scanner.next(); // nextInt();

        switch (opcao) {
            case "1":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "2":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "3":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "4":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "5":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "6":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "7":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "8":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "9":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "10":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "11":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case "12":
                try {
                    opcaoData = formatterMes.parse(opcao);
                    System.out.println(dateFormat2.format(opcaoData));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;

            default:
                break;
        }
        // System.out.printf("\nInsira o mes: ");
        // dataMes = scanner.nextInt();

        // validarMes(dataMes);

        // if (!validarMes(dataMes)) {
        // System.out.println("Mes invalido!\n Insira um mes entre 1 e 12!!!");
        // return;
        // }

        // dataMesString = String.valueOf(dataMes);
        // dataMesString = Integer.toString(dataMes);

        try {
            for (int i = 0; i < this.tarefas.size(); i++) {
                if (this.tarefas.get(i).getEstadoTarefa().equals(EstadoTarefa.FINALIZADO)) {
                    util.clearBuffer(scanner);
                    dataTermino = formatter.parse(this.tarefas.get(i).getDataHoraTermino());
                    // dataMesdate = formatter.parse(dataMesString);

                    System.out.println(dateFormat2.format(dataTermino));
                    System.out.println("***************************************");
                    System.out.println(dateFormat2.format(opcaoData)); // TA A LISTAR MAL - SEMPRE COMO JANEIRO

                    if (dateFormat2.format(opcaoData).equals(dateFormat2.format(dataTermino))) {
                        System.out.println("dataMesdate = " + opcaoData);
                        System.out.println("dataTermino = " + dataTermino);

                        System.out.println("\t->"
                                + "Descricao da tarefa: " + this.tarefas.get(i).getCurtaDescricao()
                                + "\tData termino: "
                                + this.tarefas.get(i).getDataHoraTermino() + "\t-> Estado: "
                                + this.tarefas.get(i).getEstadoTarefa());
                    } else {
                        System.out.println("Nao tem tarefas finalizadas no mes inserido!!!");
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // public void listarProjetosMensal() {
    // Scanner scanner = new Scanner(System.in);
    // SimpleDateFormat formatter = new SimpleDateFormat("MM");
    // int dataMes;
    // Date dataTermino;

    // for (int i = 0; i < this.tarefas.size(); i++) {
    // if (this.tarefas.get(i).getEstadoTarefa().equals(EstadoTarefa.EMCURSO)) {
    // break;
    // }
    // if (!this.tarefas.get(i).getEstadoTarefa().equals(EstadoTarefa.FINALIZADO)) {
    // System.out.println("Nao existem tarefas finalizadas!!!");
    // return;
    // }
    // }

    // System.out.printf("\nInsira o mes: ");
    // dataMes = scanner.nextInt();

    // validarMes(dataMes);

    // if (!validarMes(dataMes)) {
    // System.out.println("Mes invalido!\n Insira um mes entre 1 e 12!!!");
    // return;
    // }
    // try {
    // for (int i = 0; i < this.projetos.size(); i++) {
    // dataTermino = formatter.parse(this.tarefas.get(i).getDataHoraTermino());
    // if (dataTermino.equals(dataMes)) {
    // System.out.println("teste5");

    // System.out.println("dataMes = " + dataMes);
    // System.out.println("dataTermino = " + dataTermino);

    // System.out.println("\n" + this.projetos.get(i).toString());
    // } else {
    // System.out.println("Data invalida!!!");
    // }
    // }
    // } catch (ParseException e) {
    // e.printStackTrace();
    // }
    // }

}