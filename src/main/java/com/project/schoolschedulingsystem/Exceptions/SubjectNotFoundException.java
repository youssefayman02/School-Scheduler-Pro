package com.project.schoolschedulingsystem.Exceptions;

public class SubjectNotFoundException extends RuntimeException{

    public SubjectNotFoundException()
    {
        super();
    }

    public SubjectNotFoundException(String message)
    {
        super(message);
    }
}
