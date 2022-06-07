package com.mira.demo.controllers.exceptions;

public class RateBadRequestException extends RuntimeException{
    public RateBadRequestException(String base) {
        super("Request \"" + base + "\" is not valid format. Try \"RUB\" or \"EUR\" for example");
    }
}
