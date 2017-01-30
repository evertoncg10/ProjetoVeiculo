package br.com.everton.backend.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

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
            // tentando realizar conexão
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veiculodb?" + "user=root&password=");
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
