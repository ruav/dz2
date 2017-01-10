package ru.inno.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.pojo.User;
import ru.inno.service.UserDaoService;
import ru.inno.utils.MyException;
import ru.inno.utils.MyMath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Служит для обработки операций с
 * пользователями.
 * Все операции выполняются в POST методе,
 * а отображение списка - в методе GET.
 * @author Alexander Rudnev
 */
@Service
public class UsersServlet extends HttpServlet {

    @Autowired
    private UserDaoService userDaoService;

//    ApplicationContext applicationContext = null;
//
//
//    @Override
//    public void init() throws ServletException {
//        applicationContext = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("it's get method");

//        HttpSession httpSession = req.getSession();
//        if (httpSession.getAttribute("userId") != null) {

        if (req.getParameter("edit") != null && !req.getParameter("edit").equals("")) {


            int id = Integer.valueOf(req.getParameter("edit"));

//            UserDaoService userDaoService = new UserDaoService();

            req.setAttribute("edit", id);
            try {
                req.setAttribute("user", userDaoService.getById(id));
            } catch (MyException e) {
//                e.printStackTrace();
                req.getRequestDispatcher("/error").forward(req,resp);
            }
            req.getRequestDispatcher("/jsp/users/edituser.jsp").forward(req, resp);
        } else {


//            UserDaoService userDaoService = new UserDaoService();
//            UserDaoService userDaoService = applicationContext.getBean("");
            req.setAttribute("title", "Список всех пользователей");
            try {
                req.setAttribute("users", userDaoService.getAll());
            } catch (MyException e) {
//                e.printStackTrace();
                req.getRequestDispatcher("/error").forward(req,resp);
            }
//            System.out.println("user = " + httpSession.getAttribute("login"));
//            System.out.println(userDaoService.getAll().toString());

//            System.out.println(req.getRemoteUser());

            req.getRequestDispatcher("jsp/users/users.jsp").forward(req, resp);
        }
//        } else
//            req.getRequestDispatcher("/").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        System.out.println("It's post method");

//        HttpSession httpSession = req.getSession();
//        if (httpSession.getAttribute("userId") == null) {
//            req.getRequestDispatcher("/").forward(req, resp);
//        }

//        for(String str: req.getParameterMap().keySet()){
//            System.out.println(str + " : " + req.getParameter(str));
//        }

        try {
//            System.out.println((req.getParameter("remove") != null));
//            System.out.println(!req.getParameter("remove").equals(""));

            if ((req.getParameter("remove") != null) && (!req.getParameter("remove").equals(""))) {

                int id = Integer.parseInt(req.getParameter("remove"));
//                UserDaoService userDaoService = new UserDaoService();
                userDaoService.removeById(id);
            } else if (req.getParameter("edit") != null && !req.getParameter("edit").equals("")) {

                int id = Integer.valueOf(req.getParameter("edit"));
                User user = new User();
//                UserDaoService userDaoService = new UserDaoService();

                user.setLogin(req.getParameter("login"));
                user.setFirstName(req.getParameter("firstname"));
                user.setLastName(req.getParameter("lastname"));
                user.setPassword(MyMath.createMD5(req.getParameter("password")));
                user.setId(id);

                userDaoService.updateById(user);

//                req.getRequestDispatcher("users").forward(req, resp);
                resp.sendRedirect("/users");
            } else if (req.getParameter("adminconfig") != null) {

                int id = Integer.valueOf(req.getParameter("adminconfig"));
                boolean isAdmin = Boolean.valueOf(req.getParameter("isadmin"));
//                UserDaoService userDaoService = new UserDaoService();

                User user = userDaoService.getById(id);
                user.setAdmin(isAdmin);


                userDaoService.updateById(user);
                req.removeAttribute("adminconfig");
                req.removeAttribute("isadmin");
//                req.getRequestDispatcher("/users") .forward(req, resp);

                /**
                 * Этот редирект используется для
                 * предотвращения повторных изменений данных
                 * путем обновления страницы
                 */
                resp.sendRedirect("/users");
            }
        }catch (Exception e){
            req.getRequestDispatcher("/error").forward(req,resp);
//            e.printStackTrace();
//            System.out.println("Nullpointer!!!!!!");
        }
    }
}