package com.group4.exceptions;

/*
 * This Exception class can be used to give more detailed information about
 * the error that was returned from the database and the expected status code
 * that should result from the error.
 **/
public class DaoFailureException extends RuntimeException{

    private int statusCode = 0;
    private String briefDescription = "";

    public DaoFailureException(String message, int statusCode, String briefDescription){
        super(message);
        this.statusCode = statusCode;
        this.briefDescription = briefDescription;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getBriefDescription() {
        return briefDescription;
    }
}