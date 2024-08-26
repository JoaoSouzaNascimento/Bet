package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoBdSingleton {

    private Connection conexao;
    private static ConexaoBdSingleton instance;

    private ConexaoBdSingleton() {
        connect();
    }

    private void connect() {
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5434/tigrinho", "postgres", "postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexao;
    }

    public static ConexaoBdSingleton getInstance() {
        if(instance == null) {
            instance = new ConexaoBdSingleton();
        }
        return instance;
    }
}
