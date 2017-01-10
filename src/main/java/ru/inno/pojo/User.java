package ru.inno.pojo;

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

    public User() {
        login = "";
        password = "";
        firstName = "";
        lastName = "";
    }

    public User(String login, String password, boolean admin, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.admin = admin;
        this.firstName = firstName;
        this.lastName = lastName;
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
