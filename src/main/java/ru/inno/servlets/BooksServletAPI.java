package ru.inno.servlets;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.dao.BookDao;
import ru.inno.dao.BookDaoImpl;
import ru.inno.dao.DBConnection;
import ru.inno.pojo.Book;
import ru.inno.service.BookDaoService;
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
@Service
public class BooksServletAPI extends HttpServlet {

    @Autowired
    private BookDaoService bookDaoService;


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

        try {
//            BookDao bookDao = new BookDaoImpl(DBConnection.getConnection());
//            books = bookDao.getAll();
            books = bookDaoService.getAll();
        } catch (MyException e) {
//            e.printStackTrace();
            req.getRequestDispatcher("/error").forward(req,resp);
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
