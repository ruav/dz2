package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.inno.pojo.User;
import ru.inno.utils.MyException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        try {
            this.connection = DBConnection.getConnection();
        } catch (MyException e) {
//            e.printStackTrace();
            logger.warn("Exception in constructor" + Arrays.toString(e.getStackTrace()));
        }
    }

    public UserDaoImpl(Connection dbConnection) {
        this.connection = dbConnection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    @Override
    public User getById(int id) throws MyException {
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
            logger.warn("SQLException in getById" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getById");
        }

        return user;
    }

    @Override
    public User getByLogin(String login) throws MyException {

        User user = new User();

        try {
            Statement statement = connection.createStatement();
            String query = "select * from users where login = '" + login + "' order by login";
            ResultSet rs =  statement.executeQuery(query);
            if(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setAdmin(rs.getBoolean("admin"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
            }

        } catch (SQLException e) {
            logger.warn("SQLException in getByLogin" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getByLogin");
        }

        return user;
    }

    @Override
    public List<User> getAll() throws MyException {

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
            logger.warn("SQLException in getAll" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getAll");
        }

        return users;
    }

    @Override
    public void add(User user) throws MyException {
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
            logger.warn("SQLException in add" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in add");
        }

    }

    @Override
    public void removeById(int id) throws MyException {
        User user = new User();

        try {
            PreparedStatement statement = connection.prepareStatement(REMOVEUSERBYID);

            statement.setInt(1,id);
            ResultSet rs =  statement.executeQuery();
            rs.next();

        } catch (SQLException e) {
            logger.warn("SQLException in removeById" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in removeById");
        }

    }

    @Override
    public void updateById(User user) throws MyException {

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
            logger.warn("SQLException in updateById" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in updateById");
        }
    }
}
