package ru.inno.dao;

import ru.inno.pojo.Book;

import java.util.List;

/**
 * @author Alexander Rudnev
 */
public interface BookDao {

    Book getBookById(int id);
    List<Book> getBookByTitle(String title);
    List<Book> getBookByPublisher(String publisher);
    List<Book> getBookByAuthor(String author);
    List<Book> getBookByYear(int year);
    List<Book> getAllBooks();
    void removeBookById(int id);
    void addBook(Book book);
    void updateBook(Book book);

}
