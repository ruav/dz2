package ru.inno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class UserDaoService {

    @Autowired
    private  UserDao userDao;
//    private static UserDao userDao;

    public  User getById(int id) throws MyException {
        return userDao.getById(id);
    }
    public  User getByLogin(String login) throws MyException {
        return userDao.getByLogin(login);
    }
    public  List<User> getAll() throws MyException {
        return userDao.getAll();
    }
    public  void removeById(int id) throws MyException {
        userDao.removeById(id);
    }
    public  void add(User user) throws MyException {
        userDao.add(user);
    }
    public  void updateById(User user) throws MyException {
        userDao.updateById(user);
    }

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
}
