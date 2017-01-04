package ru.inno.service;

import ru.inno.dao.BookDao;
import ru.inno.dao.BookDaoImpl;
import ru.inno.pojo.Book;
import ru.inno.utils.MyException;

import java.util.List;

/**
 * @author Alexander Rudnev
 */
public class BookDaoService {



    private static BookDao bookDao = new BookDaoImpl();

    public static Book getById(int id) throws MyException {
        return bookDao.getById(id);
    }

    public static List<Book> getByTitle(String title) throws MyException {
        return bookDao.getByTitle(title);
    }
    public static List<Book> getByPublisher(String publisher) throws MyException {
        return bookDao.getByPublisher(publisher);
    }
    public static List<Book> getByAuthor(String author) throws MyException {
        return bookDao.getByAuthor(author);
    }
    public static List<Book> getByYear(int year) throws MyException {
        return bookDao.getByYear(year);
    }
    public static List<Book> getAll() throws MyException {
        return bookDao.getAll();
    }
    public static void removeById(int id) throws MyException {
        bookDao.removeById(id);
    }
    public static void add(Book book) throws MyException {
        bookDao.add(book);
    }
    public static void update(Book book) throws MyException {
        bookDao.update(book);
    }
}
