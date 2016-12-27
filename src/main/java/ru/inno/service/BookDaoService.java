package ru.inno.service;

import ru.inno.dao.BookDao;
import ru.inno.dao.BookDaoImpl;
import ru.inno.dao.DBConnection;
import ru.inno.pojo.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Alexander Rudnev
 */
public class BookDaoService {


    BookDao  bookDao;  ///сделать статиком

    public BookDaoService() throws SQLException, ClassNotFoundException {
        bookDao = new BookDaoImpl(DBConnection.getConnection());
    }

    public Book getBookById(int id){
        return bookDao.getBookById(id);
    }

    public List<Book> getBookByTitle(String title){
        return bookDao.getBookByTitle(title);
    }
    public List<Book> getBookByPublisher(String publisher){
        return bookDao.getBookByPublisher(publisher);
    }
    public List<Book> getBookByAuthor(String author){
        return bookDao.getBookByAuthor(author);
    }
    public List<Book> getBookByYear(int year){
        return bookDao.getBookByYear(year);
    }
    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }
    public void removeBookById(int id){
        bookDao.removeBookById(id);
    }
    public void addBook(Book book){
        bookDao.addBook(book);
    }
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
}
