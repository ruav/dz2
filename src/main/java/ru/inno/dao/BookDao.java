package ru.inno.dao;

import ru.inno.pojo.Book;
import ru.inno.utils.MyException;

import java.util.List;

/**
 * @author Alexander Rudnev
 */

/**
 * Интерфейс для работы с книгами.
 * Предоставляет базовый функционал.
 */
public interface BookDao {

    /**
     * Получить книгу по номеру id
     * @param id идентификатор книги
     * @return возращает книгу, если
     * она есть в базе, или же пустую книгу,
     * если ее нет в бд
     * @throws MyException
     */
    Book getById(int id) throws MyException;

    /**
     * Получить список книг по названию
     * @param title название книги
     * @return возвращает список книг, если
     * они есть в базе, или же пустой лист,
     * если ее нет в бд
     * @throws MyException
     */
    List<Book> getByTitle(String title) throws MyException;

    /**
     * Получить список книг по названию издательства
     * @param publisher название издательства
     * @return возвращает список книг, если
     * они есть в базе, или же пустой лист,
     * если ее нет в бд
     * @throws MyException
     */
    List<Book> getByPublisher(String publisher) throws MyException;

    /**
     *  Получить список книг по автору
     * @param author
     * @return Возвращает список книг, если
     * они есть в базе, или же пустой список,
     * если ее нет в бд
     * @throws MyException
     */

    List<Book> getByAuthor(String author) throws MyException;

    /**
     *  Получить список книг по году издания
     * @param year год издания книги
     * @return возвращает список книг, если
     * они есть в базе, или же пустой список,
     * если ее нет в бд
     * @throws MyException
     */
    List<Book> getByYear(int year) throws MyException;

    /**
     * Получить список всех книг в библиотеке
     * @return возвращает список книг, если
     * они есть в базе, или же пустой список,
     * если ее нет в бд
     * @throws MyException
     */
    List<Book> getAll() throws MyException;

    /**
     * Удалить книгу по id
     * @param id идентификатор книги удаляемой книги
     * @throws MyException
     */
    void removeById(int id) throws MyException;

    /**
     * Добавить новую книгу в базу
     * @param book Новая книга для добавления
     * @throws MyException
     */
    void add(Book book) throws MyException;

    /**
     * Обновить книгу в базе
     * @param book Экземпляр обновленной книги
     * @throws MyException
     */
    void update(Book book) throws MyException;

}
