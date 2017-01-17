package ru.inno.Entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import ru.inno.pojo.Book;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.SEQUENCE;

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
    Long id;

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

    public UserEntity(Long id, String login, String firstName, String lastName, Boolean admin, String password) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
