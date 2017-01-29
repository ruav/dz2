package ru.inno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.inno.utils.MyException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ruav on 15.01.17.
 */
@ControllerAdvice
//@Controller
public class ExceptionHandlingController {

    public static final String DEFAULT_ERROR_VIEW = "error";

    private Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler({MyException.class,Exception.class})
//    @ExceptionHandler(MyException.class)
    public String error(HttpServletRequest req, Exception e) {

        logger.info("Exception!!!!! " + e.getMessage());

        return "error";
    }

}
