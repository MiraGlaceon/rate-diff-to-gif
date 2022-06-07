package com.mira.demo.controllers.exceptions.advices;

import com.mira.demo.controllers.exceptions.RateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RateNotFoundAdvice {

    @ExceptionHandler(RateNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String rateNotFoundHandler(RateNotFoundException ex) {
        return ex.getMessage();
    }
}
