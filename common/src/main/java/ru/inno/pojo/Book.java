package ru.inno.pojo;

import java.io.Serializable;

/**
 * @author Alexander Rudnev
 */
public class Book implements Serializable {

//    Название, имя автора, год издания, издательство


    private int id;
    private String title;
    private String author;
    private int yearPublishing;
    private String publisher;
    private int version;

    private static final long serialVersionUID = 1L;

    public Book() {
        title = "";
        author = "";
        publisher = "";
    }

    public Book(String title, String author, int yearPublishing, String publisher) {
        this.title = title;
        this.author = author;
        this.yearPublishing = yearPublishing;
        this.publisher = publisher;
    }

    public Book(String title, String author, int yearPublishing, String publisher, int version) {
        this.title = title;
        this.author = author;
        this.yearPublishing = yearPublishing;
        this.publisher = publisher;
        this.version = version;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublishing=" + yearPublishing +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
