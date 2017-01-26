package ru.inno.service;

import ru.inno.pojo.User;
import ru.inno.utils.MyException;

import java.util.List;

/**
 * Created by ruav on 26.01.17.
 */
public interface UserDaoService {

    User getById(int id) throws MyException;
    User getByLogin(String login) throws MyException;
    List<User> getAll() throws MyException;
    void removeById(int id) throws MyException;
    void add(User user) throws MyException;
    void updateById(User user) throws MyException;


}
