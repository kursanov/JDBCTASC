package kg.kursamov.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration {

    public static Connection getConnection() {
        Connection connection = null;
        try {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "716202"
        );

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;

    }

}
