package models;

import enums.*;

public class UserManager extends Utilizador {

    public UserManager() {
        super();
    }

    public UserManager(String nomeUtilizador) {
        super(nomeUtilizador, TipoUtilizador.USERMANAGER);
    }
}
