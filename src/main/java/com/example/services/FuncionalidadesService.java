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
        this.user = new User();
    }

    public void criarProjeto(Projeto projeto) {
        this.user.getProjetos().add(projeto);
    }

    public void listarProjetos() {
        for (int i = 0; i < this.user.getProjetos().size(); i++) {
            System.out.println(this.user.getProjetos().get(i).toString());
        }
    }
}
