package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.pojo.Book;
import ru.inno.pojo.User;
import ru.inno.service.BookDaoService;
import ru.inno.service.UserDaoService;
import ru.inno.utils.MyException;
import ru.inno.utils.MyMath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ruav on 08.01.17.
 */
//@Async
@Controller
public class UsersController extends ExceptionHandlingController{

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private BookDaoService bookDaoService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getUsers(@RequestParam(name = "edit", defaultValue = "0") int id, ModelAndView modelAndView){

        if(id != 0) {


            modelAndView.addObject("edit", id);
            try {
                modelAndView.addObject("user", userDaoService.getById(id));
                modelAndView.setViewName("users/edituser");
            } catch (MyException e) {
//                outString = "error";
                modelAndView.setViewName("error");
            }
        } else {

            modelAndView.addObject("title", "Список всех пользователей");
            try {
                modelAndView.addObject("users", userDaoService.getAll());
                modelAndView.setViewName("users/users");
            } catch (MyException e) {
                modelAndView.setViewName("error");
            }

        }


        return modelAndView;
    }


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ModelAndView editUsers(@RequestParam(name = "edit", defaultValue = "0") int editId,
                                  @RequestParam(name = "remove", defaultValue = "0") int removeId,
                                  @RequestParam(name = "adminconfig", defaultValue = "0") int adminId,
                                  @RequestParam(name = "isadmin", defaultValue = "false") boolean isadmin,
                                  @ModelAttribute("user") User userIn,
                                  BindingResult bindingResult,
            ModelAndView modelAndView){

            String outString = "redirect:users";

        try {

            if (removeId != 0) {
                userDaoService.removeById(removeId);
            } else if (editId != 0) {

//                User user = new User();
//                user.setLogin(userIn.getLogin());
//                user.setFirstName(userIn.getFirstName());
//                user.setLastName(userIn.getLastName());
//                user.setPassword(MyMath.MD5Salt(userIn.getPassword()));
//                user.setId(editId);
//                user.setAdmin(userIn.isAdmin());
//
//
                userDaoService.updateById(userIn);
            } else if (adminId != 0) {
                boolean isAdmin = isadmin;
                User user = userDaoService.getById(adminId);
                user.setAdmin(isAdmin);
                userDaoService.updateById(user);
            }
        }catch (Exception e){
//            outString = "error";
        }

        modelAndView.setViewName(outString);
        return modelAndView;

    }

    @RequestMapping(value = "/users/user/{id}",method = RequestMethod.GET)
    public ModelAndView aboutUser(@PathVariable int id, ModelAndView modelAndView){

        String outString = "users/aboutuser";
        try {
            modelAndView.addObject("user", userDaoService.getById(id));
        } catch (MyException e) {
//            outString = "error";
        }

        modelAndView.setViewName(outString);

        return modelAndView;
    }

    @RequestMapping(value="/users/user/{id}/removebook/{book_id}")
    public ModelAndView removeBook(@PathVariable int id, @PathVariable int book_id, ModelAndView modelAndView){
        User user = new User();

        try {
            user = userDaoService.getById(id);
        } catch (MyException e){
//            outString = "error";
        }
//        System.out.println(user.getBooks().size());
//
//        for(Book book : user.getBooks()){
//            System.out.println(book.toString());
//        }

//        int removeId = book_id;
        Book removeBook = new Book();
        for(Book book : user.getBooks()){
            if(book.getId() == book_id){
                removeBook = book;
                break;
            }
        }

        user.getBooks().remove(removeBook);
//        System.out.println(user.getBooks().remove(removeBook));
//        System.out.println(user.getBooks().remove(bookDaoService.getById(18)));
//        System.out.println(user.getBooks().remove(removeBook));
//        System.out.println(user.getBooks().size());


        try {
            userDaoService.updateById(user);
//        System.out.println(userDaoService.getById(1).getBooks().toString());
        }catch (MyException e){
//            outString = "error";
        }

        modelAndView.setViewName("redirect:/users/user/" + id);
//        modelAndView.setViewName("redirect:users/user"+id);
//        modelAndView.setViewName("forward:/users");

//        modelAndView;
        return modelAndView;
//        return new ModelAndView("redirect:users/user"+id);

    }

    @RequestMapping(value="/users/user/{id}/addbook/{book_id}")
    public ModelAndView addBook(@PathVariable int id, @PathVariable int book_id, ModelAndView modelAndView){
        User user = new User();

        try {
            user = userDaoService.getById(id);
        } catch (MyException e){
//            outString = "error";
        }
        Book addbook = new Book();
        try {
            addbook = bookDaoService.getById(book_id);
        }catch (MyException e){
            //            outString = "error";
        }
        if (addbook.getId() != 0) {
            user.getBooks().add(addbook);
        }


//        System.out.println(user.getBooks().remove(removeBook));
//        System.out.println(user.getBooks().remove(bookDaoService.getById(18)));
//        System.out.println(user.getBooks().remove(removeBook));
//        System.out.println(user.getBooks().size());


        try {
            userDaoService.updateById(user);
//        System.out.println(userDaoService.getById(1).getBooks().toString());
        }catch (MyException e){
//            outString = "error";
        }

        modelAndView.setViewName("redirect:/books");
//        modelAndView.setViewName("redirect:users/user"+id);
//        modelAndView.setViewName("forward:/users");

//        modelAndView;
        return modelAndView;
//        return new ModelAndView("redirect:users/user"+id);

    }
}
