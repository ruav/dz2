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

    //todo сделать статиком


    private static BookDao bookDao = new BookDaoImpl();

    public static Book getBookById(int id) throws MyException {
        return bookDao.getBookById(id);
    }

    public static List<Book> getBookByTitle(String title) throws MyException {
        return bookDao.getBookByTitle(title);
    }
    public static List<Book> getBookByPublisher(String publisher) throws MyException {
        return bookDao.getBookByPublisher(publisher);
    }
    public static List<Book> getBookByAuthor(String author) throws MyException {
        return bookDao.getBooksByAuthor(author);
    }
    public static List<Book> getBookByYear(int year) throws MyException {
        return bookDao.getBookByYear(year);
    }
    public static List<Book> getAllBooks() throws MyException {
        return bookDao.getAllBooks();
    }
    public static void removeBookById(int id) throws MyException {
        bookDao.removeBookById(id);
    }
    public static void addBook(Book book) throws MyException {
        bookDao.addBook(book);
    }
    public static void updateBook(Book book) throws MyException {
        bookDao.updateBook(book);
    }
}
