/*
package ru.inno;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import ru.inno.Entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

*/
/**
 * Created by ruav on 16.01.17.
 *//*


//ContextConfiguration({
//        "file:src/main/webapp/META-INF/spring/spring-app.xml",
//        "file:src/main/webapp/META-INF/spring/spring-db.xml"
//        })
//@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
@Component
public class tempTest {


    @PersistenceContext
//    @Autowired
//    @Qualifier("entityManagerFactory")
//    @Autowired
    private EntityManagerFactory emf;

    @Test
    public void test1(){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        UserEntity emp = (UserEntity) em.find(UserEntity.class, new Long(1));
        System.out.println(emp.toString());

        em.getTransaction().commit();

    }


}
*/
