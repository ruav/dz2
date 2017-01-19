package ru.inno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.dao.BookDao;
import ru.inno.dao.BookDaoImpl;
import ru.inno.dao.BookRepository;
import ru.inno.entity.BookEntity;
import ru.inno.pojo.Book;
import ru.inno.utils.MyException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Alexander Rudnev
 */
@Service
public class BookDaoService {

    @Autowired
    private BookDao bookDao;
//
    @Autowired
    private BookRepository bookRepository;

//    private static BookDao bookDao;

    public Book getById(int id) throws MyException {
        return bookRepository.findById(id).getBook();
//        return bookDao.getById(id);
    }

    public  List<Book> getByTitle(String title) throws MyException {
        return bookRepository
                .findByTitle(title)
                .stream()
                .map((s) -> s.getBook())
                .collect(Collectors.toList());
//        return bookDao.getByTitle(title);
    }
    public  List<Book> getByPublisher(String publisher) throws MyException {
       return  bookRepository
                .findByPublisher(publisher)
                .stream()
                .map(BookEntity::getBook)
                .collect(Collectors.toList());

//        return StreamSupport
//                .stream(bookRepository.findByPublisher(publisher).spliterator(),false)
//                .map((s)->s.getBook())
//                .collect(Collectors.toList());
//        return bookDao.getByPublisher(publisher);
    }
    public  List<Book> getByAuthor(String author) throws MyException {
        return  bookRepository
                .findByAuthor(author)
                .stream()
                .map(BookEntity::getBook)
                .collect(Collectors.toList());
//        return bookDao.getByAuthor(author);
    }
    public  List<Book> getByYear(int year) throws MyException {
        return StreamSupport
                .stream(bookRepository.findByYearPublishing(year).spliterator(),false)
                .map((s)->s.getBook())
                .collect(Collectors.toList());
//        return bookDao.getByYear(year);
    }
    public  List<Book> getAll() throws MyException {
        return StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .map((s)->s.getBook())
                .collect(Collectors.toList());
//        return bookDao.getAll();
    }
    public  void removeById(int id) throws MyException {
        bookRepository.removeById(id);
//        bookDao.removeById(id);
    }
    public  void add(Book book) throws MyException {
        bookRepository.save(new BookEntity(
                book.getId(),book.getTitle(), book.getAuthor(),
                book.getYearPublishing(),book.getPublisher()));
//        bookDao.add(book);
    }
    public  void update(Book book) throws MyException {
        bookRepository.save(new BookEntity(
                book.getId(),book.getTitle(), book.getAuthor(),
                book.getYearPublishing(),book.getPublisher()));
//        bookDao.update(book);
    }


}
