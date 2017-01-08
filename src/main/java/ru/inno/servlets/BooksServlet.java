package ru.inno.servlets;

import ru.inno.pojo.Book;
import ru.inno.service.BookDaoService;
import ru.inno.utils.MyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        try {
//        if (httpSession.getAttribute("userId") != null) {

//                BookDaoService bookDaoService = new BookDaoService();

            if(req.getParameter("addbook")!= null){
                Book book = new Book();

                book.setAuthor(req.getParameter("author"));
                book.setPublisher(req.getParameter("publisher"));
                book.setTitle(req.getParameter("title"));
                book.setYearPublishing(Integer.parseInt(req.getParameter("yearpub")));

                BookDaoService.add(book);

                resp.sendRedirect("/books");
//                req.getRequestDispatcher("jsp/books/books.jsp").forward(req, resp);
            }else if (req.getParameter("edit") != null && !req.getParameter("edit").equals("")) {

//                bookDaoService = new BookDaoService();

                int id = Integer.valueOf(req.getParameter("edit"));
                req.setAttribute("edit", id);
                req.setAttribute("book",BookDaoService.getById(id));
                req.getRequestDispatcher("/jsp/books/editbook.jsp").forward(req, resp);

            }

            else {

//            UserDaoService userDaoService = applicationContext.getBean("");
                req.setAttribute("title", "Список литературы");
                req.setAttribute("books", BookDaoService.getAll());
//            System.out.println("user = " + httpSession.getAttribute("login"));
//            System.out.println(userDaoService.getAll().toString());
                req.getRequestDispatcher("jsp/books/books.jsp").forward(req, resp);
            }
//        } else
//            req.getRequestDispatcher("/").forward(req, resp);
        } catch (MyException e) {
//            e.printStackTrace();
            req.getRequestDispatcher("/error").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("userId") == null) {
            req.getRequestDispatcher("/").forward(req, resp);
        }
        try {

            if ((req.getParameter("remove") != null) && (!req.getParameter("remove").equals(""))) {

                int id = Integer.parseInt(req.getParameter("remove"));
//                BookDaoService bookDaoService = new BookDaoService();
                BookDaoService.removeById(id);
                resp.sendRedirect("/books");
            } else if (req.getParameter("editbook") != null && !req.getParameter("editbook").equals("")) {

                int id = Integer.valueOf(req.getParameter("id"));

//                BookDaoService bookDaoService = new BookDaoService();

                for(String str : req.getParameterMap().keySet()){
                    System.out.println(str + ": " + req.getParameter(str));
                }


                Book book = new Book();
                book.setId(Integer.parseInt(req.getParameter("id")));
                book.setTitle(req.getParameter("title"));
                book.setAuthor(req.getParameter("author"));
                book.setYearPublishing(Integer.parseInt(req.getParameter("yearpub")));
                book.setPublisher(req.getParameter("publisher"));

                BookDaoService.update(book);
                resp.sendRedirect("/books");
            }
        }catch (MyException e){
//            e.printStackTrace();
            req.getRequestDispatcher("/error").forward(req,resp);
        }
    }
}