package DAO;

import java.sql.*;

public abstract class ConnectionDAO {
    Connection connection;

    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    String database = "frota_logistica";
    String user = "root";
    String pass = "root";
    String url = "jdbc:mysql://localhost:3306/" + database;

    public Connection connectToDB()
    {
        try{
            connection = DriverManager.getConnection(url, user, pass);
        }catch (SQLException e)
        {
            System.out.println("Erro ao connectar com o banco de dados: " + e.getMessage());
        }

        return null;
    }

}
