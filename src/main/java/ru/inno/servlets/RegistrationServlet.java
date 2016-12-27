package ru.inno.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.pojo.User;
import ru.inno.service.UserDaoService;
import ru.inno.utils.MyMath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Alexander Rudnev
 */
public class RegistrationServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter out = resp.getWriter();
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/plain; charset=UTF-8");
//        HttpSession httpSession = req.getSession();
//
//        if(httpSession.getAttribute("login") != null){
//            UserDaoService userDaoService = new UserDaoService();
//            req.setAttribute("title","Список всех пользователей");
//            req.setAttribute("users", userDaoService.getAllUsers());
//            System.out.println("user = " + httpSession.getAttribute("login"));
//            System.out.println(userDaoService.getAllUsers().toString());
////                    getServletContext().setAttribute("users", userDaoService.getAllUsers());
////            out.close();
//            req.getRequestDispatcher("/users").forward(req,resp);
//        } else {
//            PrintWriter out = resp.getWriter();
//            out.print("<h1>Hello Servlet</h1>");
//        }

        req.getRequestDispatcher("/jsp/users/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        UserDaoService userDaoService = new UserDaoService();
//        userDaoService.
            String pass = "";

/*

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(req.getParameter("password").getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            pass = DatatypeConverter.printHexBinary(messageDigest).toUpperCase();
*/
                pass = MyMath.createMD5(req.getParameter("password"));


                User user = new User();
                user.setLogin(req.getParameter("login"));
                user.setLastName(req.getParameter("lastname"));
                user.setFirstName(req.getParameter("firstname"));
                user.setPassword(pass);

                logger.info(user.toString());

                userDaoService.createUser(user);


    }
}
