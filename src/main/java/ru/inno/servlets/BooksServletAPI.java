package ru.inno.servlets;

import com.google.gson.Gson;
import ru.inno.dao.BookDao;
import ru.inno.dao.BookDaoImpl;
import ru.inno.dao.DBConnection;
import ru.inno.pojo.Book;
import ru.inno.utils.MyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Rudnev
 */
public class BooksServletAPI extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
// Get the printwriter object from response to write the required json object to the output stream
        PrintWriter out = resp.getWriter();
// Assuming your json object is **jsonObject**, perform the following, it will return your json object

//        Gson gson = new Gson();
//        Animal a = new Animal();
//        String json = gson.toJson(a);
//        Animal a1 = gson.fromJson(json, Animal.class);
        List<Book> books = new ArrayList<>();


            BookDao bookDao = new BookDaoImpl();
        try {
            books = bookDao.getAllBooks();
        } catch (MyException e) {
//            req.getRequestDispatcher("error").forward(req,resp);
            req.getRequestDispatcher("/jsp/error.jsp").forward(req,resp);
            return;
        }


        Gson gson = new Gson();
        String json = gson.toJson(books);

        out.print(json);
        out.flush();

//        PrintWriter out = resp.getWriter();
//        out.print("<h1>Hello Servlet</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




    }
}
