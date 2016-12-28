package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inno.pojo.Book;
import ru.inno.utils.MyException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alexander Rudnev
 */
public class BookDaoImpl implements BookDao {

//    Connection connection;

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

//    public BookDaoImpl(Connection dbConnection) {
//        this.connection = dbConnection;
//    }

//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }

    @Override
    public Book getBookById(int id) throws MyException {

        Book book = new Book();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnectionPool.getConnection();
            statement = connection.prepareStatement(BOOKBYID);
            statement.setInt(1,id);
            rs = statement.executeQuery();
            rs.next();

            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setPublisher(rs.getString("publisher"));
            book.setTitle(rs.getString("title"));
            book.setYearPublishing(rs.getInt("yearpub"));

        } catch (SQLException e) {
            logger.warn("SQLException in getBookById" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getBookById");
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return book;
    }

    @Override
    public List<Book> getBookByTitle(String title) throws MyException {
        List<Book> books = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnectionPool.getConnection();
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
            logger.warn("SQLException in getBookByTitle" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getBookByTitle");
        }finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.warn("Some exception in getBookByTitle" + e.getStackTrace());
            }
        }

        return books;
    }

    @Override
    public List<Book> getBookByPublisher(String publisher) throws MyException {
        List<Book> books = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = DBConnectionPool.getConnection();
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
            logger.warn("SQLException in getBookByPublisher" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getBookByPublisher");
        }finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.warn("Some exception in getBookByPublisher" + e.getStackTrace());
            }
        }

        return books;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) throws MyException {
        List<Book> books = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnectionPool.getConnection();
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
            logger.warn("SQLException in getBooksByAuthor" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getBooksByAuthor");
        }finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
    }

    @Override
    public List<Book> getBookByYear(int year) throws MyException {
        List<Book> books = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnectionPool.getConnection();
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
            logger.warn("SQLException in getBookByYear" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getBookByYear");
        }finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
    }

    @Override
    public List<Book> getAllBooks() throws MyException {
        List<Book> books = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnectionPool.getConnection();
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
            logger.warn("SQLException in getAllBooks" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in getAllBooks");
        }finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
    }

    @Override
    public void removeBookById(int id) throws MyException {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DBConnectionPool.getConnection();
            statement = connection.createStatement();
            statement.execute("delete from books where id = " + id);

        } catch (SQLException e) {
            logger.warn("SQLException in removeBookById" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in removeBookById");
        }finally {
            try {
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }

    }

    @Override
    public void addBook(Book book) throws MyException {

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBConnectionPool.getConnection();
            statement = connection.createStatement();

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
            logger.warn("SQLException in addBook" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in addBook");
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }

        }
    }

    @Override
    public void updateBook(Book book) throws MyException {

        //            String query = "insert into users (login, password, firstname, lastname) values('" ;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionPool.getConnection();
            statement = connection.prepareStatement(UPDATEBOOK);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYearPublishing());
            statement.setInt(5, book.getId());

            //            logger.info(statement.get);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("SQLException in updateBook" + Arrays.toString(e.getStackTrace()));
            throw new MyException("SQLException in updateBook");
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}
