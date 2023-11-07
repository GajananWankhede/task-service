package com.task.service.exception;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String message){
        super(String.valueOf(message));
    }
}
