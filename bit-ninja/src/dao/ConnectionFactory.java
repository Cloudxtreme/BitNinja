package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
        	String url = "jdbc:mysql://localhost:3306/bitninja";
        	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            return DriverManager.getConnection(url, "root", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}