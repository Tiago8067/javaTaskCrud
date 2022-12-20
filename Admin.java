public class Admin extends Utilizador{

    public Admin() {
        super();
    }

    public Admin(String nomeUtilizador) {
        super(nomeUtilizador, TipoUtilizador.ADMIN);
    }
}
