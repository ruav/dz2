package ru.inno;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.inno.pojo.Book;
import ru.inno.pojo.User;
import ru.inno.service.BookDaoService;
import ru.inno.service.UserDaoService;
import ru.inno.utils.MyException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ruav on 21.01.17.
 */

@Ignore
@ContextConfiguration({
//        "file:src/main/webapp/WEB-INF/library-servlet.xml",
//        "file:src/main/webapp/WEB-INF/security-context.xml",
        "file:src/main/webapp/WEB-INF/spring/applicationContext.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class main {

    @Autowired
    UserDaoService userDaoService;

    @Autowired
    BookDaoService bookDaoService;

    @Test
    public void test() throws MyException {

        User user =  userDaoService.getById(1);

        System.out.println(user.getBooks().size());

        for(Book book : user.getBooks()){
            System.out.println(book.toString());
        }

        int removeId = 18;
        Book removeBook = new Book();
        for(Book book : user.getBooks()){
            if(book.getId() == removeId){
                removeBook = book;
                break;
            }
        }


//        System.out.println(user.getBooks().remove(bookDaoService.getById(18)));
        System.out.println(user.getBooks().remove(removeBook));

        System.out.println(user.getBooks().size());



        userDaoService.updateById(user);
        System.out.println(userDaoService.getById(1).getBooks().toString());

    }

}
