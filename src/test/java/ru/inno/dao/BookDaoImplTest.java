package ru.inno.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.inno.pojo.Book;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alexander Rudnev
 */
@Ignore
public class BookDaoImplTest {

    BookDaoImpl bookDao = null;
    @Ignore
    @Before
    public void init() throws SQLException, ClassNotFoundException {
//        bookDao = new BookDaoImpl(DBConnection.getConnection());
    }


    @Test
    public void getBookById() throws Exception {
        Book book = bookDao.getById(1);
        assertTrue(book.getId() == 1);
    }

    @Test
    public void getBookByTitle() throws Exception {
        String title = "Тарас Бульба";
        List<Book> bookList = bookDao.getByTitle(title);
        assertTrue(bookList.get(0).getId() == 3);
    }

    @Test
    public void getBookByPublisher() throws Exception {
        String publisher = "Евразия";
        List<Book> bookList = bookDao.getByPublisher(publisher);
        assertTrue(bookList.get(0).getId() == 2);
    }

    @Test
    public void getBookByAuthor() throws Exception {
//        String author = "Жан Фавье";
        String author = "Поба";
        List<Book> bookList = bookDao.getByAuthor(author);
        assertTrue(!bookList.isEmpty());
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