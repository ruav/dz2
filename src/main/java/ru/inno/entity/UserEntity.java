package ru.inno.entity;

import ru.inno.pojo.User;

import javax.persistence.*;

/**
 * Created by ruav on 16.01.17.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="users_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
//    @GeneratedValue(strategy=AUTO)
//    @GeneratedValue(generator="increment")
//    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id", unique=true, nullable=false)
    int id;

    @Column(name="login")
    String login;

    @Column(name="firstname")
    String firstName;

    @Column(name="lastname")
    String lastName;

    @Column(name="admin")
    Boolean admin;

    @Column(name="password")
    String password;

    public UserEntity() {
    }

    public UserEntity(int id, String login, String firstName, String lastName, Boolean admin, String password) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
        this.password = password;
    }

    public Integer getId() {
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", admin=" + admin +
                ", password='" + password + '\'' +
                '}';
    }

    public User getUser(){
        User user = new User();

        user.setLogin(getLogin());
        user.setAdmin(getAdmin());
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setFirstName(getFirstName());
        user.setPassword(getPassword());
        user.setId(getId());

        return user;
    }
}
