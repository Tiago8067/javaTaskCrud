package com.example.database;

import java.io.*;
import java.util.*;
import com.example.models.*;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVWriter;
import com.example.enums.*;

import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

public class Database implements Serializable {
    ArrayList<Utilizador> utilizadores;
    ArrayList<Projeto> projetos;
    ArrayList<Tarefa> tarefas;
    Map<String, String> tarefasAssociadasMap;
    List<HashMap<Utilizador, HashMap<Projeto, Tarefa>>> tarefasAssociadas;

    // diretoria padrao para guardar o ficheiro
    String path;
    File arquivo, arquivoTarefasAssociadas;

    public Database() {
        this.path = System.getProperty("user.dir");
        this.utilizadores = new ArrayList<>();
        this.projetos = new ArrayList<>();
        this.tarefas = new ArrayList<>();
        this.tarefasAssociadasMap = new HashMap<>();
        // this.tarefasAssociadas = new ArrayList<HashMap<idUser , HashMap<idPorjeto,
        // idTraefa>>>();
        this.arquivo = new File(this.path, "utilizadores.dat");
        this.arquivoTarefasAssociadas = new File(this.path, "TarefasAssociadas.csv");
    }

    public void criaFicheiroSeNaoExistir() {
        if (!this.arquivo.exists() && !this.arquivo.isDirectory()) {

            Admin admin = new Admin("admin", 0);

            admin.setEstadoUtilizador(EstadoUtilizador.ATIVO);

            this.utilizadores.add(admin);

            try {
                FileOutputStream outFileStream = new FileOutputStream(this.arquivo);
                ObjectOutputStream outDataStream = new ObjectOutputStream(outFileStream);

                outDataStream.writeObject(this.utilizadores);
                outDataStream.writeObject(this.projetos);
                outDataStream.writeObject(this.tarefas);

                outDataStream.close();

            } catch (Exception e) {
                System.out.println("Binary file input error: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream readData = new FileInputStream(this.arquivo);
                ObjectInputStream readStream = new ObjectInputStream(readData);

                // ler os arrays
                this.utilizadores = (ArrayList<Utilizador>) readStream.readObject();
                this.projetos = (ArrayList<Projeto>) readStream.readObject();
                this.tarefas = (ArrayList<Tarefa>) readStream.readObject();

                readStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void atualizaFicheiro() {

        try {
            FileOutputStream outFileStream = new FileOutputStream(this.arquivo);
            ObjectOutputStream outDataStream = new ObjectOutputStream(outFileStream);

            outDataStream.writeObject(this.utilizadores);
            outDataStream.writeObject(this.projetos);
            outDataStream.writeObject(this.tarefas);

            outDataStream.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public ArrayList<Utilizador> getUtilizadores() {
        return this.utilizadores;
    }

    public ArrayList<Projeto> getProjetos() {
        return this.projetos;
    }

    public ArrayList<Tarefa> getTarefas() {
        return this.tarefas;
    }

    // public void insereDadosTesteNoMap() throws IOException {
    // this.tarefasAssociadasMap.put("1", "sai");
    // this.tarefasAssociadasMap.put("2", "sai");
    // this.tarefasAssociadasMap.put("3", "sai");
    // this.tarefasAssociadas.add((HashMap<String, String>)
    // this.tarefasAssociadasMap);

    // this.tarefasAssociadasMap.put("4", "sol");
    // this.tarefasAssociadasMap.put("5", "sol");
    // this.tarefasAssociadasMap.put("6", "sol");
    // this.tarefasAssociadas.add((HashMap<String, String>)
    // this.tarefasAssociadasMap);

    // this.tarefasAssociadasMap.put("7", "buzz");
    // this.tarefasAssociadasMap.put("8", "buzz");
    // this.tarefasAssociadasMap.put("9", "buzz");
    // this.tarefasAssociadas.add((HashMap<String, String>)
    // this.tarefasAssociadasMap);

    // this.tarefasAssociadasMap.put("10", "tiga");
    // this.tarefasAssociadasMap.put("12", "tiga");
    // this.tarefasAssociadasMap.put("13", "tiga");
    // this.tarefasAssociadas.add((HashMap<String, String>)
    // this.tarefasAssociadasMap);

    // this.tarefasAssociadasMap.put("10", "buzz");
    // this.tarefasAssociadas.add((HashMap<String, String>)
    // this.tarefasAssociadasMap);

    // Writer writer = new FileWriter(this.arquivoTarefasAssociadas, true);
    // Reader reader = new FileReader(this.arquivoTarefasAssociadas);

    // csvWriter(this.tarefasAssociadas, writer);

    // csvReader(reader);
    // }

    // public void csvWriter(List<HashMap<String, String>> listOfMap, Writer writer)
    // throws IOException {
    // CsvSchema schema = null;
    // CsvSchema.Builder schemaBuilder = CsvSchema.builder();

    // if (listOfMap != null && !listOfMap.isEmpty()) {
    // for (String col : listOfMap.get(0).keySet()) {
    // schemaBuilder.addColumn(col);
    // }
    // schema = schemaBuilder.build().withLineSeparator("\r").withHeader();
    // }

    // CsvMapper mapper = new CsvMapper();
    // mapper.writer(schema).writeValues(writer).writeAll(listOfMap);
    // writer.flush();
    // }

    // public void csvWriter(Collection collection, Writer writer) throws
    // IOException {
    // if (collection != null && collection.size() > 0) {
    // CsvMapper mapper = new CsvMapper();
    // Object[] objects = collection.toArray();
    // Class type = objects[0].getClass();
    // CsvSchema schema = mapper.schemaFor(type).withHeader();
    // mapper.writer(schema).writeValues(writer).write(objects);
    // } else {
    // writer.write("No Data");
    // }
    // writer.flush();
    // }

    // public void csvReader(Reader reader) throws IOException {
    // Iterator<Map<String, String>> iterator = new CsvMapper()
    // .readerFor(Map.class)
    // .with(CsvSchema.emptySchema().withHeader())
    // .readValues(reader);

    // while (iterator.hasNext()) {
    // Map<String, String> keyVals = iterator.next();
    // System.out.println(keyVals);
    // }
    // }

    public void setUtilizadores(ArrayList<Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    public void setProjetos(ArrayList<Projeto> projetos) {
        this.projetos = projetos;
    }

    public void setTarefas(ArrayList<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public Map<String, String> getTarefasAssociadasMap() {
        return this.tarefasAssociadasMap;
    }

    public void setTarefasAssociadasMap(Map<String, String> tarefasAssociadasMap) {
        this.tarefasAssociadasMap = tarefasAssociadasMap;
    }

    // public List<HashMap<String, String>> getTarefasAssociadas() {
    // return this.tarefasAssociadas;
    // }

    // public void setTarefasAssociadas(List<HashMap<String, String>>
    // tarefasAssociadas) {
    // this.tarefasAssociadas = tarefasAssociadas;
    // }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getArquivo() {
        return this.arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public File getArquivoTarefasAssociadas() {
        return this.arquivoTarefasAssociadas;
    }

    public void setArquivoTarefasAssociadas(File arquivoTarefasAssociadas) {
        this.arquivoTarefasAssociadas = arquivoTarefasAssociadas;
    }
}
