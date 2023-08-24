package com.project.schoolschedulingsystem.Exceptions;

public class ClassNotFoundException extends RuntimeException{

    public ClassNotFoundException()
    {
        super();
    }

    public ClassNotFoundException(String message)
    {
        super(message);
    }
}
