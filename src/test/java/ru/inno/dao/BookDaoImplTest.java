package ru.inno.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.inno.pojo.Book;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Danil Popov
 */
@Ignore
public class BookDaoImplTest {

    BookDaoImpl bookDao = null;
    @Ignore
    @Before
    public void init() throws SQLException, ClassNotFoundException {
        bookDao = new BookDaoImpl();
    }


    @Test
    public void getBookById() throws Exception {
        Book book = bookDao.getBookById(1);
        assertTrue(book.getId() == 1);
    }

    @Test
    public void getBookByTitle() throws Exception {
        String title = "Тарас Бульба";
        List<Book> bookList = bookDao.getBookByTitle(title);
        assertTrue(bookList.get(0).getId() == 3);
    }

    @Test
    public void getBookByPublisher() throws Exception {
        String publisher = "Евразия";
        List<Book> bookList = bookDao.getBookByPublisher(publisher);
        assertTrue(bookList.get(0).getId() == 2);
    }

    @Test
    public void getBookByAuthor() throws Exception {
        String author = "Жан Фавье";
        List<Book> bookList = bookDao.getBooksByAuthor(author);
        assertTrue(bookList.get(0).getId() == 2);
    }

    @Ignore
    @Test
    public void getBookByYear() throws Exception {

    }

    @Ignore
    @Test
    public void getAllBooks() throws Exception {

    }

}