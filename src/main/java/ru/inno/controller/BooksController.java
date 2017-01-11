package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.pojo.Book;
import ru.inno.service.BookDaoService;
import ru.inno.utils.MyException;

import javax.servlet.http.HttpSession;

/**
 * Created by ruav on 10.01.17.
 */
@Controller
public class BooksController {

    @Autowired
    private BookDaoService bookDaoService;

    @RequestMapping(value="/books", method = RequestMethod.GET)
    public ModelAndView getBooks(
            @RequestParam(name="addbook", defaultValue = "false") boolean addbook,
            @RequestParam(name="edit", defaultValue = "0") int editId,
            @ModelAttribute("book") Book bookIn,
            BindingResult bindingResult,
            ModelAndView modelAndView){


        String outString = "books/books";
//        HttpSession httpSession = req.getSession();
        try {
//        if (httpSession.getAttribute("userId") != null) {

//                BookDaoService bookDaoService = new BookDaoService();

            if(addbook){
                Book book = new Book();

                book.setAuthor(bookIn.getAuthor());
                book.setPublisher(bookIn.getPublisher());
                book.setTitle(bookIn.getTitle());
                book.setYearPublishing(bookIn.getYearPublishing());

                bookDaoService.add(book);

                outString="redirect:books";
//                resp.sendRedirect("/books");
//                req.getRequestDispatcher("jsp/books/books.jsp").forward(req, resp);
            } else
            if (editId != 0) {


                modelAndView.addObject("edit", editId);
                modelAndView.addObject("book",bookDaoService.getById(editId));
//                req.getRequestDispatcher("/jsp/books/editbook.jsp").forward(req, resp);
                outString = "books/editbook";
            }

            else {

                modelAndView.addObject("title", "Список литературы");
                modelAndView.addObject("books", bookDaoService.getAll());
//            System.out.println("user = " + httpSession.getAttribute("login"));
//            System.out.println(userDaoService.getAll().toString());
//                req.getRequestDispatcher("jsp/books/books.jsp").forward(req, resp);
            }
//        } else
//            req.getRequestDispatcher("/").forward(req, resp);
        } catch (MyException e) {
//            e.printStackTrace();
//            req.getRequestDispatcher("/error").forward(req,resp);
            outString = "error";
        }

        modelAndView.setViewName(outString);
        return modelAndView;
    }



    @RequestMapping(value="/books/addbook", method = RequestMethod.GET)
    public ModelAndView addBook(ModelAndView modelAndView){
                modelAndView.setViewName("books/addbook");
                return modelAndView;
    }

    @RequestMapping(value="/books", method = RequestMethod.POST)
    public ModelAndView editBooks(
            @RequestParam(value="remove", defaultValue="0") int removeId,
            @RequestParam(value="editbook", defaultValue="false") boolean editbookId,
            @ModelAttribute("book") Book bookIn,
            BindingResult  bindingResult,
            ModelAndView modelAndView){


//        HttpSession httpSession = req.getSession();
//        if (httpSession.getAttribute("userId") == null) {
//            req.getRequestDispatcher("/").forward(req, resp);
//        }
//        String outString = "books/books";
        String outString = "redirect:books";
        try {

            if (removeId != 0) {

//                int id = Integer.parseInt(req.getParameter("remove"));
//                BookDaoService bookDaoService = new BookDaoService();
                bookDaoService.removeById(removeId);
//                resp.sendRedirect("/books");
            } else if (editbookId ) {

//                int id = Integer.valueOf(req.getParameter("id"));

//                BookDaoService bookDaoService = new BookDaoService();

//                for(String str : req.getParameterMap().keySet()){
//                    System.out.println(str + ": " + req.getParameter(str));
//                }


                Book book = new Book();
                book.setId(bookIn.getId());
                book.setTitle(bookIn.getTitle());
                book.setAuthor(bookIn.getAuthor());
                book.setYearPublishing(bookIn.getYearPublishing());
                book.setPublisher(bookIn.getPublisher());

                bookDaoService.update(book);

//                resp.sendRedirect("/books");
            }
        }catch (MyException e){
//            e.printStackTrace();
            outString = "error";
        }

        modelAndView.setViewName(outString);
        return modelAndView;
    }

    @RequestMapping(value = "/books/book/{id}",method = RequestMethod.GET)
    public ModelAndView aboutUser(@PathVariable int id, ModelAndView modelAndView){

        String outString = "books/bookread";
        try {
            modelAndView.addObject("book", bookDaoService.getById(id));
        } catch (MyException e) {
            outString = "error";
        }

        modelAndView.setViewName(outString);

        return modelAndView;
    }
}
