package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.pojo.User;
import ru.inno.service.UserDaoService;
import ru.inno.utils.MyException;
import ru.inno.utils.MyMath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ruav on 08.01.17.
 */

@Controller
public class UsersController extends ExceptionHandlingController{

    @Autowired
    private UserDaoService userDaoService;

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

                User user = new User();
                user.setLogin(userIn.getLogin());
                user.setFirstName(userIn.getFirstName());
                user.setLastName(userIn.getLastName());
                user.setPassword(MyMath.MD5Salt(userIn.getPassword()));
                user.setId(editId);
                user.setAdmin(userIn.isAdmin());
                userDaoService.updateById(user);
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
}
