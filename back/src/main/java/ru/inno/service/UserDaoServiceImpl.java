package ru.inno.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.inno.dao.UserRepository;
import ru.inno.entity.BookEntity;
import ru.inno.entity.UserEntity;
import ru.inno.pojo.Book;
import ru.inno.pojo.User;
import ru.inno.utils.MyException;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Alexander Rudnev
 */

//@Component
//@ComponentScan
//@Configurable
@Service
public class UserDaoServiceImpl implements UserDaoService, Serializable{

//    @Autowired
//    private  UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier(value = "userMapper")
    MapperFacade mapperFacade;

//    private static UserDao userDao;

    public  User getById(int id) throws MyException {

        UserEntity userEntity = userRepository.findById(id);

        User user = mapperFacade.map(userEntity,User.class);

        user.setBooks(userEntity.getBooks()
                .stream()
                .map((b)->mapperFacade.map(b,Book.class))
                .collect(Collectors.toSet()));

        return user;
//         return mapperFacade.map (userRepository.findById(id),User.class);
//        return userRepository.findById(id).getUser();
//        return userDao.getById(id);
    }
    public  User getByLogin(String login) throws MyException {
        UserEntity userEntity = userRepository.findByLogin(login);
        User user = mapperFacade.map(userEntity,User.class);

        user.setBooks(userEntity.getBooks()
                .stream()
                .map((b)->mapperFacade.map(b,Book.class))
                .collect(Collectors.toSet()));

/*
        return mapperFacade.map(userEntity,User.class)
                .setBooks(userEntity.getBooks()
                        .stream()
                        .map((b)->mapperFacade.map(b,Book.class))
                        .collect(Collectors.toSet()));
*/

        return user;
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
                .map((u)->{
                    User map = mapperFacade.map(u, User.class);
                    map.setBooks(
                            u.getBooks()
                                .stream()
                                .map((b)->mapperFacade.map(b,Book.class))
                                .collect(Collectors.toSet()));
                    return map;
                })
                .sorted((o1, o2) -> o1.getLogin().compareToIgnoreCase(o2.getLogin()))
                .collect(Collectors.toList());

//        return userDao.getAll();
    }
    public  void removeById(int id) throws MyException {
        userRepository.removeById(id);
//        userDao.removeById(id);
    }
    public  void add(User user) throws MyException {
        Set<BookEntity> bookEntities = user.getBooks()
                .stream()
                .map((b)->mapperFacade.map(b,BookEntity.class))
                .collect(Collectors.toSet());

        UserEntity userEntity = mapperFacade.map(user,UserEntity.class);
        userEntity.setBooks(bookEntities);

        userRepository.save(userEntity);
//        userRepository.save(new UserEntity(
//               user.getId(),user.getLogin(),user.getFirstName(),
//               user.getLastName(),user.isAdmin(),user.getPassword()));
////        userDao.add(user);
    }
    public  void updateById(User user) throws MyException {

        Set<BookEntity> bookEntities = user.getBooks()
                .stream()
                .map((b)->mapperFacade.map(b,BookEntity.class))
                .collect(Collectors.toSet());

        UserEntity userEntity = mapperFacade.map(user,UserEntity.class);
        userEntity.setBooks(bookEntities);

        userRepository.save(userEntity);


//        userRepository.save(mapperFacade.map(user,UserEntity.class));

//        userRepository.save(new UserEntity(
//                user.getId(),user.getLogin(),user.getFirstName(),
//                user.getLastName(),user.isAdmin(),user.getPassword(),user.getVersion()));
//        userDao.updateById(user);
    }

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
}
