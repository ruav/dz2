package ru.inno.dao;

import ru.inno.pojo.User;
import ru.inno.utils.MyException;

import java.util.List;

/**
 * @author Alexander Rudnev
 */

/**
 * Интерфейс для работы с пользователями.
 * Предоставляет базовый функционал.
 */
public interface UserDao {

    void createUser(User user) throws MyException;
    User getUserById(int id) throws MyException;
    User getUserByLogin(String login) throws MyException;
    List<User> getAllUsers() throws MyException;
    void removeUserById(int id) throws MyException;
    void updateUserById(User user) throws MyException;

}
