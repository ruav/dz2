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

    /**
     * Добавить нового пользователя в базу
     * @param user Новый пользователь
     * @throws MyException
     */
    void add(User user) throws MyException;

    /**
     * Получить пользователя по номеру id
     * @param id Идентификатор пользователя
     * @return Возвращает пользователя, если он есть в базе,
     * иначе возвращает дефолтного пользователя.
     * @throws MyException
     */
    User getById(int id) throws MyException;

    /**
     * Получить пользователя по логину
     * @param login Искомый логин пользователя
     * @return Возвращает пользователя, если он есть в базе,
     * иначе возвращает дефолтного пользователя.
     * @throws MyException
     */
    User getByLogin(String login) throws MyException;

    /**
     * Поличить всех пользователей в базе
     * @return Список пользователей в базе,
     * если база пуста, то просто пустой список.
     * @throws MyException
     */
    List<User> getAll() throws MyException;

    /**
     * Удалить пользователя по номеру id
     * @param id Идентификатор пользователя
     * @throws MyException
     */
    void removeById(int id) throws MyException;

    /**
     * Обновить пользователя
     * @param user Передается пользователь, по
     * по идентификатору которого происходит обновление
     * @throws MyException
     */
    void updateById(User user) throws MyException;

}