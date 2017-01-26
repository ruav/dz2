package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.utils.MyException;

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


    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, name, password);
        logger.info("Connection is created");
    }


    public static Connection getConnection() throws MyException {
        if(connection == null){
            try {
                new DBConnection();
            } catch (ClassNotFoundException | SQLException e) {
                logger.warn("Error with create DBConnection");
                throw new MyException("Error with create DBConnection");
            }
        }

        return connection;
    }
}
