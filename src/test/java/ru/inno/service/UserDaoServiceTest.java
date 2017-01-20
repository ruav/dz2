package ru.inno.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.inno.pojo.User;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ruav on 17.01.17.
 */

@Ignore
@ContextConfiguration({
//        "file:src/main/webapp/WEB-INF/library-servlet.xml",
//        "file:src/main/webapp/WEB-INF/security-context.xml",
        "file:src/main/webapp/WEB-INF/spring/applicationContext.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserDaoServiceTest {

    @Autowired
    UserDaoService userDaoService;

    private static Logger logger = LoggerFactory.getLogger(BookDaoServiceTest.class);

    @Test
    public void getById() throws Exception {
        User user = userDaoService.getById(1);
        logger.info(user.toString());
        assert(user.getLogin().equals("ruav"));

    }

    @Test
    public void getByLogin() throws Exception {
        User user = userDaoService.getByLogin("admin");
        logger.info(user.toString());
        assert(user.getId() == 3);
    }

    @Test
    public void getAll() throws Exception {
        List<User> users = userDaoService.getAll();

        for(User user : users){
            System.out.println(user.getBooks().toString());
        }

        System.out.println(userDaoService.getAll());
    }

    @Test
    public void removeById() throws Exception {

    }

    @Test
    public void add() throws Exception {
        User user = new User();
        user.setLogin("Temp");
        user.setPassword("1234567890");
        userDaoService.add(user);

        System.out.println(userDaoService.getByLogin("Temp"));
        assert(userDaoService.getByLogin("Temp").getId()!=0);
    }

    @Test
    public void updateById() throws Exception {
        User user = userDaoService.getById(49);
        user.setLogin("Broruav");
        userDaoService.updateById(user);

        user = userDaoService.getById(49);
        System.out.println(user.toString());

        assert("Broruav".equals(user.getLogin()));
    }

}