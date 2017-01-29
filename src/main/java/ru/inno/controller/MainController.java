package ru.inno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import javax.servlet.http.HttpSession;

/**
 * Created by ruav on 08.01.17.
 */
@Controller
@SessionAttributes
//@Async
//@RequestMapping("/")
public class MainController extends ExceptionHandlingController{

    @Autowired
    private UserDaoService userDaoService;

    private static Logger logger = LoggerFactory.getLogger(MainController.class);


    @RequestMapping(value = { "/*", "/welcome", "/home" }, method = RequestMethod.GET)
    public ModelAndView defaultPage(HttpServletRequest req, ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        return modelAndView;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("test/admin");

        return model;

    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest req, Model model) {
        return "login";
    }


    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public String login2User(HttpServletRequest req, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            if (auth.getPrincipal() != null && (auth.getPrincipal() instanceof UserDetails)) {

                HttpSession session = req.getSession();
                UserDetails userDetail = (UserDetails) auth.getPrincipal();
                System.out.println(userDetail);

                if (session.getAttribute("userId") == null) {
                    try {
                        User user = userDaoService.getByLogin(userDetail.getUsername());
                        session.setAttribute("userId", user.getId());
                        session.setAttribute("admin", user.isAdmin());
                    } catch (MyException e) {
                        logger.warn(e.getStackTrace().toString());
                    }
                }
            }
        }

        return "home";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        request.getSession().removeAttribute("userId");
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good idea to show login screen again.
    }


    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("error");
        return model;


    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }


//    @GetMapping
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model){
        return "users/registration";
    }

//    @PostMapping
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registrationCheck(@ModelAttribute("user") User userIn, Model model, BindingResult bindingResult){


        String pass = "";
        String outString = "redirect:home";


        pass = MyMath.MD5Salt(userIn.getPassword());


        User user = new User();
        user.setLogin(userIn.getLogin());
        user.setLastName(userIn.getLastName());
        user.setFirstName(userIn.getFirstName());
        user.setPassword(pass);

        logger.info(user.toString());

        try {
            userDaoService.add(user);
        } catch (MyException e) {
        }

        return outString;
    }



}
