package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Alexander Rudnev
 */
public class DBConnectionPool {


    private static DataSource dataSource;



    private DBConnectionPool() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");
        ds.setInitialSize(5);
        ds.setMaxActive(10);
        ds.setMaxIdle(5);
        ds.setMinIdle(2);
        dataSource = ds;
    }


    public static Connection getConnection() throws SQLException {
        if(dataSource == null){
            new DBConnectionPool();
        }
        return dataSource.getConnection();
    }
}
