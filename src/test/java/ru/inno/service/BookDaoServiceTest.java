package ru.inno.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.inno.pojo.Book;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ruav on 17.01.17.
 */


@ContextConfiguration({
//        "file:src/main/webapp/WEB-INF/library-servlet.xml",
//        "file:src/main/webapp/WEB-INF/security-context.xml",
        "file:src/main/webapp/WEB-INF/spring/applicationContext.xml"
        })
@RunWith(SpringJUnit4ClassRunner.class)
public class BookDaoServiceTest {

    @Autowired
    BookDaoService bookDaoService;

    private static Logger logger = LoggerFactory.getLogger(BookDaoServiceTest.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getById() throws Exception {

        System.out.println("Hello");
        Book book = bookDaoService.getById(8);
        logger.debug(book.toString());
//        System.out.println(book.toString());
        assert(book.getId() != 0);
    }

    @Test
    public void getByTitle() throws Exception {

    }

    @Test
    public void getByPublisher() throws Exception {

    }

    @Test
    public void getByAuthor() throws Exception {

    }

    @Test
    public void getByYear() throws Exception {

        List<Book> books = bookDaoService.getByYear(1987);
        logger.debug(books.toString());
        assert(books.size() != 0);


    }

    @Test
    public void getAll() throws Exception {
        System.out.println("getAll");
        List<Book> books = bookDaoService.getAll();
        logger.debug(books.toString());
        assert(books.size() != 0);
    }

    @Test
    public void removeById() throws Exception {

        bookDaoService.removeById(72);
        logger.debug(bookDaoService.getById(72).toString());
        assert (bookDaoService.getById(72).getId() == 0);
    }

    @Test
    public void add() throws Exception {

    }

    @Test
    public void update() throws Exception {

        Book book = bookDaoService.getById(71);

        book.setTitle("2345");
        bookDaoService.update(book);
        logger.debug(bookDaoService.getById(71).toString());
        assert (bookDaoService.getById(71).getTitle().equals("2345"));
    }

}