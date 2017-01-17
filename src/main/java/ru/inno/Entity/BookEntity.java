package ru.inno.Entity;

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
    private int id;

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

    public BookEntity(int id, String title, String author, int yearPublishing, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublishing = yearPublishing;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
