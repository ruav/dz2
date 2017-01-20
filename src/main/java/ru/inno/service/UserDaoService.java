package ru.inno.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

//    @Autowired
//    private  UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier(value = "userMapper")
    MapperFacade mapperFacade;

//    private static UserDao userDao;

    public  User getById(int id) throws MyException {
         return mapperFacade.map (userRepository.findById(id),User.class);
//        return userRepository.findById(id).getUser();
//        return userDao.getById(id);
    }
    public  User getByLogin(String login) throws MyException {
        return mapperFacade.map(userRepository.findByLogin(login),User.class);
//        return userRepository.findByLogin(login).getUser();

//        return userDao.getByLogin(login);
    }
    public  List<User> getAll() throws MyException {
//        return StreamSupport
//                .stream(userRepository.findAll().spliterator(),false)
//                .map((u)->u.getUser()).sorted((o1, o2) -> o1.getLogin().compareToIgnoreCase(o2.getLogin()))
//                .collect(Collectors.toList());
        return StreamSupport
                .stream(userRepository.findAll().spliterator(),false)
                .map((u)->mapperFacade.map(u,User.class)).sorted((o1, o2) -> o1.getLogin().compareToIgnoreCase(o2.getLogin()))
                .collect(Collectors.toList());

//        return userDao.getAll();
    }
    public  void removeById(int id) throws MyException {
        userRepository.removeById(id);
//        userDao.removeById(id);
    }
    public  void add(User user) throws MyException {
        userRepository.save(mapperFacade.map(user,UserEntity.class));
//        userRepository.save(new UserEntity(
//               user.getId(),user.getLogin(),user.getFirstName(),
//               user.getLastName(),user.isAdmin(),user.getPassword()));
////        userDao.add(user);
    }
    public  void updateById(User user) throws MyException {
        userRepository.save(mapperFacade.map(user,UserEntity.class));

//        userRepository.save(new UserEntity(
//                user.getId(),user.getLogin(),user.getFirstName(),
//                user.getLastName(),user.isAdmin(),user.getPassword(),user.getVersion()));
//        userDao.updateById(user);
    }

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
}
