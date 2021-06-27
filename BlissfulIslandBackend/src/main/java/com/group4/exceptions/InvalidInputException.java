package com.group4.exceptions;

public class InvalidInputException extends RuntimeException{
    private int statusCode = 0;
    public InvalidInputException(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode(){
        return statusCode;
    }
}
