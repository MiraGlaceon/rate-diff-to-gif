package com.mira.demo.controllers.exceptions;

public class RateNotFoundException extends RuntimeException{
    public RateNotFoundException(String base) {
        super("Currency rate with request \"" + base + "\" not found");
    }
}
