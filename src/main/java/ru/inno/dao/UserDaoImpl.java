package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.inno.pojo.User;
import ru.inno.utils.MyException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alexander Rudnev
 */
@Component
public class UserDaoImpl implements UserDao {


    private String CREATENEWUSER = "insert into users (login, password, firstname, lastname) " +
            "values(?,?,?,?)";
    private String UPDATEUSER = "update users set login = ?, password = ?, " +
            "firstname = ?, lastname = ?, admin = ? " +
            " where id = ?";
    private String SELECTUSERBYID = "select * from users where id = ?";
    private String REMOVEUSERBYID = "delete from users where id = ?";

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
    }


    @Override
    public User getById(int id) throws MyException {
        User user = new User();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnectionPool.getConnection();
            statement = connection.prepareStatement(SELECTUSERBYID);

            statement.setInt(1,id);
            rs =  statement.executeQuery();
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
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            }catch (Exception e){

            }
        }

        return user;
    }

    @Override
    public User getByLogin(String login) throws MyException {

        User user = new User();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = DBConnectionPool.getConnection();
            statement = connection.createStatement();
            String query = "select * from users where login = '" + login + "' order by login";
            rs =  statement.executeQuery(query);
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
        }finally {
            try{
                if(rs != null)
                    rs.close();
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            }catch (Exception e){

            }
        }

        return user;
    }

    @Override
    public List<User> getAll() throws MyException {

        List<User> users = new ArrayList<>();
        String query = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnectionPool.getConnection();
            statement = connection.createStatement();
            query = "select * from users order by id";
            rs =  statement.executeQuery(query);
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
        }finally {
            try{
                if(rs != null)
                    rs.close();
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            }catch (Exception e){

            }
        }

        return users;
    }

    @Override
    public void add(User user) throws MyException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionPool.getConnection();
//            String query = "insert into users (login, password, firstname, lastname) " ;
            statement = connection.prepareStatement(CREATENEWUSER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());

//            logger.info(statement.get);

            statement.execute();

        } catch (SQLException e) {
            logger.warn("SQLException in add" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in add");
        }finally {
            try{
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            }catch (Exception e){

            }
        }

    }

    @Override
    public void removeById(int id) throws MyException {
        User user = new User();

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionPool.getConnection();
            statement = connection.prepareStatement(REMOVEUSERBYID);

            statement.setInt(1,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.warn("SQLException in removeById" + Arrays.toString(e.getStackTrace()));
            logger.warn("SQL code = " + e.getErrorCode() + " SQL state = " + e.getSQLState());
            throw new MyException("SQLException in UserDaoImpl.removeById " + e.getErrorCode() + " " + e.getSQLState());
        }finally {
            try{
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            }catch (Exception e){

            }
        }

    }

    @Override
    public void updateById(User user) throws MyException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionPool.getConnection();
            statement = connection.prepareStatement(UPDATEUSER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setBoolean(5, user.isAdmin());
            statement.setInt(6, user.getId());

            //            logger.info(statement.get);

            logger.info("Update user: " + user.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("SQLException in updateById" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in updateById");
        }finally {
            try{
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            }catch (Exception e){

            }
        }
    }
}
