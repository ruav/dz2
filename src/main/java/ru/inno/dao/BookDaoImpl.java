package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.inno.pojo.Book;
import ru.inno.utils.MyException;
import ru.inno.utils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alexander Rudnev
 */

@Component
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
        try {
            this.connection = DBConnection.getConnection();
        } catch (MyException e) {
            logger.warn("Exception in contruction DBConnection" + Arrays.toString(e.getStackTrace()));
        }
    }

    public BookDaoImpl(Connection dbConnection) {
        this.connection = dbConnection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Book getById(int id) throws MyException {

        Book book = new Book();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(BOOKBYID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            rs.next();

            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setPublisher(rs.getString("publisher"));
            book.setTitle(rs.getString("title"));
            book.setYearPublishing(rs.getInt("yearpub"));
            rs.close();
            statement.close();
        } catch (SQLException e) {
            logger.warn("SQLException in getById" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getById");
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.warn("SQLException in getById" + Arrays.toString(e.getStackTrace()));
            }
        }

        return book;
    }

    @Override
    public List<Book> getByTitle(String title) throws MyException {
        List<Book> books = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(BOOKBYTITLE);
            statement.setString(1, title);
            rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setYearPublishing(rs.getInt("yearpub"));

                books.add(book);
            }

        } catch (SQLException e) {
            logger.warn("SQLException in getByTitle" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getByTitle");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
            }
        }

        return books;
    }

    @Override
    public List<Book> getByPublisher(String publisher) throws MyException {
        List<Book> books = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from books where publisher like '%" + publisher + "%'");
            while (rs.next()) {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setYearPublishing(rs.getInt("yearpub"));

                books.add(book);
            }

        } catch (SQLException e) {
            logger.warn("SQLException in addBook" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in addBook");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.warn("Some exception in getByPublisher" + e.getStackTrace());
            }
        }

        return books;
    }

    @Override
    public List<Book> getByAuthor(String author) throws MyException {
        List<Book> books = new ArrayList<>();
//        Statement statement = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(Queries.SELECTALLFROMTABLEBYFIELD.replaceFirst("\\?","books").replaceFirst("\\?","author"));
//            rs = statement.executeQuery("select * from books where author = '" + author + "'");
            statement.setString(1, author);
            rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setYearPublishing(rs.getInt("yearpub"));

                books.add(book);
            }

        } catch (SQLException e) {
            logger.warn("SQLException in getBooksByAuthor" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getBooksByAuthor");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.warn("SQLException in addBook" + Arrays.toString(e.getStackTrace()));
            }
        }
        return books;
    }

    @Override
    public List<Book> getByYear(int year) throws MyException {
        List<Book> books = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(BOOKBYYEAR);
            statement.setInt(1, year);
            rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setYearPublishing(rs.getInt("yearpub"));

                books.add(book);
            }

        } catch (SQLException e) {
            logger.warn("SQLException in getByYear" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getByYear");
        } finally {
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                logger.warn("SQLException in addBook" + Arrays.toString(e.getStackTrace()));
            }
        }

        return books;
    }

    @Override
    public List<Book> getAll() throws MyException {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(Queries.SELECTALLFROMTABLE.replaceFirst("\\?","books"))){
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setYearPublishing(rs.getInt("yearpub"));

                books.add(book);
            }

        } catch (SQLException e) {
            logger.warn("SQLException in getAll" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getAll");
        }

        return books;
    }

    @Override
    public void removeById(int id) throws MyException {

        try (Statement statement = connection.createStatement()){
            statement.execute("delete from books where id = " + id);
        } catch (SQLException e) {
            logger.warn("SQLException in removeById" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in removeById");
        }

    }

    @Override
    public void add(Book book) throws MyException {
        try {


            Statement statement = connection.createStatement();

            String query = "insert into books (title, publisher";
            if (!book.getAuthor().isEmpty()) {
                query += ",author";
            }
            if (book.getYearPublishing() != 0) {
                query += ",yearpub";
            }
            query += ") values('" + book.getTitle() + "','" + book.getPublisher() + "'";
            if (!book.getAuthor().isEmpty()) {
                query += ",'" + book.getAuthor() + "'";
            }
            if (book.getYearPublishing() != 0) {
                query += "," + book.getYearPublishing();
            }
            query += ")";
            statement.execute(query);

        } catch (SQLException e) {
            logger.warn("SQLException in addBook" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in addBook");
        }
    }

    @Override
    public void update(Book book) throws MyException {


        // insert into users (login, password, firstname, lastname) values(

        try (PreparedStatement statement = connection.prepareStatement(UPDATEBOOK)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYearPublishing());
            statement.setInt(5, book.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.warn("SQLException in update" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in update");
        }
    }
}