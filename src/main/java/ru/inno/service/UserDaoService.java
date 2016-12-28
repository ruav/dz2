package ru.inno.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.inno.dao.DBConnection;
import ru.inno.dao.UserDao;
import ru.inno.dao.UserDaoImpl;
import ru.inno.pojo.User;
import ru.inno.utils.MyException;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Alexander Rudnev
 */

//@Component
//@ComponentScan
//@Configurable
public class UserDaoService {

    //todo сделать статиком
    //    @Autowired
    private static UserDao userDao = new UserDaoImpl();

    public static User getUserById(int id) throws MyException {
        return userDao.getUserById(id);
    }
    public static User getUserByLogin(String login) throws MyException {
        return userDao.getUserByLogin(login);
    }
    public static List<User> getAllUsers() throws MyException {
        return userDao.getAllUsers();
    }
    public static void removeUserById(int id) throws MyException {
        userDao.removeUserById(id);
    }
    public static void createUser(User user) throws MyException {
        userDao.createUser(user);
    }
    public static void updateUserById(User user) throws MyException {
        userDao.updateUserById(user);
    }

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
}
