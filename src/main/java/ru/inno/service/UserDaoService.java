package ru.inno.service;

import ru.inno.dao.UserDao;
import ru.inno.dao.UserDaoImpl;
import ru.inno.pojo.User;
import ru.inno.utils.MyException;

import java.util.List;

/**
 * @author Alexander Rudnev
 */

//@Component
//@ComponentScan
//@Configurable
public class UserDaoService {

    //    @Autowired

    private static UserDao userDao = new UserDaoImpl();

    public static User getById(int id) throws MyException {
        return userDao.getById(id);
    }
    public static User getByLogin(String login) throws MyException {
        return userDao.getByLogin(login);
    }
    public static List<User> getAll() throws MyException {
        return userDao.getAll();
    }
    public static void removeById(int id) throws MyException {
        userDao.removeById(id);
    }
    public static void add(User user) throws MyException {
        userDao.add(user);
    }
    public static void updateById(User user) throws MyException {
        userDao.updateById(user);
    }

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
}
