package PSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private static Conexion connectInstance;
    Connection connection;
    String URL = "jdbc:postgresql://localhost:1541/Almacen";
    String USER = "postgres";
    String PASSWORD = "R0bert541";

    public Conexion() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado!");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("OK ");
        }
    }

    public static synchronized Conexion getInstance() {
        if (connectInstance == null) {
            connectInstance = new Conexion();
        }
        return connectInstance;
    }

    public Connection getConnection() {
        return connection;
    }
}
