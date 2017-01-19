package ru.inno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.dao.BookDao;
import ru.inno.dao.BookDaoImpl;
import ru.inno.dao.BookRepository;
import ru.inno.pojo.Book;
import ru.inno.utils.MyException;

import java.util.List;

/**
 * @author Alexander Rudnev
 */
@Service
public class BookDaoService {

    @Autowired
    private BookDao bookDao;
//
//    @Autowired
//    private BookRepository bookRepository;

//    private static BookDao bookDao;

    public Book getById(int id) throws MyException {
//        return bookRepository.getById(id).getBook();
        return bookDao.getById(id);
    }

    public  List<Book> getByTitle(String title) throws MyException {
        return bookDao.getByTitle(title);
    }
    public  List<Book> getByPublisher(String publisher) throws MyException {
        return bookDao.getByPublisher(publisher);
    }
    public  List<Book> getByAuthor(String author) throws MyException {
        return bookDao.getByAuthor(author);
    }
    public  List<Book> getByYear(int year) throws MyException {
        return bookDao.getByYear(year);
    }
    public  List<Book> getAll() throws MyException {
        return bookDao.getAll();
    }
    public  void removeById(int id) throws MyException {
        bookDao.removeById(id);
    }
    public  void add(Book book) throws MyException {
        bookDao.add(book);
    }
    public  void update(Book book) throws MyException {
        bookDao.update(book);
    }


}
