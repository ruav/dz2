package ru.inno.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.inno.dao.DBConnection;
import ru.inno.dao.UserDao;
import ru.inno.dao.UserDaoImpl;
import ru.inno.pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Alexander Rudnev
 */

//@Component
//@ComponentScan
//@Configurable
public class UserDaoService {
//    @Autowired
    UserDao userDao;


    public UserDaoService() {
        try {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        this.userDao = (UserDao) applicationContext.getBean("userDao");

            this.userDao = new UserDaoImpl(DBConnection.getConnection());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id){
        return userDao.getUserById(id);
    }
    public User getUserByLogin(String login){
        return userDao.getUserByLogin(login);
    }
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
    public void removeUserById(int id){
        userDao.removeUserById(id);
    }
    public void createUser(User user){
        userDao.createUser(user);
    }

    public void updateUserById(User user){
        userDao.updateUserById(user);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
