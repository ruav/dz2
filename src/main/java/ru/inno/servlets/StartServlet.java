package ru.inno.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.pojo.User;
import ru.inno.service.UserDaoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Alexander Rudnev
 */
public class StartServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(StartServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain; charset=UTF-8");
        HttpSession httpSession = req.getSession();

        System.out.println("Session user = " + req.getParameter("userId"));

        if(httpSession.getAttribute("userId") != null){
            UserDaoService userDaoService = new UserDaoService();
            req.setAttribute("title","Список всех пользователей");
            req.setAttribute("users", userDaoService.getAllUsers());
            req.getRequestDispatcher("/users").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
