package br.com.everton.backend.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String IP = "localhost";
    private static final String BANCO_DADOS = "veiculodb";
    private static final String USER = "root";
    private static final String SENHA = "";

    static {
        try {
            // Carregando classe que será utilizada como conector
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static Connection connection;

    public static Connection getConnection() {
        try {
            // tentando realizar conexão localhost:3306
            String strConexao = "jdbc:mysql://" + IP + "/" + BANCO_DADOS + "?" + "user=" + USER + "&" + "password=" + SENHA;
            connection = DriverManager.getConnection(strConexao);
            System.out.println("Conexão OK.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void fecharConexao() {
        try {
            connection.close();
            System.out.println("Conexão sendo encerrada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
