package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inno.entity.BookEntity;
import ru.inno.pojo.Book;
import ru.inno.utils.MyException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Rudnev
 */

@Component
public class BookDaoImpl implements BookDao {

    @Autowired
    private EntityManagerFactory emf;


    private static Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    public BookDaoImpl() {
    }

    @Override
    public Book getById(int id) throws MyException {

        Book book = new Book();
//        ApplicationContext aptx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        EntityManagerFactory emf = (EntityManagerFactory) aptx.getBean("entityManagerFactory");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("base");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        BookEntity emp = (BookEntity) em.find(BookEntity.class, id);
//        System.out.println("\r\n\r\n" + "BookEntity = " + emp.toString() + "\r\n\r\n");

        if(emp != null) {
//            System.out.println();
//            book.setId(id);
//            book.setAuthor(emp.getAuthor());
//            book.setPublisher(emp.getPublisher());
//            book.setTitle(emp.getTitle());
//            book.setYearPublishing(emp.getYearPublishing());

            book = emp.getBook();
        }

        em.getTransaction().commit();

        em.close();
        return book;
    }

    @Override
    public List<Book> getByTitle(String title) throws MyException {
        List<Book> books = new ArrayList<>();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<BookEntity> q = em.createQuery("SELECT b FROM BookEntity b WHERE b.title = :custName", BookEntity.class).setParameter("custName", title);
        List<BookEntity> bookEntities = q.getResultList();

        for(BookEntity be: bookEntities){
            books.add(be.getBook());
//            System.out.println(be.getBook().toString());
        }
        em.close();
        return books;
    }

    @Override
    public List<Book> getByPublisher(String publisher) throws MyException {

        List<Book> books = new ArrayList<>();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<BookEntity> q = em.createQuery("SELECT b FROM BookEntity b WHERE b.publisher = :custName", BookEntity.class).setParameter("custName", publisher);
        List<BookEntity> bookEntities = q.getResultList();

        for(BookEntity be: bookEntities){
            books.add(be.getBook());
//            System.out.println(be.getBook().toString());
        }
        em.close();
        return books;
    }

    @Override
    public List<Book> getByAuthor(String author) throws MyException {
        List<Book> books = new ArrayList<>();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<BookEntity> q = em.createQuery("SELECT b FROM BookEntity b WHERE b.author = :custName", BookEntity.class).setParameter("custName", author);
        List<BookEntity> bookEntities = q.getResultList();

        for(BookEntity be: bookEntities){
            books.add(be.getBook());
//            System.out.println(be.getBook().toString());
        }

        em.close();

        return books;
    }

    @Override
    public List<Book> getByYear(int year) throws MyException {
        List<Book> books = new ArrayList<>();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<BookEntity> q = em.createQuery("SELECT b FROM BookEntity b WHERE b.yearPublishing = :custName", BookEntity.class).setParameter("custName", year);
        List<BookEntity> bookEntities = q.getResultList();

        for(BookEntity be: bookEntities){
            books.add(be.getBook());
//            System.out.println(be.getBook().toString());
        }

        em.close();

        return books;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getAll() throws MyException {
        List<Book> books = new ArrayList<>();


        Book book = new Book();
//        ApplicationContext aptx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        EntityManagerFactory emf = (EntityManagerFactory) aptx.getBean("entityManagerFactory");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("base");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

//        TypedQuery<BookEntity> query = em.createQuery("select e from books e", BookEntity.class);
//        TypedQuery<BookEntity> query = em.createQuery("select e from books e", BookEntity.class);
//
//        List<BookEntity> bookEntities = query.getResultList();


//        Query query = em.createQuery("SELECT e FROM books e", BookEntity.class);
//        TypedQuery<BookEntity> query = em.createQuery("select id, title, author, publisher, yearpublishing from books", BookEntity.class);
//        List<BookEntity> bookEntities = (List<BookEntity>) query.getResultList();
            Query q = em.createQuery("select b from BookEntity b", BookEntity.class);
           List<BookEntity> bookEntities = q.getResultList();



        for(BookEntity be: bookEntities){
            books.add(be.getBook());
            System.out.println(be.getBook().toString());
        }



/*

        BookEntity emp = (BookEntity) em.find(BookEntity.class, id);
        System.out.println("\r\n\r\n" + "BookEntity = " + emp.toString() + "\r\n\r\n");

        if(emp != null) {
//            System.out.println();
//            book.setId(id);
//            book.setAuthor(emp.getAuthor());
//            book.setPublisher(emp.getPublisher());
//            book.setTitle(emp.getTitle());
//            book.setYearPublishing(emp.getYearPublishing());

            book = emp.getBook();
        }
*/

        em.getTransaction().commit();



/*

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("from books", Book.class);
//        query.set setResultTransformer(Transformers.aliasToBean(LogEntry.class))
//        ArrayList<LogEntry> entries = (ArrayList<LogEntry>) query.getResultList();

        listBook = query.getResultList();
//        books = em.createQuery("from books").getResultList();
        return listBook;*/
//
//        UserEntity emp = (UserEntity) em.find(UserEntity.class, new Long(1));
//        System.out.println(emp.toString());
//
//        em.getTransaction().commit();
//

        em.close();

        return books;
    }

    @Override
    public void removeById(int id) throws MyException {

        EntityManager em = emf.createEntityManager();
        BookEntity book = em.find(BookEntity.class, id);

        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void add(Book book) throws MyException {

        EntityManager em = emf.createEntityManager();
        BookEntity bookE = new BookEntity();

        bookE.setYearPublishing(book.getYearPublishing());
        bookE.setAuthor(book.getAuthor());
        bookE.setTitle(book.getTitle());
        bookE.setPublisher(book.getPublisher());

        em.getTransaction().begin();
        em.persist(bookE);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void update(Book book) throws MyException {

        // insert into users (login, password, firstname, lastname) values(

        EntityManager em = emf.createEntityManager();
        BookEntity bookE = em.find(BookEntity.class, book.getId());

        em.getTransaction().begin();
            bookE.setYearPublishing(book.getYearPublishing());
            bookE.setAuthor(book.getAuthor());
            bookE.setTitle(book.getTitle());
            bookE.setPublisher(book.getPublisher());
        em.getTransaction().commit();
        em.close();
    }
}