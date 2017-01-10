package ru.inno.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.service.UserDaoService;
import ru.inno.utils.MyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Alexander Rudnev
 */
@Service
public class StartServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(StartServlet.class);

    @Autowired
    private UserDaoService userDaoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain; charset=UTF-8");
        HttpSession httpSession = req.getSession();

        System.out.println("Session user = " + req.getParameter("userId"));

        if(httpSession.getAttribute("userId") != null){
//            UserDaoService userDaoService = new UserDaoService();
            req.setAttribute("title","Список всех пользователей");
            try {
                req.setAttribute("users", userDaoService.getAll());
            } catch (MyException e) {
//                e.printStackTrace();
                req.getRequestDispatcher("/error").forward(req,resp);
            }
            req.getRequestDispatcher("/users").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
