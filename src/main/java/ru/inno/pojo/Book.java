package ru.inno.pojo;

/**
 * @author Alexander Rudnev
 */
public class Book {

//    Название, имя автора, год издания, издательство


    private long id;
    private String title;
    private String author;
    private int yearPublishing;
    private String publisher;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
