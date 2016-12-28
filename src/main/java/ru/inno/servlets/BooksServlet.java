package ru.inno.servlets;

import ru.inno.pojo.Book;
import ru.inno.pojo.User;
import ru.inno.service.BookDaoService;
import ru.inno.service.UserDaoService;
import ru.inno.utils.MyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Служит для обработки операций с
 * пользователями.
 * Все операции выполняются в POST методе,
 * а отображение списка - в методе GET.
 * * @author Alexander Rudnev
 */
public class BooksServlet extends HttpServlet {

//    ApplicationContext applicationContext = null;
//
//
//    @Override
//    public void init() throws ServletException {
//        applicationContext = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        HttpSession httpSession = req.getSession();

//        if (httpSession.getAttribute("userId") != null) {

                BookDaoService bookDaoService = new BookDaoService();

            if(req.getParameter("addbook")!= null){
                Book book = new Book();

                book.setAuthor(req.getParameter("author"));
                book.setPublisher(req.getParameter("publisher"));
                book.setTitle(req.getParameter("title"));
                book.setYearPublishing(Integer.parseInt(req.getParameter("yearpub")));

                try {
                    bookDaoService.addBook(book);
                } catch (MyException e) {
                    resp.sendRedirect("/jsp/error.jsp");
                    return;
                }

                resp.sendRedirect("/books");
//                req.getRequestDispatcher("jsp/books/books.jsp").forward(req, resp);
            }else if (req.getParameter("edit") != null && !req.getParameter("edit").equals("")) {

                bookDaoService = new BookDaoService();

                int id = Integer.valueOf(req.getParameter("edit"));
                req.setAttribute("edit", id);
                try {
                    req.setAttribute("book",bookDaoService.getBookById(id));
                } catch (MyException e) {
                    resp.sendRedirect("/jsp/error.jsp");
                    return;
                }
                req.getRequestDispatcher("/jsp/books/editbook.jsp").forward(req, resp);

            }

            else {

//            UserDaoService userDaoService = applicationContext.getBean("");
                req.setAttribute("title", "Список литературы");
                try {
                    req.setAttribute("books", bookDaoService.getAllBooks());
                } catch (MyException e) {
                    resp.sendRedirect("/jsp/error.jsp");
                    return;
                }
//            System.out.println("user = " + httpSession.getAttribute("login"));
//            System.out.println(userDaoService.getAllUsers().toString());
                req.getRequestDispatcher("jsp/books/books.jsp").forward(req, resp);
            }
//        } else
//            req.getRequestDispatcher("/").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("userId") == null) {
            req.getRequestDispatcher("/").forward(req, resp);
        }
            if ((req.getParameter("remove") != null) && (!req.getParameter("remove").equals(""))) {

                int id = Integer.parseInt(req.getParameter("remove"));

                try {
                    BookDaoService.removeBookById(id);
                } catch (MyException e) {
                    req.getRequestDispatcher("/jsp/error.jsp").forward(req,resp);
                    return;
                }
                resp.sendRedirect("/books");
            } else if (req.getParameter("editbook") != null && !req.getParameter("editbook").equals("")) {

                int id = Integer.valueOf(req.getParameter("id"));



                for(String str : req.getParameterMap().keySet()){
                    System.out.println(str + ": " + req.getParameter(str));
                }


                Book book = new Book();
                book.setId(Integer.parseInt(req.getParameter("id")));
                book.setTitle(req.getParameter("title"));
                book.setAuthor(req.getParameter("author"));
                book.setYearPublishing(Integer.parseInt(req.getParameter("yearpub")));
                book.setPublisher(req.getParameter("publisher"));

                try {
                    BookDaoService.updateBook(book);
                } catch (MyException e) {
                    req.getRequestDispatcher("/jsp/error.jsp").forward(req,resp);
                    return;
                }
                resp.sendRedirect("/books");
            }
    }
}