package ru.inno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.inno.utils.MyException;

/**
 * Created by ruav on 15.01.17.
 */
@Controller
public class ExceptionHandlingController {


    @ExceptionHandler({MyException.class,Exception.class})
    public String error() {
        // Nothing to do.  Returns the logical view name of an error page, passed
        // to the view-resolver(s) in usual way.
        // Note that the exception is NOT available to this view (it is not added
        // to the model) but see "Extending ExceptionHandlerExceptionResolver"
        // below.
        return "error";
    }

}
