package ru.inno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import ru.inno.pojo.User;
import ru.inno.service.UserDaoService;
import ru.inno.utils.MyException;
import ru.inno.utils.MyMath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ruav on 08.01.17.
 */
@Controller
@SessionAttributes
//@RequestMapping("/")
public class MainController {

    @Autowired
    private UserDaoService userDaoService;

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping
    @RequestMapping("/")
    public String base(HttpServletRequest req, HttpServletResponse resp, Model model){
        System.out.println("Session user = " + req.getParameter("userId"));
        return "home";
    }

    @GetMapping
//    @RequestMapping(method = RequestMethod.GET)
    @RequestMapping("/login")
    public String loginPage(Model model){
//        model.addAttribute("message","WORLD!");
        return "login";
    }

    @PostMapping
    @RequestMapping("/autority")
    public String logout(HttpServletRequest req, HttpServletResponse resp, Model model){
        String login = req.getParameter("login");
        String outString = "home";
        HttpSession httpSession = req.getSession();

        if(req.getParameter("exit") != null && req.getParameter("exit") != "") {

            httpSession.invalidate();
            outString = "home";
        } else if(req.getParameter("login") != ""){
            String pass = MyMath.createMD5(req.getParameter("password"));
            User user = null;
            try {
                if((user = userDaoService.getByLogin(req.getParameter("login"))) != null && user.getPassword().equals(pass)){
                    httpSession.setAttribute("userId",user.getId());
                    httpSession.setAttribute("userName", user.getLogin());
                    httpSession.setAttribute("admin",user.isAdmin());
                    logger.info("user: " + httpSession.getAttribute("userId"));

                }
            } catch (MyException e) {
                outString = "error";
            }
        }
        return outString;
    }

//    @GetMapping
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model){
        return "users/registration";
    }

//    @PostMapping
    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String registrationCheck(HttpServletRequest req, HttpServletResponse resp, Model model){
    public String registrationCheck(@ModelAttribute("user") User userIn, Model model, BindingResult bindingResult){


//        UserDaoService userDaoService = new UserDaoService();
//        userDaoService.
        String pass = "";
        String outString = "home";


//        pass = MyMath.createMD5(req.getParameter("password"));
        pass = MyMath.createMD5(userIn.getPassword());


        User user = new User();
//        user.setLogin(req.getParameter("login"));
//        user.setLastName(req.getParameter("lastname"));
//        user.setFirstName(req.getParameter("firstname"));
//        user.setPassword(pass);
        user.setLogin(userIn.getLogin());
        user.setLastName(userIn.getLastName());
        user.setFirstName(userIn.getFirstName());
        user.setPassword(pass);

        logger.info(user.toString());

        try {
            userDaoService.add(user);
        } catch (MyException e) {
//            e.printStackTrace();
//            req.getRequestDispatcher("/error").forward(req,resp);
//            try {
////                resp.getWriter().print("error");
//            } catch (IOException e1) {
//
//            }
        }

        return outString;
    }


}
