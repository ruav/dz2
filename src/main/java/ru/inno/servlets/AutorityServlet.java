package ru.inno.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.pojo.User;
import ru.inno.service.UserDaoService;
import ru.inno.utils.MyException;
import ru.inno.utils.MyMath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * Сервлет отвечает за авторизацию пользователей.
 * Обращение по методу @GET перенаправляет на главную страницу.
 * Обращение по методу @POST вызывают функцию регистрации нового пользователя,
 * и дальнейшее перенаправление на главную страницу.
 * @author Alexander Rudnev
 */
public class AutorityServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(AutorityServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/plain; charset=UTF-8");
//        HttpSession httpSession = req.getSession();

        System.out.println("Session user = " + req.getParameter("userId"));
        req.getRequestDispatcher("/").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        HttpSession httpSession = req.getSession();



        if(req.getParameter("exit") != null && req.getParameter("exit") != ""){
            httpSession.invalidate();
            req.getRequestDispatcher("/").forward(req,resp);
        } else if(req.getParameter("login") != ""){
           String pass = MyMath.createMD5(req.getParameter("password"));
//            MessageDigest md = null;
//            try {
//                md = MessageDigest.getInstance("MD5");
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            }
//            byte[] messageDigest = md.digest(req.getParameter("password").getBytes());
//            String pass = DatatypeConverter.printHexBinary(messageDigest).toUpperCase();
//            UserDaoService userDaoService = new UserDaoService();
            User user = null;
            try {
                if((user = UserDaoService.getByLogin(req.getParameter("login"))) != null && user.getPassword().equals(pass)){
                    httpSession.setAttribute("userId",user.getLogin()); // заменить на userId
                    httpSession.setAttribute("admin",user.isAdmin());
                    logger.info("user: " + httpSession.getAttribute("userId"));
                    req.getRequestDispatcher("/").forward(req,resp);
                }
            } catch (MyException e) {
//                e.printStackTrace();
                req.getRequestDispatcher("/error").forward(req,resp);
            }
            req.getRequestDispatcher("/").forward(req,resp);

        } else if(httpSession.getAttribute("userId") != null){
            req.getRequestDispatcher("/").forward(req,resp);
        }else {

            req.getRequestDispatcher("/").forward(req, resp);
        }
    }
}
