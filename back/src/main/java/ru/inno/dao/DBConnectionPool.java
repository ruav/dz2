package ru.inno.dao;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ruav on 10.01.17.
 */
public class DBConnectionPool {

    private static DataSource dataSource;

    private String URL = "jdbc:postgresql://localhost:5432/postgres";
    private String DRIVERCLASSNAME = "org.postgresql.Driver";
    private String USERNAME = "postgres";
    private String PASSWORD = "postgres";


    private DBConnectionPool() {

        DataSource ds = new DataSource();
        ds.setDriverClassName(DRIVERCLASSNAME);
        ds.setUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
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


    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        DBConnectionPool.dataSource = dataSource;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDRIVERCLASSNAME() {
        return DRIVERCLASSNAME;
    }

    public void setDRIVERCLASSNAME(String DRIVERCLASSNAME) {
        this.DRIVERCLASSNAME = DRIVERCLASSNAME;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}

