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
        req.getRequestDispatcher("/jsp/users/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String pass = "";
                pass = MyMath.createMD5(req.getParameter("password"));
                User user = new User();
                user.setLogin(req.getParameter("login"));
                user.setLastName(req.getParameter("lastname"));
                user.setFirstName(req.getParameter("firstname"));
                user.setPassword(pass);

                logger.info(user.toString());

        try {
            UserDaoService.createUser(user);
        } catch (MyException e) {
            /*
                В этом месте не совсем правильно отрабатывает редирект,
                поэтому реализовано с помощью команды в теле ответа, и
                ее обработка на стороне клиента.
             */

//            resp.sendRedirect("/error");
//            req.getRequestDispatcher("/error").forward(req,resp);
              resp.getWriter().print("error");
//            return;
        }


    }
}
