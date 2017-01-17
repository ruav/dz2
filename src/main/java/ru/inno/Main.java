package ru.inno;

import ru.inno.entity.UserEntity;
import ru.inno.utils.MyException;

import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

/**
 * Created by ruav on 16.01.17.
 */

//@Component
public class Main {

//    @PersistenceContext
//    @Autowired
//    @Qualifier("entityManagerFactory")
//    @Autowired
//    private static EntityManagerFactory emf;

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


//        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
//            appCtx.load("file:src/main/webapp/WEB-INF/security-context.xml",
//                    "file:src/main/webapp/WEB-INF/students-servlet.xml");
//            appCtx.refresh();
//
//            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
//            StudentService bookService = appCtx.getBean(StudentServiceImpl.class);
//            System.out.println();
//        }

//        GenericXmlApplicationContext aptx = new GenericXmlApplicationContext();
//
//        aptx.load("security-context.xml",
//                    "file:src/main/webapp/META-INF/persistence.xml");
//        aptx.refresh();
//
//        EntityManagerFactory emf = aptx.getBean(EntityManagerFactory.class);
//

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("base");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        UserEntity emp = (UserEntity) em.find(UserEntity.class, new Long(1));
        System.out.println(emp.toString());

        em.getTransaction().commit();

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

//    public Main(EntityManagerFactory emf) {
//        this.emf = emf;
//    }
}
