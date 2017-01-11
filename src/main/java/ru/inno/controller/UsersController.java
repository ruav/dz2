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
public class UsersController {

    @Autowired
    private UserDaoService userDaoService;

//    @RequestMapping("/users")
    public String getUsers(HttpServletRequest req, HttpServletResponse resp, Model model){

        String outString = "users/users";

        if (req.getParameter("edit") != null && !req.getParameter("edit").equals("")) {


            int id = Integer.valueOf(req.getParameter("edit"));

            UserDaoService userDaoService = new UserDaoService();

            req.setAttribute("edit", id);
            try {
                req.setAttribute("user", userDaoService.getById(id));
            } catch (MyException e) {
//                e.printStackTrace();
                outString = "error";
            }
            outString = "users/edituser";
        } else {


            UserDaoService userDaoService = new UserDaoService();
//            UserDaoService userDaoService = applicationContext.getBean("");
            req.setAttribute("title", "Список всех пользователей");
            try {

                req.setAttribute("users", userDaoService.getAll());
            } catch (MyException e) {
//                e.printStackTrace();
                outString = "error";
            }
//            System.out.println("user = " + httpSession.getAttribute("login"));
//            System.out.println(userDaoService.getAll().toString());

//            System.out.println(req.getRemoteUser());

            outString = "users/users";
        }

        return outString;
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getUsers(@RequestParam(name = "edit", defaultValue = "0") int id, ModelAndView modelAndView){

//        UserDaoService userDaoService = new UserDaoService();
        if(id != 0) {


            modelAndView.addObject("edit", id);
//            req.setAttribute("edit", id);
            try {
                modelAndView.addObject("user", userDaoService.getById(id));
                modelAndView.setViewName("users/edituser");
            } catch (MyException e) {
//                e.printStackTrace();
//                outString = "error";
                modelAndView.setViewName("error");
            }
        } else {

//            UserDaoService userDaoService = applicationContext.getBean("");
            modelAndView.addObject("title", "Список всех пользователей");
//            req.setAttribute("title", "Список всех пользователей");
            try {
                modelAndView.addObject("users", userDaoService.getAll());
//                req.setAttribute("users", userDaoService.getAll());
                modelAndView.setViewName("users/users");
            } catch (MyException e) {
//                e.printStackTrace();
                modelAndView.setViewName("error");
            }
//            System.out.println("user = " + httpSession.getAttribute("login"));
//            System.out.println(userDaoService.getAll().toString());

//            System.out.println(req.getRemoteUser());



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

//        return modelAndView;
            String outString = "redirect:users";

        try {

            if (removeId != 0) {

//                int id = Integer.parseInt(req.getParameter("remove"));
//                UserDaoService userDaoService = new UserDaoService();
                userDaoService.removeById(removeId);
            } else if (editId != 0) {

//                int id = Integer.valueOf(req.getParameter("edit"));
//                User user = new User();
////                UserDaoService userDaoService = new UserDaoService();
//
//                user.setLogin(userIn.getLogin());
//                user.setFirstName(userIn.getFirstName());
//                user.setLastName(userIn.getLastName());
//                user.setPassword(MyMath.createMD5(userIn.getPassword()));
//                user.setId(editId);
                    userIn.setId(editId);
                userDaoService.updateById(userIn);

//                req.getRequestDispatcher("users").forward(req, resp);
//                resp.sendRedirect("/users");
            } else if (adminId != 0) {

//                int id = Integer.valueOf(req.getParameter("adminconfig"));
                boolean isAdmin = isadmin;
//                UserDaoService userDaoService = new UserDaoService();

                User user = userDaoService.getById(adminId);
                user.setAdmin(isAdmin);


                userDaoService.updateById(user);
//                req.removeAttribute("adminconfig");
//                req.removeAttribute("isadmin");
//                req.getRequestDispatcher("/users") .forward(req, resp);

                /**
                 * Этот редирект используется для
                 * предотвращения повторных изменений данных
                 * путем обновления страницы
                 */
//                resp.sendRedirect("/users");
            }
        }catch (Exception e){
//            req.getRequestDispatcher("/error").forward(req,resp);
//            e.printStackTrace();
//            System.out.println("Nullpointer!!!!!!");
            outString = "error";
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
            outString = "error";
        }

        modelAndView.setViewName(outString);

        return modelAndView;
    }
}
