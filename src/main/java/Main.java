import ru.inno.utils.MyException;

import java.sql.SQLException;

/**
 * @author Alexander Rudnev
 */

public class Main {

//    @PersistenceContext
//    static EntityManager em;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, MyException {
//        Book book;
//
//        BookDao bookDao = new BookDaoImpl();
//
//
//        book = bookDao.getById(1);
//
//        System.out.println(book.toString());



//        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("base");
//        em = managerFactory.createEntityManager();
//
//        em.getTransaction().begin();
//
//        UserEntity emp = (UserEntity) em.find(UserEntity.class, new Long(1));
//        System.out.println(emp.toString());
//
//        em.getTransaction().commit();

/*

        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from persistence.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        UserEntity userEntity = new UserEntity();

        userEntity.setAdmin(false);
        userEntity.setLogin("Bro112");
        userEntity.setFirstName("Hощр");
        userEntity.setPassword("oafh");



//        task.setId(new Long(1));
//        task.setName("Hello world task");
//        task.setDescription("Hello world task description");
        session.save(userEntity);
        UserEntity emp = (UserEntity) session.get(UserEntity.class, new Long(1));
        System.out.println(emp.toString());

//        session.get



        UserEntity emp1 = (UserEntity) session.get(UserEntity.class, "ruav");
        System.out.println(emp1.toString());

        tx.commit();
        session.close();
*/


    }
}
