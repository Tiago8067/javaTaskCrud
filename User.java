public class User extends Utilizador{

    public User() {
        super();
    }

    public User(String nomeUtilizador) {
        super(nomeUtilizador, TipoUtilizador.USER);
    }
}
