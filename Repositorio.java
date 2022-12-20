import java.io.*;
import java.util.Map;

public class Repositorio {
    private static Repositorio repo = null;
    private Map<Integer, Utilizador> utilizadorMap;

    public Repositorio() {}

    public Map<Integer, Utilizador> getUtilizadorMap() {
        return utilizadorMap;
    }

    public void serializar(File filename){
        try{
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this); //chama a lista
            out.close();
            fileOut.close();
            System.out.printf("Dados Serializados salvos em " + filename + "\n");
        } catch(IOException ex){
            System.out.println("Erro Serializar: " + ex.getMessage());
        }
    }

    public static void deserializar(File filename){
        try{
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            repo = (Repositorio) in.readObject();
            in.close();
            fileIn.close();
        } catch(IOException ex){
            System.out.println("Erro Deserializar: " + ex.getMessage());
        } catch(ClassNotFoundException ex){
            System.out.println("Classe Repositorio não encontrada. " + ex.getMessage());
        }
    }
}
