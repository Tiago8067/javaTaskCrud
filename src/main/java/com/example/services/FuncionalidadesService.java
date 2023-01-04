package com.example.services;

import com.example.database.Database;
import com.example.models.Projeto;
import com.example.models.User;
import com.example.utils.Util;

public class FuncionalidadesService {
    Database database;
    Util util;
    User user;

    public FuncionalidadesService(Database database) {
        this.database = database;
        this.util = new Util(database);
    }

    public void criarProjeto(Projeto projeto) {
        this.user.getProjetos().add(projeto);
    }
}
