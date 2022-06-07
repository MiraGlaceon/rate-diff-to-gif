package com.mira.demo.controllers.exceptions.advices;

import com.mira.demo.controllers.exceptions.RateBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RateBadRequestAdvice {

    @ExceptionHandler(RateBadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String rateBadRequestHandler(RateBadRequestException ex){
        return ex.getMessage();
    }
}
