package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBdSingleton {

	private Connection conexao;
	private static ConexaoBdSingleton instance;

	private ConexaoBdSingleton() throws SQLException {
		connect();
	}

	private void connect() throws SQLException {

		conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5434/tigrinho", "postgres", "postgres");

	}

	public Connection getConexao() throws SQLException {

		if (conexao == null || conexao.isClosed()) {
			connect();
		}
		return conexao;
	}

	public static ConexaoBdSingleton getInstance() throws SQLException {
		if (instance == null) {
			instance = new ConexaoBdSingleton();
		}
		return instance;
	}
}
