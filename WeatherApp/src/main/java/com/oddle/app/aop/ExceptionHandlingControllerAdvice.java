package com.oddle.app.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Author   : tao.tran
 * Date     : 12/18/17
 */
//@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingControllerAdvice.class);


//    @ExceptionHandler(HttpClientErrorException.class)
    public void clientError() {
        logger.error("Request raised a HttpClientErrorException");
    }
}
