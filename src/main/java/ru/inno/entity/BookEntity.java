package ru.inno.entity;

import ru.inno.pojo.Book;

import javax.persistence.*;

/**
 * @author Alexander Rudnev
 */
@Entity
@Table(name = "books")
public class BookEntity {

//    Название, имя автора, год издания, издательство

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="books_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="pk_sequence")
//    @GeneratedValue(strategy=AUTO)
//    @GeneratedValue(generator="increment")
//    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id", unique=true, nullable=false)
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="yearPublishing")
    private int yearPublishing;

    @Column(name="publisher")
    private String publisher;

    public BookEntity() {
    }

    public BookEntity(long id, String title, String author, int yearPublishing, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublishing = yearPublishing;
        this.publisher = publisher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(int yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublishing=" + yearPublishing +
                ", publisher='" + publisher + '\'' +
                '}';
    }

    public Book getBook(){
        Book book = new Book();
        book.setId(getId());
        book.setAuthor(getAuthor());
        book.setPublisher(getPublisher());
        book.setTitle(getTitle());
        book.setYearPublishing(getYearPublishing());

        return book;
    }
}
