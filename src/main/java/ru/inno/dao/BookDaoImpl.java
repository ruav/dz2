package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inno.pojo.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Rudnev
 */
public class BookDaoImpl implements BookDao {

    Connection connection;

    private String BOOKBYID = "select * from books where id = ?";
    private String BOOKBYTITLE = "select * from books where title = ?";
    private String BOOKBYYEAR = "select * from books where yearpub = ?";

    private String UPDATEBOOK = "update books set title = ?, author = ?, " +
            "publisher = ?, yearpub = ?" +
            " where id = ?";


    private String CREATENEWBOOK = "insert into books (title, author, yearpub, publisher) " +
            "values(?,?,?,?)";


    private static Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    public BookDaoImpl() {
    }

    public BookDaoImpl(Connection dbConnection) {
        this.connection = dbConnection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Book getBookById(int id) {

        Book book = new Book();

        try {
            PreparedStatement statement = connection.prepareStatement(BOOKBYID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            rs.next();

            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setPublisher(rs.getString("publisher"));
            book.setTitle(rs.getString("title"));
            book.setYearPublishing(rs.getInt("yearpub"));
            rs.close();
            statement.close();
        } catch (SQLException e) {
            logger.warn("Some exception in getBookById");
        }

        return book;
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        List<Book> books = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(BOOKBYTITLE);
            statement.setString(1, title);
            rs = statement.executeQuery();
            while(rs.next()){
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setYearPublishing(rs.getInt("yearpub"));

                books.add(book);
            }

        } catch (SQLException e) {
            logger.warn("Some exception in getBookByTitle" + e.getStackTrace());
        }finally {
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                logger.warn("Some exception in getBookByTitle" + e.getStackTrace());
            }
        }

        return books;
    }

    @Override
    public List<Book> getBookByPublisher(String publisher) {
        List<Book> books = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from books where publisher like '%" + publisher + "%'");
            while(rs.next()){
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setYearPublishing(rs.getInt("yearpub"));

                books.add(book);
            }

        } catch (SQLException e) {
            logger.warn("Some exception in getBookByPublisher" + e.getStackTrace());
        }finally {
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                logger.warn("Some exception in getBookByPublisher" + e.getStackTrace());
            }
        }

        return books;
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from books where author = '" + author + "'");
            while(rs.next()){
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setYearPublishing(rs.getInt("yearpub"));

                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
    }

    @Override
    public List<Book> getBookByYear(int year) {
        List<Book> books = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(BOOKBYYEAR);
            statement.setInt(1, year);
            rs = statement.executeQuery();
            while(rs.next()){
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setYearPublishing(rs.getInt("yearpub"));

                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from books");
            while(rs.next()){
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setYearPublishing(rs.getInt("yearpub"));

                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
    }

    @Override
    public void removeBookById(int id) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("delete from books where id = " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void addBook(Book book){
        try {


            Statement statement = connection.createStatement();

            String query = "insert into books (title, publisher";
            if(!book.getAuthor().isEmpty()){
                query +=",author";
            }if(book.getYearPublishing() != 0){
                query +=",yearpub";
            }
            query += ") values('" + book.getTitle() + "','" + book.getPublisher() + "'";
            if(!book.getAuthor().isEmpty()){
                query +=",'" + book.getAuthor() + "'";
            }if(book.getYearPublishing() != 0){
                query +="," + book.getYearPublishing();
            }
            query += ")";
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(Book book){

        //            String query = "insert into users (login, password, firstname, lastname) values('" ;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATEBOOK);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYearPublishing());
            statement.setInt(5, book.getId());

            //            logger.info(statement.get);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
