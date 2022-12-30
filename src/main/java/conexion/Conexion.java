package conexion;

import java.sql.*;

public class Conexion {

    private String url;
    private String username;
    private String password;
    private Connection connection;

    private static Conexion singleton;

    private Conexion() {
        url = "jdbc:postgresql://localhost:5432/gotaxi";
        username = "postgres";
        password = "postgres";
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("conectado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Conexion conectar() {
        if (singleton == null) {
            singleton = new Conexion();
        }
        return singleton;
    }

    public Connection getConnection() {
        return connection;
    }
}
