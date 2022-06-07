package com.mira.demo.controllers.exceptions;

public class GifNotFoundException extends RuntimeException{
    public GifNotFoundException() {
        super("Gif not found");
    }
}
