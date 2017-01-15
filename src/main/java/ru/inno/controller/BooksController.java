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
public class BooksController extends ExceptionHandlingController{

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
       try {

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
            }
        } catch (MyException e) {
//            outString = "error";
        }

        modelAndView.setViewName(outString);
        return modelAndView;
    }



    @RequestMapping(value="/books/addbook", method = RequestMethod.GET)
    public ModelAndView addBook(ModelAndView modelAndView){
                modelAndView.setViewName("books/addbook");
                return modelAndView;
    }


    @RequestMapping(value="/books/remove", method = RequestMethod.POST)
    public ModelAndView editBooks(
            @RequestParam(value="remove", defaultValue="0") int removeId,
            ModelAndView modelAndView){

        String outString = "redirect:/";
        try{
                bookDaoService.removeById(removeId);
//                resp.sendRedirect("/books");
        }catch (MyException e){
//            outString = "error";
        }

        modelAndView.setViewName(outString);
        return modelAndView;
    }


    @RequestMapping(value="/books/edit", method = RequestMethod.POST)
    public ModelAndView editBook(@RequestParam(value="editbook", defaultValue="false") boolean editbookId,
                                 @ModelAttribute("book") Book bookIn,
                                 BindingResult  bindingResult,
                                 ModelAndView modelAndView){
        String outString = "redirect:/";
        try {

                Book book = new Book();
                book.setId(bookIn.getId());
                book.setTitle(bookIn.getTitle());
                book.setAuthor(bookIn.getAuthor());
                book.setYearPublishing(bookIn.getYearPublishing());
                book.setPublisher(bookIn.getPublisher());

                bookDaoService.update(book);

        }catch (MyException e){
//            outString = "error";
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
//            outString = "error";
        }

        modelAndView.setViewName(outString);

        return modelAndView;
    }
}
