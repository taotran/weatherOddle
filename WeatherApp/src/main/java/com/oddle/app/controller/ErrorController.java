package com.oddle.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Author   : tao.tran
 * Date     : 12/18/17
 */

@Controller
public class ErrorController {

//    @ExceptionHandler(Exception.class)
    public String handlerError() {
        return "error";
    }
}
