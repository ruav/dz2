package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.inno.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Rudnev
 */
public class UserDaoImpl implements UserDao {


    Connection connection;

    private String CREATENEWUSER = "insert into users (login, password, firstname, lastname) " +
            "values(?,?,?,?)";
    private String UPDATEUSER = "update users set login = ?, password = ?, " +
            "firstname = ?, lastname = ?, admin = ? " +
            " where id = ?";
    private String SELECTUSERBYID = "select * from users where id = ?";
    private String REMOVEUSERBYID = "delete from users where id = ?";

    private static Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    public UserDaoImpl() {
    }

    public UserDaoImpl(Connection dbConnection) {
        this.connection = dbConnection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    @Override
    public User getUserById(int id) {
        User user = new User();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECTUSERBYID);

            statement.setInt(1,id);
            ResultSet rs =  statement.executeQuery();
            rs.next();

            user.setId(rs.getInt("id"));
            user.setAdmin(rs.getBoolean("admin"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));


        } catch (SQLException e) {
            user = null;
        }

        return user;
    }

    @Override
    public User getUserByLogin(String login) {

        User user = new User();

        try {
            Statement statement = connection.createStatement();
            String query = "select * from users where login = '" + login + "' order by login";
            ResultSet rs =  statement.executeQuery(query);
            rs.next();

            user.setId(rs.getInt("id"));
            user.setAdmin(rs.getBoolean("admin"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));


        } catch (SQLException e) {
            user = null;
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String query = "";
        try {
            Statement statement = connection.createStatement();
            query = "select * from users order by id";
            ResultSet rs =  statement.executeQuery(query);
            while(rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setAdmin(rs.getBoolean("admin"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));

                users.add(user);
            }
        } catch (SQLException e) {
            logger.warn(query);
        }

        return users;
    }

    @Override
    public void createUser(User user) {
        try {
//            String query = "insert into users (login, password, firstname, lastname) values('" ;
            PreparedStatement statement = connection.prepareStatement(CREATENEWUSER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());

//            logger.info(statement.get);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(int id) {
        User user = new User();

        try {
            PreparedStatement statement = connection.prepareStatement(REMOVEUSERBYID);

            statement.setInt(1,id);
            ResultSet rs =  statement.executeQuery();
            rs.next();

        } catch (SQLException e) {
            user = null;
        }

    }

    @Override
    public void updateUserById(User user) {

//            String query = "insert into users (login, password, firstname, lastname) values('" ;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATEUSER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setBoolean(5, user.isAdmin());
            statement.setInt(6, user.getId());

            //            logger.info(statement.get);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
