package ru.inno.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alexander Rudnev
 */
public class User {

    private int id;
    private String login;
    private String password;
    private boolean admin;
    private String firstName;
    private String lastName;
    private int version;
    private Set<Book> books ;

    public User() {
        login = "";
        password = "";
        firstName = "";
        lastName = "";
        books = new HashSet<>();
    }

    public User(String login, String password, boolean admin, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.admin = admin;
        this.firstName = firstName;
        this.lastName = lastName;
        books = new HashSet<>();
    }

    public User(String login, String password, boolean admin, String firstName, String lastName,int version, Set<Book> books) {
        this.login = login;
        this.password = password;
        this.admin = admin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.version = version;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
//                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
