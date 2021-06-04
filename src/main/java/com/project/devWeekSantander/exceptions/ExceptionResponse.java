package com.project.devWeekSantander.exceptions;

public class ExceptionResponse {

    private String message;

    ExceptionResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
