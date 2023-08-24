package com.project.schoolschedulingsystem.Exceptions;

public class SchoolNotFoundException extends RuntimeException{

    public SchoolNotFoundException()
    {
        super();
    }

    public SchoolNotFoundException(String message)
    {
        super(message);
    }
}
