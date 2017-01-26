package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inno.entity.UserEntity;
import ru.inno.pojo.User;
import ru.inno.utils.MyException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Rudnev
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory emf;

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
    }


    @Override
    public User getById(int id) throws MyException {
        User user = new User();
//        ApplicationContext aptx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        EntityManagerFactory emf = (EntityManagerFactory) aptx.getBean("entityManagerFactory");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("base");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        UserEntity emp = (UserEntity) em.find(UserEntity.class, id);

        if(emp != null) {
            user = emp.getUser();
        }

        em.getTransaction().commit();
        em.close();
        return user;
    }

    @Override
    public User getByLogin(String login) throws MyException {

        User user = new User();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query q = em.createQuery("SELECT b FROM UserEntity b WHERE b.login = :custName", UserEntity.class).setParameter("custName", login);
        List<UserEntity> userEntities = q.getResultList();

        user = userEntities.get(0).getUser();
        em.close();
        return user;
    }

    @Override
    public List<User> getAll() throws MyException {

        List<User> users = new ArrayList<>();


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
        Query q = em.createQuery("select b from UserEntity b", UserEntity.class);
        List<UserEntity> userEntities = q.getResultList();

        for(UserEntity ue : userEntities){
            users.add(ue.getUser());
            System.out.println(ue.getUser().toString());
        }
        em.close();
        return users;
    }

    @Override
    public void add(User user) throws MyException {
        EntityManager em = emf.createEntityManager();
        UserEntity userE = new UserEntity();

        userE.setLogin(user.getLogin());
        userE.setPassword(user.getPassword());
        userE.setFirstName(user.getFirstName());
        userE.setLastName(user.getLastName());
        userE.setAdmin(user.isAdmin());

        em.getTransaction().begin();
        em.persist(userE);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void removeById(int id) throws MyException {
        EntityManager em = emf.createEntityManager();
        UserEntity user = em.find(UserEntity.class, id);

        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateById(User user) throws MyException {

        EntityManager em = emf.createEntityManager();
        UserEntity userE = em.find(UserEntity.class, user.getId());

        em.getTransaction().begin();

        userE.setLogin(user.getLogin());
        userE.setPassword(user.getPassword());
        userE.setFirstName(user.getFirstName());
        userE.setLastName(user.getLastName());
        userE.setAdmin(user.isAdmin());

        em.getTransaction().commit();
        em.close();
    }
}
