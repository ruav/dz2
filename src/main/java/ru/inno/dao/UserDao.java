package ru.inno.dao;

import ru.inno.pojo.User;

import java.util.List;

/**
 * @author Alexander Rudnev
 */
public interface UserDao {

    void createUser(User user);
    User getUserById(int id);
    User getUserByLogin(String login);
    List<User> getAllUsers();
    void removeUserById(int id);
    void updateUserById(User user);

}
