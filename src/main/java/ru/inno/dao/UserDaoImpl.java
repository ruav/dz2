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


//    Connection connection;

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

//    public UserDaoImpl(Connection dbConnection) {
//        this.connection = dbConnection;
//    }

//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }


    @Override
    public User getUserById(int id) throws MyException {
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
            logger.warn("SQLException in getUserById" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getUserById");
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

                user = new User();
            }

        }

        return user;
    }

    @Override
    public User getUserByLogin(String login) throws MyException {

        User user = new User();

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnectionPool.getConnection();
            statement = connection.createStatement();
            String query = "select * from users where login = '" + login + "' order by login";
            rs =  statement.executeQuery(query);
            rs.next();

            user.setId(rs.getInt("id"));
            user.setAdmin(rs.getBoolean("admin"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));


        }catch (Exception e){
            logger.warn("SQLException in getUserByLogin" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getUserByLogin");
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                user = new User();
            }

        }

        return user;
    }

    @Override
    public List<User> getAllUsers() throws MyException {

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
            logger.warn("SQLException in getAllUsers" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getAllUsers");
        }finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return users;
    }

    @Override
    public void createUser(User user) throws MyException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionPool.getConnection();
//            String query = "insert into users (login, password, firstname, lastname) values('" ;
            statement = connection.prepareStatement(CREATENEWUSER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());

//            logger.info(statement.get);

            statement.execute();

        } catch (SQLException e) {
            logger.warn("SQLException in createUser" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in createUser");
        }finally {
            try {
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }

        }

    }

    @Override
    public void removeUserById(int id) throws MyException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnectionPool.getConnection();
            statement = connection.prepareStatement(REMOVEUSERBYID);

            statement.setInt(1,id);
            statement.execute();


        } catch (SQLException e) {
            logger.warn("SQLException in removeUserById" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in removeUserById");
        }finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void updateUserById(User user) throws MyException {

//            String query = "insert into users (login, password, firstname, lastname) values('" ;
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

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("SQLException in updateUserById" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in updateUserById");
        }finally {
            try {
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }




    }
}
