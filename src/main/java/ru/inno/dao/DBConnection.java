package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Alexander Rudnev
 */
public class DBConnection {

    private static Connection connection;

    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String name = "postgres";
    private String password = "postgres";
    private static Logger logger = LoggerFactory.getLogger(DBConnection.class);


    private DBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);
            logger.info("Connection is created");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection()  {
        if(connection == null){
            new DBConnection();
        }

        return connection;
    }
}
