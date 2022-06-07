package com.mira.demo.controllers.exceptions.advices;

import com.mira.demo.controllers.exceptions.GifNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GifNotFoundAdvice {

    @ExceptionHandler(GifNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String gifNotFoundHandler(GifNotFoundException ex) {
        return ex.getMessage();
    }
}
