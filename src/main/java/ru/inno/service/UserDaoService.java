package ru.inno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.dao.UserDao;
import ru.inno.dao.UserDaoImpl;
import ru.inno.dao.UserRepository;
import ru.inno.entity.UserEntity;
import ru.inno.pojo.User;
import ru.inno.utils.MyException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Autowired
    private UserRepository userRepository;

//    private static UserDao userDao;

    public  User getById(int id) throws MyException {
        return userRepository.findById(id).getUser();
//        return userDao.getById(id);
    }
    public  User getByLogin(String login) throws MyException {
        return userRepository.findByLogin(login).getUser();

//        return userDao.getByLogin(login);
    }
    public  List<User> getAll() throws MyException {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(),false)
                .map((u)->u.getUser())
                .collect(Collectors.toList());

//        return userDao.getAll();
    }
    public  void removeById(int id) throws MyException {
        userRepository.removeById(id);
//        userDao.removeById(id);
    }
    public  void add(User user) throws MyException {
       userRepository.save(new UserEntity(
               user.getId(),user.getLogin(),user.getFirstName(),
               user.getLastName(),user.isAdmin(),user.getPassword()));
//        userDao.add(user);
    }
    public  void updateById(User user) throws MyException {
        userRepository.save(new UserEntity(
                user.getId(),user.getLogin(),user.getFirstName(),
                user.getLastName(),user.isAdmin(),user.getPassword()));
//        userDao.updateById(user);
    }

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
}
