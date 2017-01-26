package ru.inno.service;

import ru.inno.pojo.Book;
import ru.inno.utils.MyException;

import java.util.List;

/**
 * Created by ruav on 26.01.17.
 */
public interface BookDaoService {

    Book getById(int id) throws MyException;
    List<Book> getByTitle(String title) throws MyException;
    List<Book> getByPublisher(String publisher) throws MyException;
    List<Book> getByAuthor(String author) throws MyException;
    List<Book> getByYear(int year) throws MyException;
    List<Book> getAll() throws MyException;
    void removeById(int id) throws MyException;
    void add(Book book) throws MyException;
    void update(Book book) throws MyException;

}
